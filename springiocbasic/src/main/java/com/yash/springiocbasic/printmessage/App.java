package com.yash.springiocbasic.printmessage;

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
        context = new ClassPathXmlApplicationContext("com/yash/springiocbasic/printmessage/printmessageapplicationcontext.xml");
        PrintMessage p = (PrintMessage) context.getBean("printmsg");
        System.out.println(p);
    }
}
