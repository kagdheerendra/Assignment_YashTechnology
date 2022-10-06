package com.yash.springiocbasic.settermessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static ApplicationContext context;
    
	public static void main(String args[]) {
       context = new ClassPathXmlApplicationContext("com/yash/springiocbasic/settermessage/settermsgapplicationcontext.xml");	
       SetterMessage s = (SetterMessage) context.getBean("settermsg");
       System.out.println(s);
    }
}
