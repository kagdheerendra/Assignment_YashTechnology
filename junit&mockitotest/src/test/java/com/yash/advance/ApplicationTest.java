package com.yash.advance;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class ApplicationTest {
	ApplicationService a = new ApplicationService();
	Application app = new Application();
	
    @Test
    public void testAddApplication1(){  
        assertEquals("Application [id=1, name=abc]",a.addApplication(new Application(1, "abc"))); 
    }
    
    @Test
    public void testAddApplication2(){  
        assertEquals("Application [id=1, name=cba]",a.addApplication(new Application(1, "abc"))); 
    }
    
    @Test
    public void testUpdateApplication1() throws InterruptedException{  
    	Thread.sleep(2000);
        assertEquals("Application [id=1, name=xyz]",a.updateApplication(1, "xyz")); 
    }
    
    @Test
    public void testUpdateApplication2() throws InterruptedException{  
    	Thread.sleep(2000);
        assertEquals("Application [id=1, name=xyz]",a.updateApplication(1, "xyz")); 
    }
    
    @Test
    public void testGetAllApplications1(){  
    	assertEquals("Application [id=1, name=xyz]",a.getAllApplications()); 
    }
}
