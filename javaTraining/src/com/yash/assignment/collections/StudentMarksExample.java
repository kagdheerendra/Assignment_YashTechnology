package com.yash.assignment.collections;

import java.util.*;

public class StudentMarksExample {

	public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(1, "dheeren", "abc", 450));
        list.add(new Student(2, "ayush", "xyz", 350));
        list.add(new Student(3, "nikhil", "sad", 250));
        list.add(new Student(4, "kritika", "asdf", 150));
        list.add(new Student(5, "rupal", "ger", 200));
        list.add(new Student(6, "deepika", "asdf", 300));
        
        System.out.println("the name of students who secure first, second and third position");
        Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return Integer.compare(o1.getTotalMarks(), o2.getTotalMarks());
			}
		});
        Collections.reverse(list);
        System.out.println("first rank student is="+list.get(0).getName());
        System.out.println("sencond rank student is="+list.get(1).getName());
        System.out.println("third rank student is="+list.get(2).getName());
        
        System.out.println("-----------||------------");
        System.out.println("name of those students who’s marks are below 50%");
        for(Student s : list) {
        	float marks = s.getTotalMarks();
        	float percentage = (float)((marks / 500) * 100);
        	if(percentage < 50) {
        		System.out.println(s.getName());
        	}
        	if(percentage < 35) {
                System.out.println("-----------||------------");
                System.out.println("name of those students who’s marks are below 35%");
        		System.out.println(s.getName());
        	}
        }
        
        Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
        	
		});
        
        System.out.println("-----------||------------");
        System.out.println(" detail of all students on the basis of Name");
        System.out.println(list.toString());
    }

}
