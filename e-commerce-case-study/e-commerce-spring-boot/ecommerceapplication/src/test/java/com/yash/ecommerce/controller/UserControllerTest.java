package com.yash.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.service.MyUserDetailService;
import com.yash.ecommerce.service.UserService;
import com.yash.ecommerce.util.JwtUtil;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebMvcTest(value = UserController.class, includeFilters = {
		// to include JwtUtil in spring context
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class) })
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	UserDetails userDetails;
	
	@MockBean
	MyUserDetailService myUserDetailService;
	
	@MockBean
	UserService userService;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}
	
	@Test
	public void getProductTest() {
		ProductResponse response = new ProductResponse();
		Product product = new Product();
		product.setProductName("mobile");
		product.setDescription("mobile");
		product.setPrice(1200.50);
		product.setQuantity(5);
		response.setOblist(List.of(product));
		response.setStatus("200");
		try {
        	when(userService.getProducts()).thenReturn(response);
        	MvcResult res = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getProducts").with(user("dk@gmail.com")))
        			.andDo(print()).andReturn();
        	System.out.println("mvcResult = "+res.getResponse().getContentAsString(Charset.forName("utf8")));
        	assertEquals(200, res.getResponse().getStatus());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
