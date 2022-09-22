package com.yash.training.task.basics;

public class AbstractTest extends AbstractDemo implements IDemo{

	public AbstractTest(String className, String trnName) {
		super(className, trnName);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        AbstractTest t = new AbstractTest("xyz", "java");
        t.showDetails();
        t.useTrn();
        IDemo i = new AbstractTest("xyz", "java");
        i.showData();
        i.showTrnName();
	}

	@Override
	void useTrn() {
		// TODO Auto-generated method stub
		System.out.println("hello i am from AbstractTest class");
	}

	public void showData() {
		// TODO Auto-generated method stub
		System.out.println("Hello this method is from IDemo Interface");
	}

	@Override
	public void showTrnName() {
		// TODO Auto-generated method stub
		System.out.println(trnName);
	}

}
