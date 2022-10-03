package com.yash.intermediate;

import java.util.List;

class Book {
	private String name;
	private double price;
	private String type;
    private boolean isWritten;
	public Book(String name, double price, String type, boolean isWritten) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
		this.isWritten = isWritten;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isWritten() {
		return isWritten;
	}
	public void setWritten(boolean isWritten) {
		this.isWritten = isWritten;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", type=" + type + ", isWritten=" + isWritten + "]";
	}
    
}

public class AuthorQuestion6 {
    public long numberOfBookWritten(List<Book> list) {
    	int count = 0;
    	for(Book s: list) {
    		if(s.isWritten()) count++;
    	}
    	return count;
    }
}
