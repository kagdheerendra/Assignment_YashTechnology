package com.yash.springiocbasic.employee;

public class Employee {
	private String empId;
	private String empName;
	private String empRole;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String empId, String empName, String empRole) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empRole = empRole;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empRole=" + empRole + "]";
	}

}
