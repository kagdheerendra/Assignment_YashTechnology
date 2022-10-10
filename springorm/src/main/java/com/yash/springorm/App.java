package com.yash.springorm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yash.springorm.dao.StudentDao;
import com.yash.springorm.entity.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext context=new ClassPathXmlApplicationContext("config.xml");
        StudentDao dao=context.getBean("studentDao",StudentDao.class);
        Student s = new Student(1, "priya", 250, "cs");	
        //int id = dao.insert(s);
        //dao.update(s);
        Student st = dao.getStudentById(2);
        System.out.println(st);
        
        
    }
}
