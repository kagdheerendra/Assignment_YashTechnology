package com.yash.assignment.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CityStateStreamTask2 {

	public static void main(String[] args) {
         ArrayList<City> list = new ArrayList<>();
         list.add(new City(1, "indore", new State(1, "mp"), 15f, 100000, 1000000));
         list.add(new City(2, "bhopal", new State(1, "mp"), 25f, 50000, 1000));
         list.add(new City(3, "pune", new State(3, "maharastra"), 35, 200000, 2000000));
         
         System.out.println("the city with less area and highest population");
         Stream<City> lessArea =  list.stream().sorted(Comparator.comparing(City::getArea_of_city).thenComparing(City::getPopulation));
         lessArea.forEach(System.out::println);
         
         System.out.println("the city with high pollution_index and high city area");
         Stream<City> higherPopultaion =  list.stream().sorted(Comparator.comparing(City::getPollutionIndex).reversed().thenComparing(City::getArea_of_city));
         higherPopultaion.forEach(System.out::println);
         
         System.out.println("the city detail on the basis of lowest pollution_index first");
         Stream<City> lowestPollutionIndex =  list.stream().sorted(Comparator.comparing(City::getPollutionIndex));
         lowestPollutionIndex.forEach(System.out::println);
         
         System.out.println("the city with lowest pollution index and lowest area of city");
         Stream<City> lowestPollutionIndexlowestArea =  list.stream().sorted(Comparator.comparing(City::getPollutionIndex).thenComparing(City::getArea_of_city));
         lowestPollutionIndexlowestArea.forEach(System.out::println);	
         
         System.out.println("count of cities in every state");
         Map<City, Long> counted = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
         System.out.println(counted);
	}

}
