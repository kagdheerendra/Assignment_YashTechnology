package com.yash.intermediate;

import java.util.Arrays;

public class StringTest2 {
	 public char testCharAtMethod(String s, int index) {
		   char c[] = s.toCharArray();
	       if ((index < 0) || (index >= c.length)) {    
	           throw new StringIndexOutOfBoundsException(index);    
	       }    
	       return c[index]; 
	 }
	 
	 public String testConcatMethod(String s1, String s2) {
		 return s1+s2;
	 }
	 
	 public boolean testContainMethod(String s1, String s2) {
		 return s1.indexOf(s2.toString()) > -1;  
	 }
	 
	 public boolean testEndWithMethod(String s1, String s2) {
		 char c[] = s1.toCharArray();
		 char c1[] = s2.toCharArray();
		 return s1.startsWith(s2, c.length - c1.length);    
	}
}
