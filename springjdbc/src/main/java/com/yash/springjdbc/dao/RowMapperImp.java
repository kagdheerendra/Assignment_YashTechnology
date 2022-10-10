package com.yash.springjdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.yash.springjdbc.entity.Employee;

public class RowMapperImp implements RowMapper<Employee> {

	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		e.setId(rs.getInt(1));
		e.setEmpName(rs.getString(2));
		e.setSalary(rs.getInt(3));
		return e;
	}

}
