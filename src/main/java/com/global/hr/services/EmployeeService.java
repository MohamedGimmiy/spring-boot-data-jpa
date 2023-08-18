package com.global.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.Reposatories.EmployeeRepo;
import com.global.hr.entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentService departmentService;
	
	public Employee findById(Long id) {
		return employeeRepo.findById(id).orElseThrow();
	}
	
	public List<Employee> findBySalary(Double salary){
		return employeeRepo.findBySalary(salary);
	}

	
	public List<Employee> findByEmpAndDept(String empName, String department) {
		
		return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, department);
	}
	
	public Employee insert(Employee emp) {
		
		if(emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
		}
		
		return employeeRepo.save(emp);
	}
	
	public Employee update(Employee emp) {
		Employee current= employeeRepo.findById(emp.getId()).get();
		current.setName(emp.getName());
		current.setSalary(emp.getSalary());
		current.setDepartment(emp.getDepartment());
		return employeeRepo.save(emp);
	}
	
	public List<Employee> findByDepartmentId(Long id){
		return employeeRepo.findByDepartmentId(id);
	}

	
	public List<Employee> findAll(){
		return employeeRepo.findAll();
	}
	
}
