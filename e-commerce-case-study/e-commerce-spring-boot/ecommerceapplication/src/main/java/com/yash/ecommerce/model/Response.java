package com.yash.ecommerce.model;

import java.io.Serializable;
import java.util.HashMap;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String message;
	private String authToken;
	private HashMap<String, String> map;

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response(String status, String message, String authToken, HashMap<String, String> map) {
		super();
		this.status = status;
		this.message = message;
		this.authToken = authToken;
		this.map = map;
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

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", authToken=" + authToken + ", map=" + map
				+ "]";
	}

}
