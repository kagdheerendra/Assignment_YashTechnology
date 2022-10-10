package com.yash.springfirstapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ApplicationContext context;

	public static void main( String[] args )
    {
        System.out.println( "Welcome to spring first application" );
        context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        Employee  e = (Employee) context.getBean("employee");
        System.out.println(e.toString());
        
        Employee  e1 = (Employee) context.getBean("employee1");
        System.out.println(e1.toString());
    }
}
