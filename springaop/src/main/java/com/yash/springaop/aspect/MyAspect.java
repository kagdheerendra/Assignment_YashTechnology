package com.yash.springaop.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
	
	@Before(value = "execution(* com.yash.springaop.services.PaymentServiceImpl.makePayment(..))")
    public void printBefore(){
    	System.out.println("Payment started..!");
    }
	
	@After(value = "execution(* com.yash.springaop.services.PaymentServiceImpl.makePayment(..))")
    public void printAfter(){
    	System.out.println("Payment done..!");
    }
}
