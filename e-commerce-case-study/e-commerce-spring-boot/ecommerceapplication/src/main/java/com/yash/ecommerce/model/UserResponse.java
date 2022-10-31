package com.yash.ecommerce.model;

import java.io.Serializable;

import com.yash.ecommerce.entity.Address;
import com.yash.ecommerce.entity.User;

public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String message;
	private String authToken;
	private User user;
	private Address address;

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponse(String status, String message, String authToken, User user, Address address) {
		super();
		this.status = status;
		this.message = message;
		this.authToken = authToken;
		this.user = user;
		this.address = address;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserResponse [status=" + status + ", message=" + message + ", authToken=" + authToken + ", user=" + user
				+ ", address=" + address + "]";
	}

}
