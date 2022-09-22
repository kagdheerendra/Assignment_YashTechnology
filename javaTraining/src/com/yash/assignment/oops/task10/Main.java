package com.yash.assignment.oops.task10;

/**
 * WAP to demonstrate the use of clone method. Clone one object of 
Product class :- pid, pname, price and unitOfMeasurement. When you clone 
object of class Product change the product name and price. With the help of 
instanceOf check that the newly created object is belong to Product class or not.

 * @author dheerendra.kag
 *
 */
public class Main {
   public static void main(String args[]) throws CloneNotSupportedException {
	   Product p = new Product(0, "xyz", 93, "Meter");
	   Product p1 = (Product) p.clone();
	   p1.setPname("abc");
	   p1.setPrice(85);
	   System.out.println(p.toString());
	   System.out.println(p1.toString());
	   if(p1 instanceof Product) {
		   System.out.println("Clone obj is belog to product class");
	   }else {
		   System.out.println("Clone obj is not belog to product class");
	   }
   }
}
