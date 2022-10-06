package com.yash.springiocbasic.employee;

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
        context = new ClassPathXmlApplicationContext("com/yash/springiocbasic/employee/employeeapplicationcontext.xml");
        Employee e = (Employee) context.getBean("employee");
        System.out.println(e);
    }
}
