package com.global.hr.Reposatories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.hr.HrStatisticsProjection;
import com.global.hr.entity.Employee;
import com.global.hr.projection.EmployeeProjection;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	List<Employee> findByDepartmentId(Long id);

	
	List<Employee> findByDepartmentt(Long deptId);

	List<Employee> findBySalary(Double salary, String name);
	
	List<Employee> findByNameContainingAndDepartmentNameContaining(String empName, String department);
	
	@Query(value = "select emp from Employee emp join emp.department dept")
	Page<Employee> findByDepartments(Pageable pageable);
	
	// projection using constructor
	@Query(value = "select new Employee(emp.id,emp.name,emp.salary) from Employee emp where emp.name like :name%")
	Page<Employee> findEmployees(Pageable pageable, String name);
	
	
	// projection with  native query
	@Query(value = "select (select count(*) from employee) as empCount, (select count(*) from users) as usersCount",
			nativeQuery = true)
	HrStatisticsProjection getHrStatistics();
	
	
}
