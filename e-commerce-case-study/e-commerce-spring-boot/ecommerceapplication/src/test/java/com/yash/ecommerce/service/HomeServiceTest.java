package com.yash.ecommerce.service;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yash.ecommerce.controller.HomeController;
import com.yash.ecommerce.entity.Authorities;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.JwtUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebMvcTest(value = HomeService.class, includeFilters = {
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class) 
		})
public class HomeServiceTest {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	MyUserDetailService myUserDetailService;
	
	@Autowired
	HomeService service;
	
	@MockBean
	UserRepository userRepository;
	
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}
	
	@Test
	public void addUserTest() {
		User user = new User();
		user.setUserName("tanay");
		user.setEmail("tanay@gmail.com");
		Authorities role = new Authorities();
		role.setUserName("tanay");
		role.setAuthority("USER");
		user.setRoles(List.of(role));
		user.setAddress(null);
		user.setUserType("USER");
		user.setPassword("(Tanay@12)");

		when(userRepository.findByUserEmail("tanay@gmail.com")).thenReturn(Optional.empty());
		when(userRepository.save(user)).thenReturn(user);
		
		ServerResponse res = null;
		try {
			 res = service.addUser(user);
		} catch (UserCustomException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("200", res.getStatus());
	}
}
