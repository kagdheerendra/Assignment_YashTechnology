package com.yash.intermediate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringTest1Test {
	StringTest1 t = new StringTest1();
	
    @Test
    public void testCharAt1(){  
    	assertEquals('a',t.testCharAtMethod("abc", 0));  
    } 

    @Test
    public void testCharAt2(){  
    	assertEquals('b',t.testCharAtMethod("abc", 0));  
    } 
    
    @Test
    public void testConcatMethod1(){  
    	assertEquals("javadeveloper",t.testConcatMethod("java", "developer"));  
    } 

    @Test
    public void testConcatMethod2(){  
    	assertEquals("java  developer",t.testConcatMethod("java", "developer"));
    } 
    
    @Test
    public void testContainMethod1(){  
    	assertEquals(true,t.testContainMethod("java developer", "developer"));  
    } 

    @Test
    public void testContainMethod2(){  
    	assertEquals(true,t.testContainMethod("java developer", "hello56"));
    }
    
    @Test
    public void testEndWithMethod1(){  
    	assertEquals(true,t.testEndWithMethod("java developer", "r"));  
    } 

    @Test
    public void testEndWithMetho2(){  
    	assertEquals(true,t.testEndWithMethod("java developer", "p"));
    }
}


