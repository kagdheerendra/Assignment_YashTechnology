package com.yash.basic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecimalToBinaryQuestion7Test {
	DecimalToBinaryQuestion7 d = new DecimalToBinaryQuestion7();
	
    @Test
    public void testdecimalToBinary1(){  
    	assertEquals("101",d.decimalToBinary(5));  
    } 
    
    @Test
    public void testdecimalToBinary2(){  
    	assertEquals("111",d.decimalToBinary(5)); 
    }
}
