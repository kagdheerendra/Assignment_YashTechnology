package com.yash.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FactorialQuestion2Test {
	FactorialQuestion2 f = new FactorialQuestion2();
	
    @Test
    public void testFactorial1(){  
    	assertEquals(120,f.factorial(5));  
    } 
    
    @Test
    public void testFactorial2(){  
    	assertEquals(130,f.factorial(5));  
    } 
}
