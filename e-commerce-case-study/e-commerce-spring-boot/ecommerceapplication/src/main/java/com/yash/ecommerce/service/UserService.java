package com.yash.ecommerce.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yash.ecommerce.model.Response;
import com.yash.ecommerce.controller.UserController;
import com.yash.ecommerce.entity.Address;
import com.yash.ecommerce.entity.Bufcart;
import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.entity.PlaceOrder;
import com.yash.ecommerce.exception.AddressCustomException;
import com.yash.ecommerce.exception.CartCustomException;
import com.yash.ecommerce.exception.PlaceOrderCustomException;
import com.yash.ecommerce.exception.ProductCustomException;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.CartResponse;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.model.UserResponse;
import com.yash.ecommerce.repository.AddressRepository;
import com.yash.ecommerce.repository.CartRepository;
import com.yash.ecommerce.repository.OrderRepository;
import com.yash.ecommerce.repository.ProductRepository;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.ConstantProperties;
import com.yash.ecommerce.util.Validator;

@Service
public class UserService {

	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
	private ProductRepository prodRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public ProductResponse getProducts() throws IOException, ProductCustomException {
		logger.debug("inside getproduct method of userservice");
		ProductResponse resp = new ProductResponse();
		try {
			List<Product> products = prodRepository.findAll();
			logger.debug("product size is {}", products.size());
			resp.setStatus(ConstantProperties.SUCCESS_CODE);
			resp.setMessage(ConstantProperties.LIST_SUCCESS_MESSAGE);	
			resp.setOblist(products);
		} catch (Exception e) {
			throw new ProductCustomException("Unable to retrieve products, please try again");
		}
		return resp;
	}
	
