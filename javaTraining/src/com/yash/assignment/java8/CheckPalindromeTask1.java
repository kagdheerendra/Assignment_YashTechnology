package com.yash.assignment.java8;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class CheckPalindromeTask1 {

	public static void main(String[] args) {
        int num = 454, copyNum;
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
