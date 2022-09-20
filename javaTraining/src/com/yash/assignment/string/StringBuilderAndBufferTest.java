package com.yash.assignment.string;

/**
 * WAP to demonstrate which is fast in processing StringBuilder or StringBuffer.
 * @author dheerendra.kag
 *
 */
public class StringBuilderAndBufferTest {

    public static void main(String[] args){  
        long startTime = System.currentTimeMillis();  
        StringBuffer sb = new StringBuffer("Yash");  
        for (int i=0; i<1000000; i++){  
            sb.append("Technology");  
        }  
        System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");  
        startTime = System.currentTimeMillis();  
        StringBuilder sb2 = new StringBuilder("Yash");  
        for (int i=0; i<1000000; i++){  
            sb2.append("Technology");  
        }  
        System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");  
    } 

}
