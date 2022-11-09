package com.yash.ecommerce.model;

import java.util.List;

import com.yash.ecommerce.entity.Product;

/**
 * hold the current product response.
 * @author dheerendra.kag
 *
 */
public class ProductResponse {

	private String status;
	private String message;
	private String authToken;
	private List<Product> oblist;

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

	public List<Product> getOblist() {
		return oblist;
	}

	public void setOblist(List<Product> oblist) {
		this.oblist = oblist;
	}
}
