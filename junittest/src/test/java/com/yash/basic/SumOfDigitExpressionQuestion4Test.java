package com.yash.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SumOfDigitExpressionQuestion4Test {
	SumOfDigitExpressionQuestion4 s = new SumOfDigitExpressionQuestion4();
	
    @Test
    public void testSumOfDigitFromExpression1(){  
    	assertEquals(6,s.sumOfDigitFromExpression("1+2+3"));  
    } 
    
    @Test
    public void testSumOfDigitFromExpression2(){  
    	assertEquals(7,s.sumOfDigitFromExpression("1+2+3")); 
    }
}
