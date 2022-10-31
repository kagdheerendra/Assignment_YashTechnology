package com.yash.ecommerce.model;

import java.util.List;

import com.yash.ecommerce.entity.Bufcart;

public class CartResponse {
	private String status;
	private String message;
	private String authToken;
	private List<Bufcart> oblist;

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

	public List<Bufcart> getOblist() {
		return oblist;
	}

	public void setOblist(List<Bufcart> oblist) {
		this.oblist = oblist;
	}

}
