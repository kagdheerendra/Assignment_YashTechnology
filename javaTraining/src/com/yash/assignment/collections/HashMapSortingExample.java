package com.yash.assignment.collections;

import java.util.*;
import java.util.Map.Entry;

public class HashMapSortingExample {
    public static void main(String args[]) {
    	HashMap<String,Integer> map = new HashMap<>();
    	Map<String, Integer> map1 = new LinkedHashMap<>();
    	map.put("indore", 100000);
    	map.put("pune", 300000);
    	map.put("mumbai", 500000);
    	map.put("dehli", 700000);
    	
    	Set<String> set = map.keySet();
    	List<String> list = new ArrayList<String>(set);
        Collections.sort(list);
        for(String s : list) {
        	if(map.containsKey(s)) {
        		map1.put(s, map.get(s));
        	}
        }
        System.out.println(map1.toString());
    }
}
