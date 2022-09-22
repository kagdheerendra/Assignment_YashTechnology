package com.yash.assignment.oops.task1;

public class Employee extends Person{
    private String date_of_joining;
    private String base_location;
    private String deptobj;
    private int contactno;
    private String emailid;

	public Employee(int pid, String pname, String paddress, String dob, String date_of_joining, String base_location,
			String deptobj, int contactno, String emailid) {
		super(pid, pname, paddress, dob);
		this.date_of_joining = date_of_joining;
		this.base_location = base_location;
		this.deptobj = deptobj;
		this.contactno = contactno;
		this.emailid = emailid;
	}
	
	public String getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(String date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public String getBase_location() {
		return base_location;
	}
	public void setBase_location(String base_location) {
		this.base_location = base_location;
	}
	public String getDeptobj() {
		return deptobj;
	}
	public void setDeptobj(String deptobj) {
		this.deptobj = deptobj;
	}
	public int getContactno() {
		return contactno;
	}
	public void setContactno(int contactno) {
		this.contactno = contactno;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	@Override
	public String toString() {
		return "Employee [date_of_joining=" + date_of_joining + ", base_location=" + base_location + ", deptobj="
				+ deptobj + ", contactno=" + contactno + ", emailid=" + emailid + ", getDate_of_joining()="
				+ getDate_of_joining() + ", getBase_location()=" + getBase_location() + ", getDeptobj()=" + getDeptobj()
				+ ", getContactno()=" + getContactno() + ", getEmailid()=" + getEmailid() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
    
    
}