	public ServerResponse addToCart(String productId,
			Authentication auth) throws IOException, CartCustomException {

		ServerResponse resp = new ServerResponse();
		try {
			User loggedUser = userRepository.findByUserName(auth.getName())
					.orElseThrow(() -> new UserCustomException(auth.getName()));
			Product cartItem = prodRepository.findByProductId(Integer.parseInt(productId))
					.orElseThrow(()-> new ProductCustomException("Unable to find product details, please try again"));

			Bufcart buf = new Bufcart();
			buf.setEmail(loggedUser.getEmail());
			buf.setQuantity(1);
			buf.setPrice(cartItem.getPrice());
			buf.setProductId(Integer.parseInt(productId));
			buf.setProductName(cartItem.getProductName());
			Date date = new Date();
			buf.setDateAdded(date);

			System.out.println(buf);
			Bufcart bufcart = cartRepository.save(buf);
            if(bufcart != null) {
    			resp.setStatus(ConstantProperties.SUCCESS_CODE);
    			resp.setMessage(ConstantProperties.CART_UPD_MESSAGE_CODE);            	
            }else {
            	throw new CartCustomException("Unable to add product to cart, please try again");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public CartResponse viewCart(Authentication auth) throws IOException, CartCustomException {
		CartResponse resp = new CartResponse();
		try {
			User loggedUser = userRepository.findByUserName(auth.getName())
					.orElseThrow(() -> new UserCustomException(auth.getName()));

			List<Bufcart> list = cartRepository.findByEmail(loggedUser.getEmail());
			resp.setStatus(ConstantProperties.SUCCESS_CODE);
			resp.setMessage(ConstantProperties.VW_CART_MESSAGE);
			resp.setOblist(list);
//			if(list.size()>0) {
//				resp.setStatus(ConstantProperties.SUCCESS_CODE);
//				resp.setMessage(ConstantProperties.VW_CART_MESSAGE);
//				resp.setOblist(cartRepository.findByEmail(loggedUser.getEmail()));
//			}else {
//				resp.setStatus(ConstantProperties.FAILURE_CODE);
//				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
//				throw new CartCustomException("Unable to retrieve cart items, please try again");
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CartCustomException("Unable to retrieve cart items, please try again");
		}

		return resp;
	}
	
	public CartResponse updateCart(@RequestBody HashMap<String, String> cart, Authentication auth)
			throws IOException {

		CartResponse resp = new CartResponse();
		try {
			User loggedUser = userRepository.findByUserName(auth.getName())
					.orElseThrow(() -> new UserCustomException(auth.getName()));
			Bufcart selCart = cartRepository.findByBufcartIdAndEmail(Integer.parseInt(cart.get("id")), loggedUser.getEmail())
					.orElseThrow(() -> new CartCustomException("Unable to find cart items, please try again"));
			selCart.setQuantity(Integer.parseInt(cart.get("quantity")));
			cartRepository.save(selCart);
			List<Bufcart> bufcartlist = cartRepository.findByEmail(loggedUser.getEmail());
			if(bufcartlist.size()>0) {
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.UPD_CART_MESSAGE);
				resp.setOblist(bufcartlist);				
			}else {
				resp.setStatus(ConstantProperties.FAILURE_CODE);
				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
				throw new CartCustomException("Unable to update cart items, please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public CartResponse delCart(@RequestParam(name = ConstantProperties.BUF_ID) String bufcartid,
			Authentication auth) throws IOException, CartCustomException {

		CartResponse resp = new CartResponse();
		try {
			User loggedUser = userRepository.findByUserName(auth.getName())
					.orElseThrow(() -> new UserCustomException(auth.getName()));
			cartRepository.deleteByBufcartIdAndEmail(Integer.parseInt(bufcartid), loggedUser.getEmail());
			List<Bufcart> bufcartlist = cartRepository.findByEmail(loggedUser.getEmail());
			resp.setStatus(ConstantProperties.SUCCESS_CODE);
			resp.setMessage(ConstantProperties.DEL_CART_SUCCESS_MESSAGE);
			resp.setOblist(bufcartlist);
//			if(bufcartlist.size()>0) {
//				resp.setStatus(ConstantProperties.SUCCESS_CODE);
//				resp.setMessage(ConstantProperties.DEL_CART_SUCCESS_MESSAGE);
//				resp.setOblist(bufcartlist);				
//			}else {
//				resp.setStatus(ConstantProperties.FAILURE_CODE);
//				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
//				throw new CartCustomException("Unable to delete cart items, please try again");
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CartCustomException("Unable to delete cart items, please try again");
		}
		return resp;
	}
	
	public Response getAddress(Authentication auth) {
		Response resp = new Response();
		try {
			User user = userRepository.findByUserName(auth.getName()).orElseThrow(
					() -> new UserCustomException("User with username " + auth.getName() + " doesn't exists"));
			Optional<Address> adr = addressRepository.findByUser(user);
			if(adr.isPresent()) {
				HashMap<String, String> map = new HashMap<>();
				map.put(ConstantProperties.ADR_NAME, adr.get().getAddress());
				map.put(ConstantProperties.ADR_CITY, adr.get().getCity());
				map.put(ConstantProperties.ADR_STATE, adr.get().getState());
				map.put(ConstantProperties.ADR_COUNTRY, adr.get().getCountry());
				map.put(ConstantProperties.ADR_ZP, String.valueOf(adr.get().getZipcode()));
				map.put(ConstantProperties.PHONE, adr.get().getPhonenumber());

				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.CUST_ADR_ADD);
				resp.setMap(map);				
			}else {
				resp.setStatus(ConstantProperties.FAILURE_CODE);
				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
				new AddressCustomException("Unable to retrieve address, please try again");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public UserResponse addAddress(@RequestBody Address address, Authentication auth) {
		UserResponse resp = new UserResponse();
		if (Validator.isAddressEmpty(address)) {
			resp.setStatus(ConstantProperties.BAD_REQUEST_CODE);
			resp.setMessage(ConstantProperties.BAD_REQUEST_MESSAGE);
			return resp;
		} else {
			try {
				User user = userRepository.findByUserName(auth.getName())
						.orElseThrow(() -> new UsernameNotFoundException(auth.getName()));
				Optional<Address> userAddress = addressRepository.findByUser(user);//.orElseThrow(() -> new AddressCustomException("Unable to find address, please try again"));
				if(userAddress.isPresent()) {
					userAddress.get().setAddress(address.getAddress());
					userAddress.get().setCity(address.getCity());
					userAddress.get().setCountry(address.getCountry());
					userAddress.get().setPhonenumber(address.getPhonenumber());
					userAddress.get().setState(address.getState());
					userAddress.get().setZipcode(address.getZipcode());
					Address a = addressRepository.save(userAddress.get());
					if(a != null) {
						resp.setAddress(a);
						//resp.setUser(user);
						resp.setStatus(ConstantProperties.SUCCESS_CODE);
						resp.setMessage(ConstantProperties.CUST_ADR_ADD);
					}else {
						resp.setStatus(ConstantProperties.FAILURE_CODE);
						resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
						throw new AddressCustomException("Unable to add address, please try again");
					}
				}else {
					user.setAddress(address);
					address.setUser(user);
					Address a = addressRepository.save(address);
					if(a != null) {
						resp.setAddress(a);
						//resp.setUser(user);
						resp.setStatus(ConstantProperties.SUCCESS_CODE);
						resp.setMessage(ConstantProperties.CUST_ADR_ADD);
					}else {
						resp.setStatus(ConstantProperties.FAILURE_CODE);
						resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
						throw new AddressCustomException("Unable to add address, please try again");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resp;
	}
	
	public ServerResponse placeOrder(Authentication auth) throws IOException {

		ServerResponse resp = new ServerResponse();
		try {
			User loggedUser = userRepository.findByUserName(auth.getName())
					.orElseThrow(() -> new UserCustomException(auth.getName()));
			PlaceOrder po = new PlaceOrder();
			po.setEmail(loggedUser.getEmail());
			Date date = new Date();
			po.setOrderDate(date);
			po.setOrderStatus(ConstantProperties.ORD_STATUS_CODE);
			double total = 0;
			List<Bufcart> buflist = cartRepository.findAllByEmail(loggedUser.getEmail());
			if(buflist.size()>0) {
				for (Bufcart buf : buflist) {
					total = +(buf.getQuantity() * buf.getPrice());
				}
				po.setTotalCost(total);
				PlaceOrder res = orderRepository.save(po);
				if(res != null) {
					buflist.forEach(bufcart -> {
					bufcart.setOrderId(res.getOrderId());
					cartRepository.save(bufcart);
	
				    });
					resp.setStatus(ConstantProperties.SUCCESS_CODE);
					resp.setMessage(ConstantProperties.ORD_SUCCESS_MESSAGE);					
				}else {
					resp.setStatus(ConstantProperties.FAILURE_CODE);
					resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
					throw new PlaceOrderCustomException("Unable to place order, please try again later");
				}
			}else {
				resp.setStatus(ConstantProperties.FAILURE_CODE);
				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
				throw new CartCustomException("Unable to find cart items, please try again");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	public ServerResponse buyNow(Product product,
			Authentication auth) throws IOException, CartCustomException {

		ServerResponse resp = new ServerResponse();
		try {
			User loggedUser = userRepository.findByUserName(auth.getName())
					.orElseThrow(() -> new UserCustomException(auth.getName()));

			Bufcart buf = new Bufcart();
			buf.setEmail(loggedUser.getEmail());
			buf.setQuantity(product.getQuantity());
			buf.setPrice(product.getPrice());
			buf.setProductId(product.getProductId());
			buf.setProductName(product.getProductName());
			Date date = new Date();
			buf.setDateAdded(date);
            buf.setAccessByCart(false);
			System.out.println(buf);
			Bufcart bufcart = cartRepository.save(buf);
			PlaceOrder po = new PlaceOrder();
			po.setEmail(loggedUser.getEmail());
			Date d = new Date();
			po.setOrderDate(d);
			po.setOrderStatus(ConstantProperties.ORD_STATUS_CODE);
			double total = bufcart.getQuantity() * bufcart.getPrice();
			po.setTotalCost(total);
			PlaceOrder res = orderRepository.save(po);
			if(res != null) {
				bufcart.setOrderId(res.getOrderId());
				cartRepository.save(bufcart);
				resp.setStatus(ConstantProperties.SUCCESS_CODE);
				resp.setMessage(ConstantProperties.ORD_SUCCESS_MESSAGE);					
			}else {
				resp.setStatus(ConstantProperties.FAILURE_CODE);
				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
				throw new PlaceOrderCustomException("Unable to place order, please try again later");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

//		ServerResponse resp = new ServerResponse();
//		try {
//			User loggedUser = userRepository.findByUserName(auth.getName())
//					.orElseThrow(() -> new UserCustomException(auth.getName()));
//			PlaceOrder po = new PlaceOrder();
//			po.setEmail(loggedUser.getEmail());
//			Date date = new Date();
//			po.setOrderDate(date);
//			po.setOrderStatus(ConstantProperties.ORD_STATUS_CODE);
//			double total = 0;
//			List<Bufcart> buflist = cartRepository.findAllByEmail(loggedUser.getEmail());
//			if(buflist.size()>0) {
//				for (Bufcart buf : buflist) {
//					total = +(buf.getQuantity() * buf.getPrice());
//				}
//				po.setTotalCost(total);
//				PlaceOrder res = orderRepository.save(po);
//				if(res != null) {
//					buflist.forEach(bufcart -> {
//					bufcart.setOrderId(res.getOrderId());
//					cartRepository.save(bufcart);
//	
//				});
//					resp.setStatus(ConstantProperties.SUCCESS_CODE);
//					resp.setMessage(ConstantProperties.ORD_SUCCESS_MESSAGE);					
//				}else {
//					resp.setStatus(ConstantProperties.FAILURE_CODE);
//					resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
//					throw new PlaceOrderCustomException("Unable to place order, please try again later");
//				}
//			}else {
//				resp.setStatus(ConstantProperties.FAILURE_CODE);
//				resp.setMessage(ConstantProperties.FAILURE_MESSAGE);
//				throw new CartCustomException("Unable to find cart items, please try again");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp;
//	}
}
