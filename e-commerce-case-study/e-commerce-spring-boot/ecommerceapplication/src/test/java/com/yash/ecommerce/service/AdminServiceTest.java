package com.yash.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yash.ecommerce.entity.Bufcart;
import com.yash.ecommerce.entity.Product;
import com.yash.ecommerce.model.ProductResponse;
import com.yash.ecommerce.repository.CartRepository;
import com.yash.ecommerce.repository.OrderRepository;
import com.yash.ecommerce.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

	@InjectMocks
	AdminService adminService;
	
	@Mock
	private ProductRepository prodRepository;

	@Mock
	private OrderRepository ordRepository;

	@Mock
	private CartRepository cartRepository;
	
	@SuppressWarnings("deprecation")
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deleteProTest() {
		
		doNothing().when(prodRepository).deleteByProductId(1);
		ProductResponse res = null;
		try {
            res = adminService.delProduct("1");
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("200", res.getStatus());
	}
	
	@Test
	public void addProductTest() {
		Product buf = Mockito.mock(Product.class);
		when(prodRepository.save(any())).thenReturn(buf);
		when(prodRepository.findAll()).thenReturn(List.of(buf));
		ProductResponse res = null;
		try {
            res = adminService.addProduct(null, "mobile", "mobile", "23", "12000");
		} catch (Exception e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("200", res.getStatus());
	}
}
