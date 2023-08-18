package com.global.hr.Reposatories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	List<Employee> findByDepartmentId(Long id);
	
	List<Employee> findBySalary(Double salary);
	
	List<Employee> findByNameContainingAndDepartmentNameContaining(String empName, String department);
	
	@Query(value = "select emp from Employee emp join emp.department dept where dept.id = :id")
	List<Employee> findByDepartment(Long id);
	
	
}