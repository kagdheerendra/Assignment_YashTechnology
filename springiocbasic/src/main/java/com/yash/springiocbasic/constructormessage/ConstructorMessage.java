package com.yash.springiocbasic.constructormessage;

public class ConstructorMessage {
	private String message = null;

	public ConstructorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConstructorMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ConstructorMessage [message=" + message + "]";
	}

}
