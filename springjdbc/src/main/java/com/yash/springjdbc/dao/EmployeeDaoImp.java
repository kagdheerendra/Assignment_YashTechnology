package com.yash.springjdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yash.springjdbc.entity.Employee;

public class EmployeeDaoImp implements IDaoEmployee{
    
	private JdbcTemplate jdbcTemplate;

	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int insertEmp(Employee e) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("insert into employee() values('"+e.getId()+"','"+e.getEmpName()+"','"+e.getSalary()+"')");
	}

	public int updateEmp(Employee e, int id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("update employee set empName='"+e.getEmpName()+"', salary='"+e.getSalary()+"' where id='"+e.getId()+"'");
	}

	public int deleteEmp(int id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.update("delete from employee where id="+id);
	}

	public Employee getEmpById(int id) {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.queryForObject("select * from employee where id=?", new RowMapperImp(), id);
	}

	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from employee", new RowMapperImp());
	}

}
