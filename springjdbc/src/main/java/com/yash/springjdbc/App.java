package com.yash.springjdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yash.springjdbc.dao.EmployeeDaoImp;
import com.yash.springjdbc.entity.Employee;

/**
 * Hello world!
 *
 */
public class App 
{
    private static ApplicationContext context;

	public static void main( String[] args )
    {
        System.out.println( "Hello Spring Jdbc!" );
        context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        EmployeeDaoImp empDao = context.getBean("empDao", EmployeeDaoImp.class);
        Employee e = new Employee();
        e.setId(102);
        e.setEmpName("priyanka");
        e.setSalary(45000);
        //empDao.insertEmp(e);
        //empDao.updateEmp(e, 102);
        //empDao.deleteEmp(102);
        System.out.println("getEmpById = "+empDao.getEmpById(103));
        List<Employee>list = empDao.getAllEmp();
        for(Employee ep : list) {
        	System.out.println(ep);
        }
    }
}
