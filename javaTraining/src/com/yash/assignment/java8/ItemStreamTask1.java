package com.yash.assignment.java8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.function.BiFunction;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class ItemStreamTask1 {

	public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Task(1, "mobile", LocalDateTime.now(), LocalDateTime.of(2023, 9, 10, 6, 40, 45), 20000f));
        list.add(new Task(2, "laptop", LocalDateTime.now(), LocalDateTime.of(2024, 9, 10, 6, 40, 45), 50000f));
        list.add(new Task(3, "oil", LocalDateTime.now(), LocalDateTime.of(2023, 9, 10, 6, 40, 45), 2500f));
        list.add(new Task(4, "pen", LocalDateTime.now(), LocalDateTime.of(2025, 9, 10, 6, 40, 45), 100f));
        list.add(new Task(6, "marker", LocalDateTime.now(), LocalDateTime.of(2025, 9, 10, 6, 40, 45), 100f));

        Task t = new Task();
        t.setItemid(5);
        t.setiName("bottle");
        t.setDate_of_manufacturing(LocalDateTime.now());
        t.setPrice(10000f);
        list.add(t);
        
        OptionalDouble avg =  list.stream().mapToDouble(i->i.getPrice()).average();
        System.out.println("Average price of item =" + avg.getAsDouble());
        
        OptionalDouble highest = list.stream().mapToDouble(i->i.getPrice()).max();
        System.out.println("Highest price of item =" + highest.getAsDouble());
        
        OptionalDouble lowest = list.stream().mapToDouble(i->i.getPrice()).min();
        System.out.println("lowest price of item =" + lowest.getAsDouble());
        
//        OptionalDouble noExpiryD = list.stream().filter(i->i.getDate_of_expiry().equals(null)).mapToDouble(i->i.getPrice()).max();
//        System.out.println("Not having expiry Date =" + noExpiryD.getAsDouble());
        
        Set<Task> itemSet = list.stream().map(i->new Task(0,i.getiName(),null,null,i.getPrice())).collect(Collectors.toSet());
        System.out.println("set of item is = "+itemSet.toString());
        
        
//        List<Task> unique = list.stream()
//                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingFloat(Task::getPrice))),
//                                           ArrayList::new));
        
        BiFunction<TreeSet<Task>,List<Task> ,TreeSet<Task>> appendTree = (y,x) -> (y.addAll(x))? y:y;

        TreeSet<Task> outputList = appendTree.apply(new TreeSet<Task>(Comparator.comparing(p->p.getPrice())),list);
        
        System.out.println("distinct list is = "+ outputList.toString());
        
	}

}
