package com.global.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.global.hr.HrStatisticsProjection;
import com.global.hr.Reposatories.EmployeeRepo;
import com.global.hr.entity.Employee;
import com.global.hr.projection.EmployeeProjection;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private UserService userService;
	
	public Employee findById(Long id) {
		return employeeRepo.findById(id).orElseThrow();
	}
	
	public List<Employee> findBySalary(Double salary, String name){
		return employeeRepo.findBySalary(salary, name);
	}

	
	public List<Employee> findByEmpAndDept(String empName, String department) {
		
		return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName, department);
	}
	
	public Employee insert(Employee emp) {
		
		if(emp.getDepartment() != null && emp.getDepartment().getId() != null) {
			emp.setDepartment(departmentService.findById(emp.getDepartment().getId()));
		}
		
		if(emp.getUser() != null && emp.getUser().getId() != null) {
			emp.setUser(userService.findById(emp.getUser().getId()));
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
	
	public List<Employee> findByDepartment(Long id){
		return employeeRepo.findByDepartmentt(id);
	}

	
	public Page<Employee> findByDepartmentandSorting(int pageNum, int pageSize, boolean isAsc){
		Pageable page = PageRequest.of(pageNum, pageSize,Sort.by(isAsc? Direction.ASC:Direction.DESC,"name"));
		
		return employeeRepo.findByDepartments(page);
	}
	
	public Page<Employee> findEmployees(int pageNum, int pageSize, boolean isAsc, String name){
		Pageable page = PageRequest.of(pageNum, pageSize,Sort.by(isAsc? Direction.ASC:Direction.DESC,"name"));
		
		return employeeRepo.findEmployees(page, name);
	}	
	public List<Employee> findAll(){
		return employeeRepo.findAll();
	}
	
	public HrStatisticsProjection getHrStatistics() {
		
		return employeeRepo.getHrStatistics();
	}
	
}
