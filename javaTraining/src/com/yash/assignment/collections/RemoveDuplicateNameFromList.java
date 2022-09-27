package com.yash.assignment.collections;

import java.util.ArrayList;
import java.util.Collections;

public class RemoveDuplicateNameFromList {

	public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("dheeren");
        list.add("priya");
        list.add("ram");
        list.add("priya");
        list.add("ayush");
        list.add("ram");
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> duplicateElements = new ArrayList<>();
        for(String s : list) {
        	if(list1.contains(s)) {
        		duplicateElements.add(s);
        	}else {
        		list1.add(s);
        	}
        }
        Collections.reverse(duplicateElements);
        System.out.println(duplicateElements.toString());
	}

}
