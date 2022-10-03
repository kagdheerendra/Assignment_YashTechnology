package com.yash.intermediate;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CarShopQuestion4Test {
	CarShopQuestion4 c = new CarShopQuestion4();
	
    @Test
    public void numberOfEmployee1(){  
    	ArrayList<Car> l = new ArrayList<Car>();
        l.add(new Car(200, "dk", "bmw", true));
        l.add(new Car(100, "nk", "tata", true));
        l.add(new Car(150, "nk", "lam", false));
        assertEquals(2,c.numberOfCarsSoldout(l)); 
    }
    
    @Test
    public void numberOfEmployee2(){  
    	ArrayList<Car> l = new ArrayList<Car>();
        l.add(new Car(200, "dk", "bmw", true));
        l.add(new Car(100, "nk", "tata", true));
        l.add(new Car(150, "nk", "lam", false));
        assertEquals(3,c.numberOfCarsSoldout(l)); 
    }
}
