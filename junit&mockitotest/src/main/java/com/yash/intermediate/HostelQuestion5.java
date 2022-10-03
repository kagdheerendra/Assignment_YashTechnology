package com.yash.intermediate;

import java.util.List;

class Student{
	private int id;
	private String name;
	private String className;
	private boolean isRegistered;
	public Student(int id, String name, String className, boolean isRegistered) {
		super();
		this.id = id;
		this.name = name;
		this.className = className;
		this.isRegistered = isRegistered;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public boolean isRegistered() {
		return isRegistered;
	}
	public void setRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", className=" + className + ", isRegistered=" + isRegistered
				+ "]";
	}
	
}
public class HostelQuestion5 {
    public long numberOfStudnetRegistered(List<Student> list) {
    	int count = 0;
    	for(Student s: list) {
    		if(s.isRegistered()) count++;
    	}
    	return count;
    }
}
