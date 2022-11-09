package com.yash.ecommerce.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * this will responsible to hold the order for approval. 
 * @author dheerendra.kag
 *
 */
@Entity
@Table(name = "placeOrder")
public class PlaceOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String email;
	/**
	 * this will hold the current orderStatus of the order.
	 */
	private String orderStatus;
	/**
	 * this will hold the orderDate.
	 */
	private Date orderDate;
	/**
	 * this will hold the totalCost for the order.
	 */
	private double totalCost;

	public PlaceOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaceOrder(int orderId, String email, String orderStatus, Date orderDate, double totalCost) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.totalCost = totalCost;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "PlaceOrder [orderId=" + orderId + ", email=" + email + ", orderStatus=" + orderStatus + ", orderDate="
				+ orderDate + ", totalCost=" + totalCost + "]";
	}

}
