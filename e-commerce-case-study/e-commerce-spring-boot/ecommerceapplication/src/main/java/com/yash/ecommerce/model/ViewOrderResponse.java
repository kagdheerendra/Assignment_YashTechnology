package com.yash.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

/**
 * hold the order response.
 * @author dheerendra.kag
 *
 */
public class ViewOrderResponse {
	private String status;
	private String message;
	private String authToken;
	private List<Order> orderlist = new ArrayList<>();
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public List<Order> getOrderlist() {
		return orderlist;
	}
	public void setOrderlist(List<Order> orderlist) {
		this.orderlist = orderlist;
	}
	
	
}
