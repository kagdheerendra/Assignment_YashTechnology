package com.yash.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yash.springaop.services.IPaymentService;
import com.yash.springaop.services.PaymentServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ApplicationContext context;

	public static void main( String[] args )
    {
        context = new ClassPathXmlApplicationContext("com/yash/springaop/config.xml");
        IPaymentService p = (IPaymentService) context.getBean("payment");
        p.makePayment(123);
    }
}
