package com.yash.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.service.AdminService;
import com.yash.ecommerce.service.MyUserDetailService;
import com.yash.ecommerce.util.JwtUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebMvcTest(value = AdminController.class, includeFilters = {
		// to include JwtUtil in spring context
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtUtil.class) })
public class AdminControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	UserDetails userDetails;
	
	@MockBean
	MyUserDetailService myUserDetailService;
	
	@MockBean
	AdminService adminService;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}
	
	@Test
	public void addProductTest() {
		HashMap<String, String> map = new HashMap<>();
		map.put("email", "akku@gmail.com");
		map.put("password", "(Akku@12)");
		map.put("email", "akku@gmail.com");
		map.put("password", "(Akku@12)");
		
		String productName = "mobile";
		String description = "mobile";
		String quantity = "5";
		String price = "1200";
		MultipartFile prodImage = null;
		ProductResponse response = new ProductResponse();
		response.setStatus("200");
		response.setOblist(List.of());
		byte[] proImage = null;
		try {
			Product p = new Product(0,"mobile","mobile",12000,4,proImage);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
			ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
			String requestJson = ow.writeValueAsString(p);
			
        	when(adminService.addProduct(prodImage, productName, description, quantity, price)).thenReturn(response);
        	MvcResult res = this.mockMvc.perform(MockMvcRequestBuilders.get("/admin/addProduct").contentType(MediaType.APPLICATION_JSON).content(requestJson).with(user("akku@gmail.com")))
        			.andDo(print()).andReturn();
        	System.out.println("mvcResult = "+res.getResponse().getContentAsString(Charset.forName("utf8")));
        	assertEquals(200, res.getResponse().getStatus());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
