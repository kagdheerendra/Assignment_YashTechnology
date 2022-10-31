package com.yash.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.yash.ecommerce.entity.Authorities;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.JwtUtil;
import com.yash.ecommerce.util.Validator;

@Service
public class HomeService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailService userDetailService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtutil;
	
	public ServerResponse generateToken(HashMap<String, String> credential) throws UserCustomException {
		ServerResponse resp = new ServerResponse();
		final String email = credential.get(ConstantProperties.USER_EMAIL);
		final String password = credential.get(ConstantProperties.USER_PASSWORD);
		if (!Validator.isValidEmail(email)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.INVALID_EMAIL_FAIL_MSG);
		} else if (!Validator.isValidPassword(password)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.INVALID_PASSWORD_FAIL_MSG);
		} else {
			try {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			} catch (BadCredentialsException e) {
				throw new UserCustomException("Invalid User Credentials");
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
	public ServerResponse addUser(User user) throws UserCustomException {
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
				resp.setMessage(ConstantProperties.USER_EMAIL_ALREADY_EXISTS);
				resp.setStatus(ConstantProperties.CONFLICT);
			}else {
				Authorities a = new Authorities();
				a.setUserName(user.getUserName());
				if (user.getUserType().equals("customer")) {
					a.setAuthority("ROLE_USER");
				} else if (user.getUserType().equals("admin")) {
					a.setAuthority("ROLE_ADMIN");
				}
				user.setRoles(List.of(a));
				try {
					Optional<User> op = Optional.ofNullable(userRepository.save(user));
					if (op.isPresent()) {
						resp.setStatus(ConstantProperties.SUCCESS_CODE);
						resp.setMessage(ConstantProperties.CUST_REG);
						System.out.println(op.get().toString());
						resp.setUserType(op.get().getUserType());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}

		return resp;
	}
}
