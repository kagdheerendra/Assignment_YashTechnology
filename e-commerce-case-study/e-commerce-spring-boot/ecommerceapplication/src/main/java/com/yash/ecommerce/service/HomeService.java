package com.yash.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.yash.ecommerce.controller.HomeController;
import com.yash.ecommerce.entity.Authorities;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.JwtUtil;
import com.yash.ecommerce.util.Validator;

import net.bytebuddy.utility.RandomString;

/**
 * this will responsible to check the authentication, communicate with the database and map the result with entity classes.
 * @author dheerendra.kag
 *
 */
@Service
public class HomeService {

	private static final Logger logger = LogManager.getLogger(HomeService.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailService userDetailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	SendMailService mailService;
	
	public ServerResponse generateToken(HashMap<String, String> credential) throws UserCustomException {
		logger.debug("inside generateToken method of HomeService {}", credential);
		ServerResponse resp = new ServerResponse();
		final String email = credential.get(ConstantProperties.USER_EMAIL);
		final String password = credential.get(ConstantProperties.USER_PASSWORD);
		logger.debug("inside generateToken method of homeService {}", email, password);
		if (!Validator.isValidEmail(email)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.INVALID_EMAIL_FAIL_MSG);
		} else if (!Validator.isValidPassword(password)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.INVALID_PASSWORD_FAIL_MSG);
		}else {
			
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			} catch (BadCredentialsException e) {
				resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
				resp.setMessage(ConstantProperties.INVALID_CREDENTIAL);
				return resp;
				//throw new UserCustomException("Invalid User Credentials");
			}
			
			Optional<User> checkUser = userRepository.findByUserEmail(email);
			if(!(checkUser.isPresent() && checkUser.get().isVerified())) {
				resp.setStatus(ConstantProperties.UNAUTHORIZED);
				resp.setMessage(ConstantProperties.NOT_VERIFIED);
				return resp;
			}
			
			final UserDetails userDetails = userDetailService.loadUserByUsername(email);
			final String jwt = jwtutil.generateToken(userDetails);

	        resp.setStatus(ConstantProperties.SUCCESS_CODE);
			resp.setMessage(ConstantProperties.SUCCESS_MESSAGE);
			resp.setAuthToken(jwt);

			if (userDetails != null
					&& userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
				resp.setUserType("ADMIN");
			} else {
				resp.setUserType("CUSTOMER");
			}

		}
		return resp;
	}
	
	@Transactional
	public ServerResponse addUser(User user, HttpServletRequest request) throws UserCustomException {
		logger.info("inside adduser method of homeservice {}", user);
		ServerResponse resp = new ServerResponse();
		if (Validator.isUserEmpty(user)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidEmail(user.getEmail())) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.INVALID_EMAIL_FAIL_MSG);
		} else if (!Validator.isValidPassword(user.getPassword())) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.INVALID_PASSWORD_FAIL_MSG);
		} else {
			Optional<User> checkUser = userRepository.findByUserEmail(user.getEmail());
			if(checkUser.isPresent()) {
				logger.info("user already present");
				resp.setMessage(ConstantProperties.USER_EMAIL_ALREADY_EXISTS);
				resp.setStatus(ConstantProperties.CONFLICT);
			}else {
				logger.info("user going to add");
				Authorities a = new Authorities();
				a.setUserName(user.getUserName());
				if (user.getUserType().equals("customer")) {
					a.setAuthority("ROLE_USER");
				} else if (user.getUserType().equals("admin")) {
					a.setAuthority("ROLE_ADMIN");
				}
				user.setRoles(List.of(a));
				String randomCode = RandomString.make(64);
				user.setVerificationCode(randomCode);
				user.setVerified(true);
				
				try {
					Optional<User> op = Optional.ofNullable(userRepository.save(user));
					if (op.isPresent()) {
						resp.setStatus(ConstantProperties.SUCCESS_CODE);
						resp.setMessage(ConstantProperties.CUST_REG);
						resp.setUserType(op.get().getUserType());
					    mailService.sendSimpleEmail(Validator.getSiteURL(request), user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}

		return resp;
	}
	
	public boolean verifyUser(String verificationCode) {
	    User user = userRepository.findByVerificationCode(verificationCode);
	    if (user == null || user.isVerified()) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setVerified(true);
	        userRepository.save(user);
	        return true;
	    }
	}
}
