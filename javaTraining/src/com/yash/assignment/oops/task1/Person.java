package com.yash.assignment.oops.task1;

public class Person {
	private int Pid;
	private String pname;
	private String paddress;
	private String dob;

	
	public Person(int pid, String pname, String paddress, String dob) {
		super();
		Pid = pid;
		this.pname = pname;
		this.paddress = paddress;
		this.dob = dob;
	}

	public int getPid() {
		return Pid;
	}

	public void setPid(int pid) {
		Pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Person [Pid=" + Pid + ", pname=" + pname + ", paddress=" + paddress + ", dob=" + dob + ", getPid()="
				+ getPid() + ", getPname()=" + getPname() + ", getPaddress()=" + getPaddress() + ", getDob()="
				+ getDob() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
}
