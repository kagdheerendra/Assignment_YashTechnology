package com.yash.springiocbasic.constructormessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static ApplicationContext context;
    
	public static void main(String args[]) {
       context = new ClassPathXmlApplicationContext("com/yash/springiocbasic/constructormessage/conmsgapplicationcontext.xml");	
       ConstructorMessage con =  (ConstructorMessage) context.getBean("conMsg");
       System.out.println(con);
    }
}
