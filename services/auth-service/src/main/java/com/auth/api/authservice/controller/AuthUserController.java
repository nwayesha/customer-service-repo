package com.auth.api.authservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.api.authservice.entity.AuthUser;
import com.auth.api.authservice.service.AuthUserService;

import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("auth")
public class AuthUserController {

	final static Logger logger = LoggerFactory.getLogger(AuthUserController.class);

	@Autowired
	private AuthUserService authUserService;

	@ApiOperation(value = "Create Auth User")
	@RequestMapping(value = "auth-user/save", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveAuthUser(@RequestBody AuthUser authUser, HttpServletResponse response) {
		try {
			authUserService.saveAuthUser(authUser);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			logger.error("Error=>",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Success");
	}

	@ApiOperation(value = "Create Auth User")
	@RequestMapping(value = "token/generate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> generateToken(@RequestBody AuthUser authUser, HttpServletResponse response) {
		try {
			JSONObject jsonResponse = authUserService.generateToken(authUser);
			return ResponseEntity.ok().body(jsonResponse);			
		} catch (Exception e) {
			logger.error("Error=>",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "Validate Auth User")
	@RequestMapping(value = "token/validate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> validateToken(@RequestBody JSONObject json, HttpServletResponse response) {
		try {
			JSONObject jsonResponse  = authUserService.validateAuthUser(json);
			logger.info("validRequest > " + jsonResponse.getAsString("status"));
			
			if (jsonResponse.getAsString("status").equals("true")) {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			return ResponseEntity.ok().body(jsonResponse);
		} catch (Exception e) {
			logger.error("Error=>",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}

	@ApiOperation(value = "Invalidate Auth User")
	@RequestMapping(value = "token/invalidate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> invalidateToken(@RequestBody AuthUser authUser, HttpServletResponse response) {
		try {
			authUserService.saveAuthUser(authUser);
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception e) {
			logger.error("Error=>",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok("Success");
	}

}
