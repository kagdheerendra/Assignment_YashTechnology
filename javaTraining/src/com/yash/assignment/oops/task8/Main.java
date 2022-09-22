package com.yash.assignment.oops.task8;

import java.util.Date;

class Electronics{
	private int id;
	private String semiconductorType;
	private Date dateOfManufacturing;
	
	public Electronics(int id, String semiconductorType, Date dateOfManufacturing) {
		this.id = id;
		this.semiconductorType = semiconductorType;
		this.dateOfManufacturing = dateOfManufacturing;
	}
	
	public void showDetails() {
		System.out.println(id+"::"+semiconductorType+"::"+dateOfManufacturing);
	}
}

class Mobile extends Electronics{
	public Mobile(int id, String semiconductorType, Date dateOfManufacturing) {
		super(id, semiconductorType, dateOfManufacturing);
	}
}

class LCD extends Electronics{
	public LCD(int id, String semiconductorType, Date dateOfManufacturing) {
		super(id, semiconductorType, dateOfManufacturing);
	}
}

class Laptop extends Electronics{
	public Laptop(int id, String semiconductorType, Date dateOfManufacturing) {
		super(id, semiconductorType, dateOfManufacturing);
	}
}

public class Main {
    public static void main(String args[]) {
    	Electronics eMob = new Mobile(1,"semi1",new Date("01/01/21"));
    	eMob.showDetails();
    	Electronics eLcd = new LCD(2,"semi2",new Date("02/02/22"));
    	eLcd.showDetails();
    	Electronics eLaptop = new Laptop(3,"semi3",new Date("03/03/23"));
    	eLaptop.showDetails();
    }
}
