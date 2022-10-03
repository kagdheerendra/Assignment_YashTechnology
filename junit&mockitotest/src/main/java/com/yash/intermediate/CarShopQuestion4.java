package com.yash.intermediate;

import java.util.List;

class Car{
	private int speed;
	private String carOwner;
	private String carType;
	private boolean isSold;
	public Car(int speed, String carOwner, String carType, boolean isSold) {
		super();
		this.speed = speed;
		this.carOwner = carOwner;
		this.carType = carType;
		this.isSold = isSold;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public boolean isSold() {
		return isSold;
	}
	public void setSold(boolean isSold) {
		this.isSold = isSold;
	}
	@Override
	public String toString() {
		return "Car [speed=" + speed + ", carOwner=" + carOwner + ", carType=" + carType + ", isSold=" + isSold + "]";
	}

}


public class CarShopQuestion4 {
    public long numberOfCarsSoldout(List<Car> list) {
    	int count = 0;
    	for(Car c: list) {
    		if(c.isSold()) count++;
    	}
    	return count;
    }
}
