package com.yash.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bufcart")
public class Bufcart implements Serializable {

	private static final long serialVersionUID = 4049687597028261161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bufcartId;
	private String email;
	private Date dateAdded;
	private int quantity;
	private double price;
	private int productId;
	private String productName;

	public Bufcart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bufcart(int bufcartId, String email, Date dateAdded, int quantity, double price, int productId,
			String productName) {
		super();
		this.bufcartId = bufcartId;
		this.email = email;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.price = price;
		this.productId = productId;
		this.productName = productName;
	}

	public int getBufcartId() {
		return bufcartId;
	}

	public void setBufcartId(int bufcartId) {
		this.bufcartId = bufcartId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Bufcart [bufcartId=" + bufcartId + ", email=" + email + ", dateAdded=" + dateAdded + ", quantity="
				+ quantity + ", price=" + price + ", productId=" + productId + ", productName=" + productName + "]";
	}

}
