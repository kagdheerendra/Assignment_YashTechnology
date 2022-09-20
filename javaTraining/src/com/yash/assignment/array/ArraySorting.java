package com.yash.assignment.array;

import java.util.Arrays;

/**
 * Suppose that you are having an array of size N. now your task is to sort the half array that is 
   from 0 to N/2 in ascending order and N/2+1 to N in descending order.
 * @author dheerendra.kag
 *
 */
public class ArraySorting {
   public static void main(String args[]) {
	   int ar[] = {2,1,4,3,6,5,8,7,9};
	   Arrays.sort(ar);
	    for (int i = 0; i < ar.length / 2; i++)
	        System.out.print(ar[i]+" ");
	 
	    for (int j = ar.length - 1; j >= ar.length / 2; j--)
	    System.out.print(ar[j]+" ");
   }
}
