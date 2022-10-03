package com.yash.intermediate;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class HostelQuestion5Test {
	HostelQuestion5 c = new HostelQuestion5();
	
    @Test
    public void numberOfStudnetRegistered1(){  
    	ArrayList<Student> l = new ArrayList<Student>();
        l.add(new Student(1, "dk", "abc", true));
        l.add(new Student(2, "nk", "xyz", true));
        l.add(new Student(3, "rk", "cbs", false));
        
        assertEquals(2,c.numberOfStudnetRegistered(l)); 
    }
    
    @Test
    public void numberOfStudnetRegistered2(){  
    	ArrayList<Student> l = new ArrayList<Student>();
        l.add(new Student(1, "dk", "abc", true));
        l.add(new Student(2, "nk", "xyz", true));
        l.add(new Student(3, "rk", "cbs", false));
        
        assertEquals(3,c.numberOfStudnetRegistered(l)); 
    }
}
