package com.yash.ecommerce.controller;

import java.util.HashMap;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	private static Logger logger = Logger.getLogger(HomeController.class.getName());
	
	@Autowired
	private HomeService service;

	@PostMapping("/auth")
	public ResponseEntity<ServerResponse> createAuthToken(@RequestBody HashMap<String, String> credential) throws UserCustomException {
        
		ServerResponse resp = service.generateToken(credential);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equalsIgnoreCase("BAD_REQUEST_CODE")) {
			https = HttpStatus.BAD_REQUEST;
		}
 		return new ResponseEntity<ServerResponse>(resp, https);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<ServerResponse> addUser(@RequestBody User user) throws UserCustomException {
		System.out.println(user);
		ServerResponse resp = service.addUser(user);
		HttpStatus https = HttpStatus.ACCEPTED;
		if(resp.getStatus().equalsIgnoreCase("BAD_REQUEST_CODE")) {
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
