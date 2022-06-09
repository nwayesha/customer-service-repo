package com.api.security;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import net.minidev.json.JSONObject;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";
	private String authServiceURL;

	JWTAuthorizationFilter(String authServiceURL) {
		this.authServiceURL = authServiceURL;
	}

	final static Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			if (checkJWTToken(request, response)) {

				JSONObject authResponse = callValidateAuthAPI(request);

				Boolean isValid = Boolean.valueOf(authResponse.getAsString("status"));
				logger.info("authResponse: " + isValid);

				if (isValid) {
					List<String> authorities = getAuthorities(authResponse);

					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							authResponse.get("email"), null,
							authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
					SecurityContextHolder.getContext().setAuthentication(auth);
				} else {
					SecurityContextHolder.clearContext();
				}

			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (UnsupportedJwtException | MalformedJwtException | URISyntaxException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}

	private List<String> getAuthorities(JSONObject authResponse) {
		String authString[] = authResponse.getAsString("authorities").replace("[", "").replace("]", "").split(",");
		List<String> authorities = new ArrayList<>();
		for (String authStringTemp : authString) {
			authorities.add(authStringTemp);
		}
		return authorities;
	}

	private JSONObject callValidateAuthAPI(HttpServletRequest request) throws URISyntaxException {
		URI uri = new URI(authServiceURL);
		JSONObject json = new JSONObject();
		json.put("token", request.getHeader(HEADER).replace(PREFIX, ""));

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JSONObject> result = restTemplate.postForEntity(uri, json, JSONObject.class);
		return result.getBody();
	}

	private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}

}