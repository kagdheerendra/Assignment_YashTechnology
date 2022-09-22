package com.yash.assignment.oops.task11;

import java.util.Date;

/**
 * WAP to print the object of Employee class with the help of toString method. 
Employee class fields :- empid, empname, empsalary, empaddress, emp_dob, 
emp_doj. use Date class to store the date of birth(dob) and date of joining(doj). 
 * @author dheerendra.kag
 *
 */
public class Main {
   public static void main(String args[]) {
	   Employee e = new Employee();
	   e.setEmpid(0);
	   e.setEmpname("dk");
	   e.setEmpsalary(2000);
	   e.setEmpaddress("indore");
	   e.setEmp_dob(new Date("04/04/1994"));
	   e.setEmp_doj(new Date("30/08/2022"));
	   
	   System.out.println(e.toString());
   }
}
