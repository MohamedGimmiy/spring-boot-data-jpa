package com.global.hr.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.entity.Employee;
import com.global.hr.entity.EmployeeResponse;
import com.global.hr.services.EmployeeService;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/{id}")
	public EmployeeResponse findById(@PathVariable Long id) {
		
		Employee employee = employeeService.findById(id);
		EmployeeResponse res = new EmployeeResponse();
		res.setId(employee.getId());
		res.setName(employee.getName());
		res.setSalary(employee.getSalary());
		res.setDepartment(employee.getDepartment());

		return res; 
	}
	
	
	@GetMapping("/emp-dept")
	public List<Employee> findByEmpAndDept(@RequestParam String empName,@RequestParam String department) {
		
		return employeeService.findByEmpAndDept(empName, department);
	}
	
	
	@PostMapping
	public Long insert(@RequestBody Employee emp) {
		Employee inserted =  employeeService.insert(emp);
		return inserted.getId();
	}
	
	@PutMapping
	public Employee update(@RequestBody Employee emp) {
		return employeeService.update(emp);
	}
	
	@GetMapping("/department/{id}")
	public List<Employee> findByDepartmentId(@PathVariable Long id){
		return employeeService.findByDepartmentId(id);
	}
	
	@GetMapping("/salary")
	public ResponseEntity<?> findBySalary(@RequestParam Double salary){
		return new ResponseEntity<List<Employee>>( employeeService.findBySalary(salary),HttpStatus.OK);
	}
}
