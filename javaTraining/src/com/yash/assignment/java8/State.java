package com.yash.assignment.java8;

public class State {
	private int Stated;
	private String statename;

	public State(int stated, String statename) {
		super();
		Stated = stated;
		this.statename = statename;
	}

	public int getStated() {
		return Stated;
	}

	public void setStated(int stated) {
		Stated = stated;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	@Override
	public String toString() {
		return "State [Stated=" + Stated + ", statename=" + statename + "]";
	}

}
