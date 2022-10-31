package com.yash.ecommerce.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.repository.ProductRepository;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.Validator;

import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.exception.ProductCustomException;
@Service
public class AdminService {
	
	@Autowired
	private ProductRepository prodRepository;

	@Transactional
	public ProductResponse addProduct(MultipartFile prodImage, String productName, String description, String quantity, String price) throws ProductCustomException {
		ProductResponse resp = new ProductResponse();
		if (Validator.isStringEmpty(productName) || Validator.isStringEmpty(description)
				|| Validator.isStringEmpty(price) || Validator.isStringEmpty(quantity)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
			return resp;
		} else {
			Product prod = new Product();
			prod.setDescription(description);
			prod.setPrice(Double.parseDouble(price));
			prod.setProductName(productName);
			prod.setQuantity(Integer.parseInt(quantity));
			if (prodImage != null) {
				try {
					prod.setProductimage(prodImage.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Product product = prodRepository.save(prod);
				System.out.println(product);
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.ADD_SUCCESS_MESSAGE);
				try {
					List<Product> list = prodRepository.findAll();
					if(list.size()>0) {
						resp.setOblist(list);
					}else {
						throw new ProductCustomException("Unable to retrieve products, please try again");	
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}catch(Exception e) {
				throw new ProductCustomException("Unable to save product details, please try again");
			}
		}
		return resp;
	}
}
