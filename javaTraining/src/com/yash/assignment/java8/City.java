package com.yash.assignment.java8;

public class City {
    private int id;
    private String name;
    private State stateObj;
    private float pollutionIndex;
    private int population;
    private int area_of_city;
	public City(int id, String name, State stateObj, float pollutionIndex, int population, int area_of_city) {
		super();
		this.id = id;
		this.name = name;
		this.stateObj = stateObj;
		this.pollutionIndex = pollutionIndex;
		this.population = population;
		this.area_of_city = area_of_city;
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
	public State getStateObj() {
		return stateObj;
	}
	public void setStateObj(State stateObj) {
		this.stateObj = stateObj;
	}
	public float getPollutionIndex() {
		return pollutionIndex;
	}
	public void setPollutionIndex(float pollutionIndex) {
		this.pollutionIndex = pollutionIndex;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getArea_of_city() {
		return area_of_city;
	}
	public void setArea_of_city(int area_of_city) {
		this.area_of_city = area_of_city;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", stateObj=" + stateObj + ", pollutionIndex=" + pollutionIndex
				+ ", population=" + population + ", area_of_city=" + area_of_city + "]";
	}
}
