package com.yash.training.task.basics;

public abstract class AbstractDemo {
   String className = "abstract demo class";
   String trnName = "";
   public AbstractDemo(String className, String trnName) {
	   this.className = className;
	   this.trnName = trnName;
   }
   
   public void showDetails() {
	   System.out.println(this.className+"::"+this.trnName);
   }
   
   abstract void useTrn();
}
