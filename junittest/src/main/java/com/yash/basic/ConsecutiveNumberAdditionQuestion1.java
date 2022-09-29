package com.yash.basic;

public class ConsecutiveNumberAdditionQuestion1 {
	
	public int findConsecutive(int array[]) {
	    int length = array.length;
	    int firstNum = array[0];
	    int lastNum = array[length - 1];
	    int totalSum = length * (firstNum + lastNum) / 2;
	    return totalSum;
	}
}
