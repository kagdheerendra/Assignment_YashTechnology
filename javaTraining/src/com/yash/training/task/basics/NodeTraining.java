package com.yash.training.task.basics;

public class NodeTraining extends JavaTraining {

	public NodeTraining() {
		super(50);
		System.out.println("Calling default Const of nodetraining");
	}

	public void doCoding() {
		System.out.println("nodejs Coding...!");
	}

	public static void main(String[] args) {
		// JavaTraining j = new JavaTraining();
		NodeTraining n = new NodeTraining();
		n.doCoding();
		System.out.println(n.duration);
	}

}
