package com.auth.api.authservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.auth.api.authservice.entity.AuthUser;
import com.auth.api.authservice.repository.AuthUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class AuthUserService {

	final static Logger logger = LoggerFactory.getLogger(AuthUserService.class);

	@Autowired
	private AuthUserRepository authUserRepository;

	@Value("${auth.api.jwtSecret}")
	private String secretKey;

	@Value("${auth.api.jwtExpirationMs}")
	private int jwtExpirationMs;

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public AuthUser saveAuthUser(AuthUser authUser) {
		try {
			authUser.setId(authUser.getId());
			authUser.setTokenExpireTime(dateFormat.format(Calendar.getInstance().getTime()));
			authUserRepository.save(authUser);
			logger.info("saving auth user >" + authUser.getId());
		} catch (Exception e) {
			logger.error("error => ", e);
		}
		return authUser;
	}

	public AuthUser updateAuthUserToken(AuthUser authUser) {
		try {
			authUserRepository.save(authUser);
			logger.info("updating auth user >" + authUser.getId());
		} catch (Exception e) {
			logger.error("error => ", e);
		}
		return authUser;
	}

	public JSONObject generateToken(AuthUser authUser) {
		JSONObject response = new JSONObject();
		String email = authUser.getEmail();
		Optional<AuthUser> getAuthUser = getAuthUserByEmail(email);
		if (!getAuthUser.isEmpty()) {
			AuthUser user = getAuthUser.get();
			if (user.getPassword().equals(authUser.getPassword())) {
				String token = buildToken(authUser);

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.MINUTE, +30);

				user.setToken(token);
				user.setTokenExpireTime(dateFormat.format(calendar.getTime()));
				updateAuthUserToken(user);

				logger.info("token > " + token);
				response.put("token", token);
				response.put("email", user.getEmail());
				logger.info("token is generated...");
			}

		}
		return response;
	}

	private String buildToken(AuthUser authUser) {
		// String token = Jwts.builder().setSubject(authUser.getEmail()).setIssuedAt(new
		// Date())
		// .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
		// .signWith(SignatureAlgorithm.HS512, secretKey).compact();

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN_USER,MANAGER_USER,GUEST_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(authUser.getEmail())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "" + token;
	}

	private Optional<AuthUser> getAuthUserByEmail(String email) {
		return authUserRepository.findByEmail(email);
	}

	private Optional<AuthUser> getAuthUserByToken(String token) {
		return authUserRepository.findByToken(token);
	}

	public JSONObject validateAuthUser(JSONObject json) throws ParseException {
		JSONObject jsonResponse = new JSONObject();
		String token = json.getAsString("token");

		logger.info("token >>>" + token);		
		
		Optional<AuthUser> getAuthUser = getAuthUserByToken(token);
		logger.info("isempty > " + getAuthUser.isEmpty());
		if (!getAuthUser.isEmpty()) {
			Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
			List<String> authorities = (List) claims.get("authorities");			
			
			AuthUser user = getAuthUser.get();
			boolean takenMatch = user.getToken().equals(token);

			long currentTime = Calendar.getInstance().getTimeInMillis(); // 1651083173328
			long expireTime = dateFormat.parse(user.getTokenExpireTime()).getTime();// 1650930285000
			user.getTokenExpireTime();
			boolean isTokenExpire = expireTime < currentTime;

			jsonResponse.put("status", (takenMatch && !isTokenExpire));
			jsonResponse.put("authorities", new JSONArray().toJSONString(authorities));
			jsonResponse.put("email", user.getEmail());
		} else {
			jsonResponse.put("status", false);
		}
		return jsonResponse;
	}

}
