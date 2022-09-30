package com.yash.assignment.string;

import java.util.Scanner;

/**
 * Create a program in which two string is input by the user and after that user will enter index in 
   first string where we want to insert the second string and insert the second string at that index 
   and create a new string
 * @author dheerendra.kag
 *
 */
public class SubStringProgramTask1 {
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter first String");
    	String fString  = sc.next();
    	System.out.println("Enter Second String");
    	String sString = sc.next();
    	System.out.println("Enter first String index");
    	int index = sc.nextInt();
    	String result = fString.substring(0, index) + sString + fString.substring(index, fString.length());
    	System.out.println(result);
    }
}
