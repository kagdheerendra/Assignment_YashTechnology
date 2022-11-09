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

/**
 * this entity class is responsible to hold user information.
 * @author dheerendra.kag
 *
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -8850740904859933967L;

	/**
	 * this will hold the current userId of this user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId; 
	/**
	 * this will hold the current email of this user.
	 */
	private String email;
	/**
	 * this will hold the current userName of this user.
	 */
	private String userName;
	/**
	 * this will hold the current password of this user.
	 */
	private String password;
	/**
	 * this will hold the current userType of this user.
	 */
	private String userType;

	/**
	 * this will hold the current address information of this user. 
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	@JsonManagedReference
	private Address address;

	/**
	 * this will hold the current roles of this user.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Authorities> roles;

	/**
	 * this will constructs the default user.
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * this constructs a user with specified parameter
	 * @param userId  the unique primary key of the user 
	 * @param email   the email of the user
	 * @param userName the userName of the user 
	 * @param password the password of the user
	 * @param userType the type to user
	 * @param address the address of the user
	 * @param roles the authority of the user
	 */
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

	/**
	 * this will return the current id of this user
	 * @return this user's userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * this will set the id of the user
	 * @param userId unique id of the user
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * this will return the current email of this user
	 * @return this user's email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * this will set the email to this user
	 * @param email email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * this will return the current userName of this user
	 * @return this user's userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * this will set the userName of this user
	 * @param userName name of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * this will return the current password of this user
	 * @return this user's password;
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * this will set the password of this user
	 * @param password password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * this will return the type of this user
	 * @return this user's userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * this will set the type of this user
	 * @param userType type of the user
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * this will return the current address of this user
	 * @return address of this user's
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * this will set the address to this user
	 * @param address address of this user
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * this will return the all roles of this user
	 * @return role of this user's
	 */
	public List<Authorities> getRoles() {
		return roles;
	}

	/**
	 * this will set the roles to this user
	 * @param roles roles of this user's
	 */
	public void setRoles(List<Authorities> roles) {
		this.roles = roles;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * this will print all information of this user's
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", userName=" + userName + ", password=" + password
				+ ", userType=" + userType + ", address=" + address + ", roles=" + roles + "]";
	}

}
