package com.global.hr.Reposatories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
	
}
