package com.yash.basic;

public class SumOfDigitExpressionQuestion4 {
     public int sumOfDigitFromExpression(String s) {
    	 int sum = 0;
    	 for(int i=0; i<s.length(); i++) {
    		 char temp = s.charAt(i);
		     if (Character.isDigit(temp)) {
			        int b = Integer.parseInt(String.valueOf(temp));
			        sum=sum+b;
		     }
    	 }
    	 return sum;
     }
}
