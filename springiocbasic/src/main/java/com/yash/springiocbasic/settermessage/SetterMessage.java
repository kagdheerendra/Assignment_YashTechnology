package com.yash.springiocbasic.settermessage;

public class SetterMessage {
	private String message = null;

	public SetterMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SetterMessage(String message) {
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
		return "SetterMessage [message=" + message + "]";
	}

}
