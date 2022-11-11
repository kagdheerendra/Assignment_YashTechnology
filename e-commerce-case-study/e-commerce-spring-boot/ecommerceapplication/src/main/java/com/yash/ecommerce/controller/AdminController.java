package com.yash.ecommerce.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

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

/**
 * this will responsible to add,delete,update or approve the product.
 * @author dheerendra.kag
 *
 */
@CrossOrigin()
@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	/**
	 * will inject the service dependency into controller.
	 */
	@Autowired
	private AdminService adminService;
	
	/**
	 * will add the new product into product list.
	 * @param prodImage hold the current product image.
	 * @param description hold the current product description.
	 * @param price hold the current product price.
	 * @param productName hold the current product name.
	 * @param quantity hold the current product quantity.
	 * @return product response.
	 * @throws IOException 
	 * @throws ProductCustomException will generate the custom product exception.
	 */
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
	
	/**
	 * will update the product.
	 * @param prodImage hold the current product image.
	 * @param description hold the current product description.
	 * @param price hold the current product price.
	 * @param productName hold the current product name.
	 * @param quantity hold the current product quantity.
	 * @param productid hold the updated product id.
	 * @return server response.
	 * @throws IOException
	 */
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
	
	/**
	 * will delete the product.
	 * @param productid hold the product id which is going to be deleted.
	 * @return product response.
	 * @throws IOException
	 */
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
	
	/**
	 * will return the product info by product id.
	 * @param productid hold the requested productId.
	 * @return product entity response.
	 * @throws IOException
	 */
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
	
	/**
	 * will return the list of all order item.
	 * @return list of all order item.
	 * @throws IOException
	 */
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
	
	/**
	 * will approve or decline the order.
	 * @param orderId hold the requested order id.
	 * @param orderStatus hold the order status.
	 * @return server response.
	 * @throws IOException
	 */
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
