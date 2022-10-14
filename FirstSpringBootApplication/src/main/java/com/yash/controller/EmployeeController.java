package com.yash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.entity.Employee;
import com.yash.service.EmployeeNotFoundException;
import com.yash.service.IEmployeeService;

@RestController
public class EmployeeController {

	@Autowired
    IEmployeeService service;
	
	@GetMapping("/getEmployee")
	public List<Employee> getAllEmployee(){
		List<Employee> getAllEmployee = service.getAllEmp();
		return getAllEmployee;
	}
	
	@PostMapping("/addEmployee")
	public Employee addEmp(@RequestBody Employee e) {
		return service.addEmp(e);
	}
	
	@PutMapping("/updateEmployee")
	public Employee updateEmp(@RequestBody Employee e) {
		return service.updateEmp(e);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public String deleteEmp(@PathVariable int id) {
		return service.deleteEmp(id);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Employee getEmpById(@PathVariable int id) throws EmployeeNotFoundException {
		return service.getEmpById(id);
	}
}
