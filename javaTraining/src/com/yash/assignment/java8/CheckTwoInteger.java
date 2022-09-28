package com.yash.assignment.java8;

import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CheckTwoInteger {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	    boolean flag = true;
	    do {
			System.out.println("Press 1 to check given no is even number");
			System.out.println("Press 2 to check given no is odd number");
			System.out.println("Press 3 to check given no is Armstrong number");
			System.out.println("Press 4 to check given no is palindrome");
			System.out.println("Press 5 to exit");
			int op = sc.nextInt();
			System.out.println("Enter the num");
			int num = sc.nextInt();
			switch (op) {
			case 1: {
				checkEvenNumber(num);
				break;
			}
			case 2: {
				checkoddNumber(num);
				break;
			}
			case 3: {
				checkArmstrongNumber(num);
				break;
			}
			case 4: {
				checkPalindromeNumber(num);
				break;
			}
			case 5: flag = false;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}	    	
	    }while(flag);
	}
	
	public static void checkEvenNumber(int num) {
		Predicate<Integer> p = n->n%2==0;
		if(p.test(num)) {
			System.out.println("even number");
		}else System.out.println("Not even number");
	}

	public static void checkoddNumber(int num) {
		Predicate<Integer> p = n->n%2==0;
		if(!p.test(num)) {
			System.out.println("odd number");
		}else System.out.println("Not odd number");
	}
	
	public static void checkArmstrongNumber(int num) {
		int rem = 0, rev=0;
		int copyNumber = num;
		while(num>0) {
			rem = num%10;
			rev = rev + rem*rem*rem;
			num = num/10;
		}
		BiPredicate<Integer, Integer> b = (Integer num1, Integer num2)->num1.equals(num2);
		if(b.test(copyNumber, rev)) {
			System.out.println(copyNumber + " is an Armstrong number.");
		}else System.out.println(copyNumber + " is not an Armstrong number.");
	}
	
	public static void checkPalindromeNumber(int num) {
        int copyNum;
        int rem = 0,rev = 0;
        copyNum = num;
        while(num>0) {
        	rem = num%10;
        	rev = rem + rev*10;
        	num = num/10;
        }
        BiPredicate<Integer, Integer> p = (Integer num1, Integer num2)->num1.equals(num2);
        if(p.test(copyNum, rev)) {
        	System.out.println("palindrome number");
        }else {
        	System.out.println("not palindrome");
        }
	}
}
