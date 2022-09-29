package com.yash.assignment.java8;

import java.time.LocalDateTime;

public class Student {
	private int rollNo;
	private LocalDateTime doa;
	private LocalDateTime dob;
	private String maths;
	private String physics;
	private String chemistry;
	private String english;
	private String hindi;
	private String className;

	public Student(int rollNo, LocalDateTime doa, LocalDateTime dob, String maths, String physics, String chemistry,
			String english, String hindi, String className) {
		super();
		this.rollNo = rollNo;
		this.doa = doa;
		this.dob = dob;
		this.maths = maths;
		this.physics = physics;
		this.chemistry = chemistry;
		this.english = english;
		this.hindi = hindi;
		this.className = className;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public LocalDateTime getDoa() {
		return doa;
	}

	public void setDoa(LocalDateTime doa) {
		this.doa = doa;
	}

	public LocalDateTime getDob() {
		return dob;
	}

	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}

	public String getMaths() {
		return maths;
	}

	public void setMaths(String maths) {
		this.maths = maths;
	}

	public String getPhysics() {
		return physics;
	}

	public void setPhysics(String physics) {
		this.physics = physics;
	}

	public String getChemistry() {
		return chemistry;
	}

	public void setChemistry(String chemistry) {
		this.chemistry = chemistry;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getHindi() {
		return hindi;
	}

	public void setHindi(String hindi) {
		this.hindi = hindi;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", doa=" + doa + ", dob=" + dob + ", maths=" + maths + ", physics="
				+ physics + ", chemistry=" + chemistry + ", english=" + english + ", hindi=" + hindi + ", className="
				+ className + "]";
	}

}
