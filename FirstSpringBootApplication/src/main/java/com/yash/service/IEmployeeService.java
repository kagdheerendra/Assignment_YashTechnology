package com.yash.service;

import java.util.List;

import com.yash.entity.Employee;

public interface IEmployeeService {
    public Employee addEmp(Employee e);
    public Employee updateEmp(Employee e);
    public String deleteEmp(int id);
    public Employee getEmpById(int id) throws EmployeeNotFoundException;
    public List<Employee> getAllEmp();
}
