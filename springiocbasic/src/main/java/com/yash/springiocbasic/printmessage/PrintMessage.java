package com.yash.springiocbasic.printmessage;

public class PrintMessage {
    private String message;

	public PrintMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrintMessage(String message) {
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
		return "PrintMessage [message=" + message + "]";
	}
    
} 
