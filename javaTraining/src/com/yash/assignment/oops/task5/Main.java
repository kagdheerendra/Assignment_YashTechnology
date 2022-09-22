package com.yash.assignment.oops.task5;

abstract class CalcAbs{
	void sum(int a, int b) {
		System.out.println(a+b);
	}
	 void sub(int a, int b){
		System.out.println(a-b);
	}
	 void mul(int a, int b){
		System.out.println(a*b);
	}
	 void div(int a,int b){
		System.out.println(a/b);
	}
}

class A extends CalcAbs{
	void sum(int a, int b) {
		System.out.println(a+b);
	}
}

class B extends A{
	 void sub(int a, int b){
		System.out.println(a-b);
	}
}

class C extends B{
	 void mul(int a, int b){
		System.out.println(a*b);
	}
}

public class Main extends C{
	void div(int a,int b){
		System.out.println(a/b);
	}
   public static void main(String args[]) {
	   Main d = new Main();
	   d.div(10, 2);
       d.mul(1, 3);
       d.sub(3, 1);
       d.sum(2, 9);
	}
}


