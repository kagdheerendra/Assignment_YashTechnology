package com.yash.ecommerce.controller;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ecommerce.EcommerceApplication;
import com.yash.ecommerce.entity.Address;
import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.exception.CartCustomException;
import com.yash.ecommerce.exception.ProductCustomException;
import com.yash.ecommerce.model.CartResponse;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.model.Response;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.model.UserResponse;
import com.yash.ecommerce.service.UserService;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.Validator;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class UserController {
    
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getProducts")
	public ResponseEntity<ProductResponse> getProducts(Authentication auth) throws IOException {
		logger.debug("authentication token {}"+auth.toString());
		ProductResponse resp = null;
		try {
			resp = userService.getProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ProductResponse>(resp, HttpStatus.OK);
	}

	@GetMapping("/addToCart")
	public ResponseEntity<ServerResponse> addToCart(@RequestParam(ConstantProperties.PROD_ID) String productId,
			Authentication auth) throws IOException {

		ServerResponse resp = new ServerResponse();
		try {
			resp = userService.addToCart(productId, auth);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CartCustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}
		return new ResponseEntity<ServerResponse>(resp, https);
	}
	
	@GetMapping("/viewCart")
	public ResponseEntity<CartResponse> viewCart(Authentication auth) throws IOException {
		CartResponse resp = new CartResponse();
		try {
			resp = userService.viewCart(auth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("500")) {
			https = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CartResponse>(resp, https);
	}
	
	@PutMapping("/updateCart")
	public ResponseEntity<CartResponse> updateCart(@RequestBody HashMap<String, String> cart, Authentication auth)
			throws IOException {

		CartResponse resp = userService.updateCart(cart, auth);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("500")) {
			https = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CartResponse>(resp, https);
	}
	
	@DeleteMapping("/delCart")
	public ResponseEntity<CartResponse> delCart(@RequestParam(name = ConstantProperties.BUF_ID) String bufcartid,
			Authentication auth) throws IOException {

		CartResponse resp = null;
		try {
			resp = userService.delCart(bufcartid, auth);
		} catch (IOException | CartCustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("500")) {
			https = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<CartResponse>(resp, https);
	}
	
	@GetMapping("/placeOrder")
	public ResponseEntity<ServerResponse> placeOrder(Authentication auth) throws IOException {

		ServerResponse resp = userService.placeOrder(auth);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("500")) {
			https = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<ServerResponse>(resp, https);
	}
	
	@PostMapping("/addAddress")
	public ResponseEntity<UserResponse> addAddress(@RequestBody Address address, Authentication auth) {
		UserResponse resp = userService.addAddress(address, auth);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("400")) {
			https = HttpStatus.BAD_REQUEST;
		}else if(resp.getStatus().equals("500")) {
			https = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<UserResponse>(resp, https);
	}
	
	@GetMapping("/getAddress")
	public ResponseEntity<Response> getAddress(Authentication auth) {
		Response resp = userService.getAddress(auth);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("500")) {
			https = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
	
	@PostMapping("/buyNow")
	public ResponseEntity<ServerResponse> buyNow(@RequestBody Product product,
			Authentication auth) throws IOException {

		ServerResponse resp = new ServerResponse();
		try {
			resp = userService.buyNow(product, auth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}
		return new ResponseEntity<ServerResponse>(resp, https);
	}


}
