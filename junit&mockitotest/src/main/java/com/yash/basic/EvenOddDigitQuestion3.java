package com.yash.basic;

public class EvenOddDigitQuestion3 {
    public String findTotalEvenOddDigit(long num){
    	long digit = 0;
    	int eCount=0;
    	int oCount=0;
    	while(num>0) {
    		digit = num%10;
    		if(digit%2==0) {
    			eCount++;
    		}else {
    			oCount++;
    		}
    		num = num/10;
    	}
    	return eCount+" "+oCount;
    }
}
