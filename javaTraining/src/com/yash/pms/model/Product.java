package com.yash.pms.model;

public class Product {
    private int id;
    private String pName;
    private double price;
	public Product(int id, String pName, double price) {
		super();
		this.id = id;
		this.pName = pName;
		this.price = price;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", pName=" + pName + ", price=" + price + "]";
	}
    
    
}
