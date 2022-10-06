package com.yash.springiocbasic.shape;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DrawShap 
{
	private static ApplicationContext context;
	public static void main( String[] args ) throws BeansException
    {
        context = new ClassPathXmlApplicationContext("com/yash/springiocbasic/shape/shapeapplicationcontext.xml");
        Shape r = (Shape) context.getBean("rect");
        r.draw();
        Shape t = (Shape) context.getBean("trgl");
        t.draw();
    }
}
