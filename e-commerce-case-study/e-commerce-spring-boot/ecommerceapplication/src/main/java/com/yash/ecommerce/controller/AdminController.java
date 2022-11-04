package com.yash.ecommerce.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ecommerce.exception.OrderCustomException;
import com.yash.ecommerce.exception.ProductCustomException;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.model.ViewOrderResponse;
import com.yash.ecommerce.service.AdminService;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.Validator;

@CrossOrigin()
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductResponse> addProduct(
			@RequestParam(name = ConstantProperties.PROD_FILE, required = false) MultipartFile prodImage,
			@RequestParam(name = ConstantProperties.PROD_DESC) String description,
			@RequestParam(name = ConstantProperties.PROD_PRICE) String price,
			@RequestParam(name = ConstantProperties.PROD_NAME) String productName,
			@RequestParam(name = ConstantProperties.PROD_QUANITY) String quantity) throws IOException, ProductCustomException {
		logger.debug("inside addProduct in admincontroller {}", description, price, productName, quantity);
		ProductResponse resp = adminService.addProduct(prodImage, productName, description, quantity, price);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("BAD_REQUEST_CODE")) {
			https = HttpStatus.BAD_REQUEST;
		}
        return new ResponseEntity<ProductResponse>(resp, HttpStatus.OK);
	}
	
	@PutMapping("/updateProducts")
	public ResponseEntity<ServerResponse> updateProducts(
			@RequestParam(name = ConstantProperties.PROD_FILE, required = false) MultipartFile prodImage,
			@RequestParam(name = ConstantProperties.PROD_DESC) String description,
			@RequestParam(name = ConstantProperties.PROD_PRICE) String price,
			@RequestParam(name = ConstantProperties.PROD_NAME) String productname,
			@RequestParam(name = ConstantProperties.PROD_QUANITY) String quantity,
			@RequestParam(name = ConstantProperties.PROD_ID) String productid) throws IOException {
		ServerResponse resp = adminService.updateProducts(prodImage, description, price, productname, quantity, productid);
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}else 	if(resp.getStatus().equals("400")) {
			https = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<ServerResponse>(resp, https);
	}
	
	@DeleteMapping("/delProduct")
	public ResponseEntity<ProductResponse> delProduct(@RequestParam(name = ConstantProperties.PROD_ID) String productid)
			throws IOException {
		ProductResponse resp = adminService.delProduct(productid);
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}else 	if(resp.getStatus().equals("400")) {
			https = HttpStatus.NOT_ACCEPTABLE;
		}
        return new ResponseEntity<ProductResponse>(resp, https);
	}
	
	@GetMapping("/findProductById")
	public ResponseEntity<ProductResponse> findProductById(@RequestParam(name = ConstantProperties.PROD_ID) String productid) throws IOException{
		ProductResponse resp = adminService.findProductById(productid);
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}else 	if(resp.getStatus().equals("400")) {
			https = HttpStatus.NOT_ACCEPTABLE;
		}
        return new ResponseEntity<ProductResponse>(resp, https);
	}
	
	@GetMapping("/viewOrders")
	public ResponseEntity<ViewOrderResponse> viewOrders() throws IOException {

		ViewOrderResponse resp = null;
		try {
			resp = adminService.viewOrders();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}
		return new ResponseEntity<ViewOrderResponse>(resp, https);
	}
	
	@PostMapping("/updateOrder")
	public ResponseEntity<ServerResponse> updateOrders(@RequestParam(name = ConstantProperties.ORD_ID) String orderId,
			@RequestParam(name = ConstantProperties.ORD_STATUS) String orderStatus) throws IOException {

		ServerResponse resp = adminService.updateOrders(orderId, orderStatus);
		HttpStatus https = HttpStatus.INTERNAL_SERVER_ERROR;
		if(resp.getStatus().equals("200")) {
			https = HttpStatus.OK;
		}else 	if(resp.getStatus().equals("400")) {
			https = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<ServerResponse>(resp, https);
	}
}
