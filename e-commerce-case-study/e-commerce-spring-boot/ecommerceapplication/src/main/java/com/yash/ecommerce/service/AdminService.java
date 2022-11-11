package com.yash.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yash.ecommerce.model.Order;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.model.ViewOrderResponse;
import com.yash.ecommerce.repository.CartRepository;
import com.yash.ecommerce.repository.OrderRepository;
import com.yash.ecommerce.repository.ProductRepository;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.Validator;
import com.yash.ecommerce.controller.AdminController;
import com.yash.ecommerce.entity.PlaceOrder;
import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.OrderCustomException;
import com.yash.ecommerce.exception.ProductCustomException;

@Service
public class AdminService {

	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	private ProductRepository prodRepository;

	@Autowired
	private OrderRepository ordRepository;

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private SendMailService mailService;

	@Transactional
	public ProductResponse addProduct(MultipartFile prodImage, String productName, String description, String quantity,
			String price) throws ProductCustomException {
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
				logger.debug("prod is {}", product.getProductName());
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.ADD_SUCCESS_MESSAGE);
				try {
					List<Product> list = prodRepository.findAll();
					logger.debug("product list {}", list.toString());
					if (list.size() > 0) {
						resp.setOblist(list);
					} else {
						throw new ProductCustomException("Unable to retrieve products, please try again");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				throw new ProductCustomException("Unable to save product details, please try again");
			}
		}
		return resp;
	}

	@Transactional
	public ServerResponse updateProducts(MultipartFile prodImage, String description, String price, String productname,
			String quantity, String productid) throws IOException {
		ServerResponse resp = new ServerResponse();
		if (Validator.isStringEmpty(productname) || Validator.isStringEmpty(description)
				|| Validator.isStringEmpty(price) || Validator.isStringEmpty(quantity)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
			return resp;
		} else {
			try {
				Product prodOrg = prodRepository.findByProductId(Integer.parseInt(productid)).orElseThrow(
						() -> new ProductCustomException("Unable to find product details, please try again"));
				Product prod = new Product(Integer.parseInt(productid), description, productname,
						Double.parseDouble(price), Integer.parseInt(quantity), prodOrg.getProductimage());
				Product p = prodRepository.save(prod);
				if (p != null) {
					resp.setStatus(ConstantProperties.SUCCESS_CODE);
					resp.setMessage(ConstantProperties.UPD_SUCCESS_MESSAGE);
				} else {
					resp.setStatus(ConstantProperties.FAILURE_CODE);
					resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
					throw new ProductCustomException("Unable to update product details, please try again");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	@Transactional
	public ProductResponse delProduct(@RequestParam(name = ConstantProperties.PROD_ID) String productid)
			throws IOException {
		ProductResponse resp = new ProductResponse();
		if (Validator.isStringEmpty(productid)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
			return resp;
		} else {
			try {
				prodRepository.deleteByProductId(Integer.parseInt(productid));
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.DEL_SUCCESS_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	@Transactional
	public ProductResponse findProductById(@RequestParam(name = ConstantProperties.PROD_ID) String productid)
			throws IOException {
		ProductResponse resp = new ProductResponse();
		if (Validator.isStringEmpty(productid)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
			return resp;
		} else {
			try {
				Product p = prodRepository.findByProductId(Integer.parseInt(productid)).orElseThrow(
						() -> new ProductCustomException("Unable to find product details, please try again"));
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.DEL_SUCCESS_MESSAGE);
				resp.setOblist(List.of(p));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resp;
	}

	public ViewOrderResponse viewOrders() throws IOException, OrderCustomException {

		ViewOrderResponse resp = new ViewOrderResponse();
		try {
			List<Order> orderList = new ArrayList();
			List<PlaceOrder> poList = ordRepository.findAll();
			resp.setStatus(ConstantProperties.SUCCESS_CODE);
			resp.setMessage(ConstantProperties.VIEW_SUCCESS_MESSAGE);
			poList.forEach((po) -> {
				Order ord = new Order();
				ord.setOrderBy(po.getEmail());
				ord.setOrderId(po.getOrderId());
				ord.setOrderStatus(po.getOrderStatus());
				ord.setProducts(cartRepository.findAllByOrderId(po.getOrderId()));
				orderList.add(ord);
			});
			resp.setOrderlist(orderList);
		} catch (Exception e) {
			throw new OrderCustomException("Unable to retrieve place order list, please try again");
		}
		return resp;
	}

	public ServerResponse updateOrders(String orderId,
			String orderStatus) throws IOException {

		ServerResponse resp = new ServerResponse();
		if (Validator.isStringEmpty(orderId) || Validator.isStringEmpty(orderStatus)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
			return resp;
		} else {
			try {
				PlaceOrder pc = ordRepository.findByOrderId(Integer.parseInt(orderId))
						.orElseThrow(() -> new OrderCustomException("Unable to retrieve orderes, please try again"));
				pc.setOrderStatus(orderStatus);
				pc.setOrderDate(new java.util.Date(System.currentTimeMillis()));
				ordRepository.save(pc);
				User user = new User();
				user.setEmail(pc.getEmail());
				mailService.sendNotificationToUser(pc.getEmail(), orderStatus);
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.UPD_ORD_SUCCESS_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resp;
	}
}
