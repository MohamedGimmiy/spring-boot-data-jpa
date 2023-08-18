package com.global.hr.Reposatories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.hr.entity.Employee;
import com.global.hr.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	

	
}
