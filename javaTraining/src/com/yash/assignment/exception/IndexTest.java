package com.yash.assignment.exception;

import java.util.Scanner;

public class IndexTest {

	public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
		 String fName[] = {"abc","xyz","priya","azhar","neha","anshu","renuka","raj","bhunesh","ayush"};
		 System.out.println("Enter the Index");
		 try {
			 int index = sc.nextInt();
			 System.out.println(fName[index]);
		 }catch(ArrayIndexOutOfBoundsException e) {
			 System.out.println(e);
		 }
	}

}
