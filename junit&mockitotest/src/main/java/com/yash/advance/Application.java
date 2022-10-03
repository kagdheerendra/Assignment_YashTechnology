package com.yash.advance;

public class Application {
    private int id;
    private String name;
	public Application(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Application() {
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + "]";
	}
    
}
