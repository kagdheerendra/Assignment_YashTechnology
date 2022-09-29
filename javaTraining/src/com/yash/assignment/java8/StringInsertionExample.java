package com.yash.assignment.java8;

import java.util.Arrays;

public class StringInsertionExample {

	public static void main(String[] args) {
        String s1 = "java training";
        String s2 = "full stack";
        StringConcatenation con = (String str1, String str2)->{
        	for(int i=0; i<str1.length(); i++) {
        		if(str1.charAt(i) == ' ') {
        			return str1.substring(0, i)+" "+str2+str1.substring(i);
        		}
        	}
        	return null;
        };
        System.out.println(con.concatenation(s1, s2));
	}

}
