package com.yash.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.Validator;

/**
 * will fetch the user info from database to authenticate the user.
 * @author dheerendra.kag
 *
 */
@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user;
		if(Validator.isValidEmail(username)) {
			user = repository.findByUserEmail(username);
		}else {
			user = repository.findByUserName(username);
		}
		//Optional<User> user = repository.findByUserEmail(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return user.map(MyUserDetails::new).get();
	}

}
