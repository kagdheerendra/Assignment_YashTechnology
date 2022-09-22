package com.yash.training.task.basics;

public class JavaTraining {
	static int duration;
	String trainingName;

	public JavaTraining() {
		System.out.println("Calling default Const");
	}

	public JavaTraining(int duration) {
		this.duration = duration;
	}

	public void useLaptop() {
		System.out.println("Laptop");
	}

	public void doCoding() {
		System.out.println("Coding...!");
	}

	public void useTeams() {
		System.out.println("on teams virtually attend the session");
	}

	public static void main(String args[]) {
		JavaTraining j = new JavaTraining(24);
		System.out.println(duration);
		j.doCoding();
	}
}
