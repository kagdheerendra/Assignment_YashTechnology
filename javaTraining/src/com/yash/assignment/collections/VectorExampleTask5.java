package com.yash.assignment.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Vector;

public class VectorExampleTask5 {

	public static void main(String[] args) {
        Vector<ProductItem> v = new Vector();
        v.add(new ProductItem(1, "mobile", 20000));
        v.add(new ProductItem(2, "laptop", 50000));
        v.add(new ProductItem(3, "watch", 5000));
        v.add(new ProductItem(1, "printer", 15000));
        v.add(new ProductItem(1, "tv", 26000));
        Collections.sort(v, new Comparator<ProductItem>() {

			@Override
			public int compare(ProductItem o1, ProductItem o2) {
				return Double.compare(o1.getPrice(), o2.getPrice());
			}
		});
        
        Enumeration<ProductItem> e = Collections.enumeration(v);
        while(e.hasMoreElements()) {
        	System.out.println(e.nextElement().toString());
        }
    }

}
