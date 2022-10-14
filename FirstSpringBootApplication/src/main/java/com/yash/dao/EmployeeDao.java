package com.yash.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.entity.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer>{

}
