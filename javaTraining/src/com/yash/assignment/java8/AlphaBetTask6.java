package com.yash.assignment.java8;

public class AlphaBetTask6 {
	public static void main(String[] args) {
	   AlphabetFunction a = (int input, int alphabet)-> {
	       for(int i=0;i<= input;i++)
	       {
	           for(int j=0;j<=input-i;j++)
	           {
	               System.out.print((char)(alphabet+j));
	           }
	           for(int k=1;k<=i*2-1; k++)
	           {
	               System.out.print(" ");
	           }
	           for(int l=input-i; l>=0; l--)
	           {
	               if(l!=input)
	                   System.out.print((char)(alphabet+l));
	           }
	          System.out.println();
	       }
	   };
	   a.printPattern(5, 65);
	}
}
