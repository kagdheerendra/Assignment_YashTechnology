package com.yash.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yash.ecommerce.entity.Authorities;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.service.HomeService;
import com.yash.ecommerce.service.MyUserDetailService;
import com.yash.ecommerce.util.JwtUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebMvcTest(value = HomeController.class, includeFilters = {
		// to include JwtUtil in spring context
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class) })
public class HomeControllerDemoTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	MyUserDetailService myUserDetailService;

	@MockBean
	HomeService homeService;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	//@Ignore
	@Test
	public void generateAuthForUserNameTest() throws Exception {
		HashMap<String, String> map = new HashMap<>();
		map.put("email", "akku@gmail.com");
		map.put("password", "(Akku@12)");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(map);

		ServerResponse response = new ServerResponse();
		response.setStatus("200");
		response.setAuthToken(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2t1IiwiZXhwIjoxNjY3OTI4MDAzLCJpYXQiOjE2Njc4OTIwMDN9.w_VcGl2zDQUMv4wHe9CrHTUr0hGZYXBt5XmCmON9W9E");
		when(homeService.generateToken(map)).thenReturn(response);

		MvcResult res = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/home/auth").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print()).andReturn();
		assertEquals(200, res.getResponse().getStatus());
	}
	
	@Ignore
	@Test
	public void addUserTest() throws Exception {
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
		
		ServerResponse response = new ServerResponse();
		response.setStatus("200");
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(user);
			
			when(homeService.addUser(any(User.class))).thenReturn(response);
			
			MvcResult res = this.mockMvc.perform(
					MockMvcRequestBuilders.post("/home/signup").contentType(MediaType.APPLICATION_JSON).content(requestJson))
					.andDo(print()).andReturn();
			assertEquals(202, res.getResponse().getStatus());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
