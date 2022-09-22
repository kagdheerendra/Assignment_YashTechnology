package com.yash.assignment.oops.task9;

/**
 * With the help of finalize method print the how many objects are currently 
a class is having and which object is going to be freed from the memory 
with its hashcode. 
 * @author dheerendra.kag
 *
 */
public class Main {
   private static int count;
   public Main() {
	   count++;
   }
   public static void main(String args[]) {
	   Main m1 = new Main();
	   Main m2 = new Main();
	   Main m3 = new Main();
	   Main m5 = new Main();
	   System.out.println("Number of object is = "+count);
	   System.out.println(m1.hashCode());
	   m1 = null;
	   System.gc();
	}
   
   @Override  
   protected void finalize()   
   {   
       System.out.println("finalize method called");   
   } 
} 
