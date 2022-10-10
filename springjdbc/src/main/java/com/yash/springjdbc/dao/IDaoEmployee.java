package com.yash.springjdbc.dao;

import java.util.*;

import com.yash.springjdbc.entity.Employee;

public interface IDaoEmployee {
    public int insertEmp(Employee e);
    public int updateEmp(Employee e, int id);
	public int deleteEmp(int stuid);
	public Employee getEmpById(int id);
	public List<Employee> getAllEmp();
}
