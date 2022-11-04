package com.yash.ecommerce.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "bufcart")
public class Bufcart implements Serializable {

	private static final long serialVersionUID = 4049687597028261161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bufcartId;
	@Column(name = "order_id", nullable = true)
	private int orderId;
	private String email;
	private Date dateAdded;
	private int quantity;
	private double price;
	private int productId;
	private String productName;
	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean accessByCart = true;

	public Bufcart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bufcart(int bufcartId, int orderId, String email, Date dateAdded, int quantity, double price, int productId,
			String productName, boolean accessByCart) {
		super();
		this.bufcartId = bufcartId;
		this.orderId = orderId;
		this.email = email;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.price = price;
		this.productId = productId;
		this.productName = productName;
		this.accessByCart = accessByCart;
	}

	public int getBufcartId() {
		return bufcartId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	public boolean isAccessByCart() {
		return accessByCart;
	}

	public void setAccessByCart(boolean accessByCart) {
		this.accessByCart = accessByCart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Bufcart [bufcartId=" + bufcartId + ", orderId=" + orderId + ", email=" + email + ", dateAdded="
				+ dateAdded + ", quantity=" + quantity + ", price=" + price + ", productId=" + productId
				+ ", productName=" + productName + ", accessByCart=" + accessByCart + "]";
	}

}
