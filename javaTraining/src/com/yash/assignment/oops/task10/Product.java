package com.yash.assignment.oops.task10;

public class Product implements Cloneable{
    private int pid;
    private String pname;
    private double price;
    private String unitOfMeasurement;
    
	public Product(int pid, String pname, double price, String unitOfMeasurement) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.unitOfMeasurement = unitOfMeasurement;
	}
    
	
    public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getPname() {
		return pname;
	}


	public void setPname(String pname) {
		this.pname = pname;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}


	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}


	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price + ", unitOfMeasurement="
				+ unitOfMeasurement + "]";
	}
	
}
