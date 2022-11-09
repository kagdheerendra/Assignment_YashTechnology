package com.yash.ecommerce.entity;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * this entity class will hold the product information.
 * @author dheerendra.kag
 *
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {

	private static final long serialVersionUID = -7446162716367847201L;

	/**
	 * this will hold the current productId of this product.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	/**
	 * this will hold the current product description of this product.
	 */
	private String description;
	/**
	 * this will hold the current productName of this product.
	 */
	private String productName;
	/**
	 * this will hold the current price of this product.
	 */
	private double price;
	/**
	 * this will hold the current quantity of this product.
	 */
	private int quantity;

	/**
	 * this will hold the current image of this prodcut.
	 */
	@Lob
	private byte[] productimage;

	/**
	 * this will construct the default product.
	 */
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * this construct a product with specified parameter
	 * @param productId the unique id of the product.
	 * @param description the description of the product.
	 * @param productName the name of the product.
	 * @param price the price of the product.
	 * @param quantity the quatity of the product.
	 * @param productimage the image of the product.
	 */
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

	/**
	 * this will return the current id of this product.
	 * @return this product unique id.
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * this will set the id of this product.
	 * @param productId id of the product.
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * this will return the current description of this product.
	 * @return this product description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * this will set the description of this product.
	 * @param description description of the product.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * this will return the current name of this product.
	 * @return name of the product.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * this will set the current name of this product.
	 * @param productName name of the product.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * this will return the current price of this product.
	 * @return price of the product.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * set the price of this product.
	 * @param price price of the product.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * this will return the current quantity of this product.
	 * @return quantity of the product.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * set the quantity of this product.
	 * @param quantity of the product.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * this will return the current image of this product.
	 * @return image of the product.
	 */
	public byte[] getProductimage() {
		return productimage;
	}

	/**
	 * set the image of this product.
	 * @param productimage of the product.
	 */
	public void setProductimage(byte[] productimage) {
		this.productimage = productimage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * this will print the all information of this product.
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", description=" + description + ", productName=" + productName
				+ ", price=" + price + ", quantity=" + quantity + ", productimage=" + Arrays.toString(productimage)
				+ "]";
	}

}
