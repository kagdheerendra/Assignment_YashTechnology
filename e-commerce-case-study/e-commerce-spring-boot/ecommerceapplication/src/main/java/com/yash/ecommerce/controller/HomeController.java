package com.yash.ecommerce.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.service.HomeService;
import com.yash.ecommerce.service.MyUserDetailService;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.JwtUtil;

@CrossOrigin()
@RestController
@RequestMapping("/home")
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);
	
	@Autowired
	private HomeService service;

	@PostMapping("/auth")
	public ResponseEntity<ServerResponse> createAuthToken(@RequestBody HashMap<String, String> credential) {
		logger.debug("inside createAuthToken method of HomeController {}", credential);
        ServerResponse resp = null;
		try {
			resp = service.generateToken(credential);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        HttpStatus https = HttpStatus.OK;
        logger.debug("resp getStatus {}", resp.getStatus());
		if(resp.getStatus() == "400") {
			https = HttpStatus.BAD_REQUEST;
		}
 		return new ResponseEntity<ServerResponse>(resp, https);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ServerResponse> addUser(@RequestBody User user) throws UserCustomException {
		logger.info("inside adduser method of HomeController {}", user);
		ServerResponse resp = service.addUser(user);
		HttpStatus https = HttpStatus.ACCEPTED;
		if(resp.getStatus() == "400") {
			https = HttpStatus.BAD_REQUEST;
		}else if(resp.getStatus().equalsIgnoreCase("CONFLICT")) {
			https = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<ServerResponse>(resp, https);
	}
	
	@GetMapping(value = "/logout")
	public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}
}
