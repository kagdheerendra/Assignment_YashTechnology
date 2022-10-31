package com.yash.ecommerce.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ecommerce.exception.ProductCustomException;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.service.AdminService;
import com.yash.ecommerce.util.ConstantProperties;

@CrossOrigin()
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ProductResponse> addProduct(
			@RequestParam(name = ConstantProperties.PROD_FILE, required = false) MultipartFile prodImage,
			@RequestParam(name = ConstantProperties.PROD_DESC) String description,
			@RequestParam(name = ConstantProperties.PROD_PRICE) String price,
			@RequestParam(name = ConstantProperties.PROD_NAME) String productName,
			@RequestParam(name = ConstantProperties.PROD_QUANITY) String quantity) throws IOException, ProductCustomException {
		ProductResponse resp = adminService.addProduct(prodImage, productName, description, quantity, price);
		HttpStatus https = HttpStatus.OK;
		if(resp.getStatus().equals("BAD_REQUEST_CODE")) {
			https = HttpStatus.BAD_REQUEST;
		}
        return new ResponseEntity<ProductResponse>(resp, HttpStatus.OK);
	}
}
