package com.yash.assignment.java8;

import java.util.Arrays;
import java.util.*;
import java.util.Scanner;
import java.util.stream.IntStream;

public class IntegerPatternExampleTask5 {
   public static void main(String args[]) {
	   try (Scanner sc = new Scanner(System.in)) {
		   System.out.println("Enter the size of array list");
		   int size = sc.nextInt();
		   ArrayList<Integer> list = new ArrayList<Integer>();
		   System.out.println("Enter the element of array");
		   for(int i=0; i<size; i++) {
			   list.add(sc.nextInt());
		   }
		   ArrayList<String> flist = new ArrayList<String>();
		   int length = 1;
		   for(int i=1; i<list.size();i++) {
			   if(list.get(i) - list.get(i-1) != 1) {
                    flist.add(list.get(i-length) + "->" + list.get(i-1));
                    length = 1;
			   }else {
				    length++;
			   }
		   }
		   System.out.println(flist.toString());
//		   IntStream.rangeClosed(1, list.size())
//				   .filter(i->list.get(i) - list.get(i - 1) != 1)
//				   .forEach(System.out::println);
		   
		   int[] indices = IntStream.rangeClosed(0, list.size())
		      .filter(i -> i == 0 || i == list.size() || list.get(i - 1) + 1 != list.get(i))
		      .toArray();
		   int longest = IntStream.range(0, indices.length - 1).map(i -> indices[i + 1] - indices[i])
		      .max().orElseThrow(NoSuchElementException::new);
		   //System.out.println(longest);
		   //System.out.println(Arrays.toString(indices));
//		   boolean isSequential = IntStream.range(1, list.size())
//				   .allMatch(value -> list.get(value) - list.get(value - 1) == 1);
//		   System.out.println(isSequential);
	}
   }
}
