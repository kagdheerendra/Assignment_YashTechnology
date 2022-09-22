package com.yash.assignment.oops.task1;

import java.util.*;

public class Main {
    public static void main(String args[]) {
        Employee e = new Employee(1,"dheeren","indore","4/4/94","30/8/94","manawar","xyz",83479,"dheeren.kag@yash.com");
        Customer c = new Customer(1,"dheeren","indore","4/4/94","30/8/22","2/2/22",9981214148L,"dheerendra.kag@gmail.com");
        ArrayList<Department> lDept = new ArrayList<>();
        lDept.add(new Department(1,"Hr"));
        lDept.add(new Department(2,"It"));
        System.out.println("Employee details is= "+e.toString());
        System.out.println("Customer details is= "+c.toString());
    }
}
