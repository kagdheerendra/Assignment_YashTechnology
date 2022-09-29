package com.yash.assignment.java8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class StudentStreamTask3 {

	public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        list.add(new Student(101, LocalDateTime.parse("2016-03-04 11:30", formatter), LocalDateTime.parse("1994-03-04 11:30", formatter), "40", "45", "50", "55", "60", "first"));
        list.add(new Student(101, LocalDateTime.parse("2017-03-04 11:30", formatter), LocalDateTime.parse("1995-03-04 11:30", formatter), "60", "55", "50", "45", "40", "second"));
        DoubleSummaryStatistics stats = list.stream().mapToDouble(s->Double.parseDouble(s.getEnglish())+Double.parseDouble(s.getChemistry())+Double.parseDouble(s.getHindi())+Double.parseDouble(s.getPhysics())+Double.parseDouble(s.getMaths())).summaryStatistics();
	    System.out.println(stats);
	}

}
