package com.yash.assignment.oops.task7;

import java.util.Scanner;

/**
 * With the help of method overriding perform the string comparison. User 
will input two string and your task is to compare both the string one 
alphabets at a time.
 * @author dheerendra.kag
 *
 */
public class Main {
   public static void main(String args[]) {
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Enter first String");
	   String s1 = sc.next();
	   System.out.println("Enter Second String");
	   String s2 = sc.next();
	   compareString(s1, s2);
   }
   
   public static void compareString(String s1, String s2) {
	   System.out.println("Comparing \"" + s1 + "\" to \"" + s2 + "\"...");
	   int result = s1.compareTo(s2);
	   if(result < 0) {
		   System.out.println(s2+" is larger");
	   }else if(result > 0) {
		   System.out.println(s1+" is larger");
	   }else {
		   System.out.println("Both are equals");
	   }
   }
}
