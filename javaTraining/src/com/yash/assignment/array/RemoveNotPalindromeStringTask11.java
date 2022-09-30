package com.yash.assignment.array;

import java.util.Arrays;

/*
 * WAP to remove the String which is not palindrome string from the array of String.
 */
public class RemoveNotPalindromeStringTask11 {

	public static void main(String[] args) {
       String name[] = {"akka", "akku", "abba","vbv"};
       for(int i=0; i<name.length; i++) {
    	   if(!isPalindrome(name[i])) {
    		   String temp = name[i];
    		   name[i] = name[i+1];
    	   }
       }
       System.out.println(Arrays.toString(name));
    }
	
    public static boolean isPalindrome(String s) {
    	String dups = "";
    	for(int i=s.length()-1; i>=0; i--) {
    		dups = dups + s.charAt(i);
    	}
    	return s.equals(dups);
    }
}
