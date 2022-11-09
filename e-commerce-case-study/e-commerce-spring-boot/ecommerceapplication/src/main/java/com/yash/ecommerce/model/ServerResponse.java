package com.yash.ecommerce.model;

/**
 * hold the generated server response.
 * @author dheerendra.kag
 *
 */
public class ServerResponse {
	private String status;
	private String message;
	private String authToken;
	private String userType;

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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
