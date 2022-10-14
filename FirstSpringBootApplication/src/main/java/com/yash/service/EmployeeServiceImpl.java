package com.yash.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.dao.EmployeeDao;
import com.yash.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeDao empDao;

	@Transactional
	@Override
	public Employee addEmp(Employee e) {
		Employee emp = new Employee();
		try {
			emp = empDao.save(e);
		} catch (Exception e2) {
			System.out.println("Exception while adding employee");
		}
		return emp;
	}

	@Transactional
	@Override
	public Employee updateEmp(Employee e) {
		Employee emp = new Employee();
		try {
			 emp  = getEmpById(e.getId());
			 if(emp != null) {
				 emp = empDao.save(e);
			}
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}
		return emp;
	}

	@Transactional
	@Override
	public String deleteEmp(int empId) {
		Employee e = new Employee();
		boolean flag = false;
		try {
			e = getEmpById(empId);
			if (e != null) {
				empDao.deleteById(empId);
				flag = true;
			}
		} catch (EmployeeNotFoundException e1) {
			e1.printStackTrace();
		}
		if (flag) {
			return "employee deleted Successfully with id= " + empId;
		} else {
			return "id " + empId + " does not exist";
		}
	}

	@Override
	public Employee getEmpById(int id) throws EmployeeNotFoundException {
		Employee e = new Employee();
		Optional<Employee> op = empDao.findById(id);
		if (!op.isEmpty()) {
			e.setId(op.get().getId());
			e.seteName(op.get().geteName());
			e.setSalary(op.get().getSalary());
			return e;
		} else {
			throw new EmployeeNotFoundException("Employee not found" + e.toString());
		}
	}

	@Override
	public List<Employee> getAllEmp() {
        return empDao.findAll();
	}

}
