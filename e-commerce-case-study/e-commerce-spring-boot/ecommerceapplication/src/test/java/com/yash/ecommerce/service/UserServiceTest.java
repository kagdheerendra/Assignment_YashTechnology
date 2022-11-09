package com.yash.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.Buffer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yash.ecommerce.controller.HomeController;
import com.yash.ecommerce.entity.Authorities;
import com.yash.ecommerce.entity.Bufcart;
import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.entity.User;
import com.yash.ecommerce.exception.ProductCustomException;
import com.yash.ecommerce.exception.UserCustomException;
import com.yash.ecommerce.model.CartResponse;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.model.ServerResponse;
import com.yash.ecommerce.repository.AddressRepository;
import com.yash.ecommerce.repository.CartRepository;
import com.yash.ecommerce.repository.OrderRepository;
import com.yash.ecommerce.repository.ProductRepository;
import com.yash.ecommerce.repository.UserRepository;
import com.yash.ecommerce.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;
	
	@Mock
	private ProductRepository prodRepository;

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private CartRepository cartRepository;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private AddressRepository addressRepository;
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProducts() {
		Product p = new Product();
		p.setProductName("mobile");
		p.setDescription("mobile");
		p.setQuantity(5);
		p.setPrice(12000);
		p.setProductimage(null);
		
		when(prodRepository.findAll()).thenReturn(List.of(p));
		
		ProductResponse resp = null;
		try {
			 resp = userService.getProducts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProductCustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("200", resp.getStatus());
	}
	
	@Test
	public void addToCartTest() {
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
		
		Authentication auth = new Authentication() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isAuthenticated() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getDetails() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getCredentials() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		when(userRepository.findByUserName(auth.getName())).thenReturn(Optional.of(user));
		Product p = new Product();
		p.setProductName("mobile");
		p.setDescription("mobile");
		p.setQuantity(5);
		p.setPrice(12000);
		p.setProductimage(null);
		when(prodRepository.findByProductId(1)).thenReturn(Optional.of(p));
		Bufcart buf1 = new Bufcart();
		buf1.setEmail("akku@gmail.com");
		buf1.setQuantity(1);
		buf1.setPrice(12000);
		buf1.setProductId(1);
		buf1.setProductName("mobile");
		Date date1 = new Date();
		buf1.setDateAdded(date1);
		
		when(cartRepository.save(any())).thenReturn(buf1);
		
		ServerResponse res = null;
		try {
			 res = userService.addToCart("1",auth);
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("200", res.getStatus());
	}
	
	@Test
	public void updateCartTest() {
          Authentication auth = new Authentication() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isAuthenticated() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object getPrincipal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getDetails() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getCredentials() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				return null;
			}
		};

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
		
		  when(userRepository.findByUserName(auth.getName())).thenReturn(Optional.of(user));
		    Bufcart buf = Mockito.mock(Bufcart.class);
			Bufcart buf1 = new Bufcart();
			buf1.setEmail("akku@gmail.com");
			buf1.setQuantity(1);
			buf1.setPrice(12000);
			buf1.setProductId(1);
			buf1.setProductName("mobile");
			Date date1 = new Date();
			buf1.setDateAdded(date1);
		
		  Optional<Bufcart> o = Optional.of(buf);
		  when(cartRepository.findByBufcartIdAndEmail(anyInt(), anyString())).thenReturn(o);
		  when(cartRepository.save(any(Bufcart.class))).thenReturn(buf1);
		  when(cartRepository.findByEmail(anyString())).thenReturn(List.of(buf1));
		  
			CartResponse res = null;
			try {
				HashMap<String, String> cart = new HashMap<>();
				cart.put("id", "1");
				cart.put("quantity", "12");
				 res = userService.updateCart(cart,auth);
			} catch (Exception e) {	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertEquals("200", res.getStatus());
	}
}
