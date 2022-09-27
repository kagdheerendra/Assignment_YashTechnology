package com.yash.assignment.collections;

import java.util.*;

public class AscOrderSorting {

	public static void main(String[] args) {
         ArrayList<String> list = new ArrayList<>();
         list.add("abc");
         list.add("acdc");
         list.add("ebc");
         list.add("gabc");
         list.add("hbac");
         Collections.sort(list);
         System.out.println(list.toString());
    }

}
