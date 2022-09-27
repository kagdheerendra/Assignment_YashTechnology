package com.yash.assignment.array;

public class CheckPalindromeString {

	public static void main(String[] args) {
         String s = "abbya";
         String arr[] = {"abba", "isd", "aca", "aoisdj"};
         for(int i=0; i<arr.length; i++) {
        	 if(!isPalindrome(arr[i])) {
        		 
        	 }
         }
         System.out.println(isPalindrome(s));
	}

	public static boolean isPalindrome(String s) {
		String rev = "";
		for(int i=s.length()-1; i>=0; i--) {
			rev = rev + s.charAt(i);
		}
		if(rev.equals(s)) {
			return true;
		}else {
			return false;
		}
	}
}
