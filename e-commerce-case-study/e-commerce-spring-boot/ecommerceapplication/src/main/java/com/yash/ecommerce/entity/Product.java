package com.yash.ecommerce.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = -7446162716367847201L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String description;
	private String productName;
	private double price;
	private int quantity;

	@Lob
	private byte[] productimage;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String description, String productName, double price, int quantity,
			byte[] productimage) {
		super();
		this.productId = productId;
		this.description = description;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.productimage = productimage;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public byte[] getProductimage() {
		return productimage;
	}

	public void setProductimage(byte[] productimage) {
		this.productimage = productimage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", productName=" + productName
				+ ", price=" + price + ", quantity=" + quantity + ", productimage=" + Arrays.toString(productimage)
				+ "]";
	}

}
