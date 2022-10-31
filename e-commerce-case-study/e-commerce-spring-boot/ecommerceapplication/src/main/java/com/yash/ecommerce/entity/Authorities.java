package com.yash.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

	private static final long serialVersionUID = 6005072159059903199L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int authorityId;
	private String userName;
	private String authority;

	@ManyToOne
	@JoinColumn(name = "userName", referencedColumnName = "userName", insertable = false, updatable = false)
	private User user;

	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authorities(int authorityId, String userName, String authority, User user) {
		super();
		this.authorityId = authorityId;
		this.userName = userName;
		this.authority = authority;
		this.user = user;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
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
		return "Authorities [authorityId=" + authorityId + ", userName=" + userName + ", authority=" + authority
				+ ", user=" + user + "]";
	}

}
