package com.yash.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * this entity class will responsible to hold the address of the user.
 * @author dheerendra.kag
 *
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 4265352674204944987L;

	/**
	 * this will hold the current id of the address.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * this wiil hold the current address of the address.
	 */
	private String address;
	/**
	 * this will hold the current city of the address.	
	 */
	private String city;
	/**
	 * this will hold the current state of the address.
	 */
	private String state;
	/**
	 * this will hold the current country of the address.
	 */
	private String country;
	/**
	 * this will hold the current zipCode of the address.
	 */
	private int zipcode;
	/**
	 * this will hold the current phone number of the address of the user.
	 */
	private String phonenumber;

	/**
	 * this will hold the current user information of the address.
	 */
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userid", nullable = false)
	@JsonBackReference
	private User user;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * this construct the address with specified parameter.
	 * @param id id of the address.
	 * @param address address of the address. 
	 * @param city city of the address.
	 * @param state state of the address.
	 * @param country country of the address.
	 * @param zipcode zipCode of the address.
	 * @param phonenumber phoneNumber of the user.
	 * @param user current user of the address.
	 */
	public Address(Long id, String address, String city, String state, String country, int zipcode, String phonenumber,
			User user) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.phonenumber = phonenumber;
		this.user = user;
	}

	/**
	 * this will return the current id of this address
	 * @return this user's userId
	 */
	public Long getId() {
		return id;
	}

	/**
	 * this will set the id of the address
	 * @param userId unique id of the address
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state + ", country="
				+ country + ", zipcode=" + zipcode + ", phonenumber=" + phonenumber + ", user=" + user + "]";
	}

}
