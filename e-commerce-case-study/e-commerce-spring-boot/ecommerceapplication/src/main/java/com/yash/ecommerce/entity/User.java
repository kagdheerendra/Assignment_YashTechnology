package com.yash.ecommerce.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -8850740904859933967L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String email;
	private String userName;
	private String password;
	private String userType;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	@JsonManagedReference
	private Address address;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Authorities> roles;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String email, String userName, String password, String userType, Address address,
			List<Authorities> roles) {
		super();
		this.userId = userId;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
		this.address = address;
		this.roles = roles;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Authorities> getRoles() {
		return roles;
	}

	public void setRoles(List<Authorities> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", userType=" + userType + ", address=" + address + ", roles=" + roles + "]";
	}

}
