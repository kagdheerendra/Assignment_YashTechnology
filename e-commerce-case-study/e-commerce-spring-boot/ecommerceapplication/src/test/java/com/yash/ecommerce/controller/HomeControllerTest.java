package com.yash.ecommerce.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yash.ecommerce.EcommerceApplication;
import com.yash.ecommerce.config.JwtRequestFilter;
import com.yash.ecommerce.config.SecurityConfiguration;
import com.yash.ecommerce.service.HomeService;
import com.yash.ecommerce.service.MyUserDetailService;
import com.yash.ecommerce.util.JwtUtil;

//@WebMvcTest(controllers = HomeController.class, useDefaultFilters = false)
//@WebMvcTest(controllers = HomeController.class)
@ContextConfiguration(classes = EcommerceApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(value = HomeController.class, includeFilters = {
	    // to include JwtUtil in spring context
	    @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class)})
@Import({SecurityConfiguration.class})
public class HomeControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	//@Autowired
	MyUserDetailService myUserDetailService; 
	
	@MockBean
	//@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@MockBean
	//@Autowired
	JwtUtil jwtUtil;
	
	@MockBean
	HomeService homeService;
	
	private static UserDetails dummy;
	private static String jwtToken;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).addFilter(jwtRequestFilter).build();
	}

	@Test
	//@WithMockUser(username = "akku@gmail.com", password = "(Akku@12)", roles = "ADMIN")
	//@WithUserDetails(value = "akku", userDetailsServiceBeanName = "myUserDetailService")
	public void generateAuthForUserName() throws Exception {
		
		Map<String, String> map = new HashMap<>();
		map.put("email", "akku@gmail.com");
		map.put("password", "(Akku@12)");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(map);
		
		System.out.println("requestJson = "+requestJson.toString());
		
//        MvcResult res = this.mockMvc.perform(MockMvcRequestBuilders.post("/home/auth").contentType(MediaType.APPLICATION_JSON).content(requestJson))
//				.andDo(print()).andExpect(status().isOk()).andReturn();


		
//		ResultActions res = this.mockMvc.perform(post("/home/auth").with(csrf()));
//		res.andDo(print());
		
//		assertNotNull(this.mockMvc);
		MvcResult res = this.mockMvc.perform(MockMvcRequestBuilders.post("/home/auth").with(csrf()).with(user("akku@gmail.com").password("(Akku@12)").roles("ADMIN"))).andDo(print())
				.andExpect(authenticated()).andExpect(status().isOk()).andReturn();
		assertEquals(200, res.getResponse().getStatus());
	}
}
