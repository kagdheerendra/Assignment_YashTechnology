package com.yash.assignment.collections;

public class Student {
	private int rollNo;
	private String name;
	private String className;
	private int totalMarks;

	public Student(int rollNo, String name, String className, int totalMarks) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.className = className;
		this.totalMarks = totalMarks;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", className=" + className + ", totalMarks="
				+ totalMarks + "]";
	}

}
