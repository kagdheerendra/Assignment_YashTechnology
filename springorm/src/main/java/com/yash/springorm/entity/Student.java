package com.yash.springorm.entity;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class Student {
	@Id
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private double marks;
	@Column
	private String branch;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, double marks, String branch) {
		super();
		this.id = id;
		this.name = name;
		this.marks = marks;
		this.branch = branch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", marks=" + marks + ", branch=" + branch + "]";
	}

}
