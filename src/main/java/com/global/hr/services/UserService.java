package com.global.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.Reposatories.UserRepo;
import com.global.hr.entity.Role;
import com.global.hr.entity.User;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleService roleService;
	
	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow();
	}
	
	public User insert(User user) {
		return userRepo.save(user);
	}
	
	public User update(User user) {
		User current= userRepo.findById(user.getId()).get();
		current.setUsername(user.getUsername());
		current.setPassword(user.getPassword());
		return userRepo.save(current);
	}
	

	
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	@Transactional
	public void addRoleForAllUsers(String roleName) {
		// start transaction
		Role role = roleService.findByName(roleName);
		
		findAll().forEach(user -> {
			user.addRole(role);
			userRepo.save(user);
		});
		// end transaction
	}
}
