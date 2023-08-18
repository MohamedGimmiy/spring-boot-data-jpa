package com.global.hr.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.hr.entity.Role;
import com.global.hr.entity.User;
import com.global.hr.services.RoleService;
import com.global.hr.services.UserService;


@Component
public class AppStartUp implements CommandLineRunner {

	@Autowired
	private UserService userservice;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(userservice.findAll().isEmpty()) {
			
			// create role
			Role role = new Role();
			role.setName("Admin");
			
			Role role2 = new Role();
			role2.setName("user");
			
			roleService.insert(role);
			roleService.insert(role2);
			
			Set<Role> adminRoles = new HashSet<Role>();
			adminRoles.add(role);
			
			
			Set<Role> userroles = new HashSet<Role>();
			userroles.add(role2);
			
			
			// create users
			User user = new User();
			user.setUsername("mido30");
			user.setPassword("asd1234");
			user.setRoles(adminRoles);
			
			userservice.insert(user);
			
			User user2 = new User();
			user2.setUsername("mido100");
			user2.setPassword("asd1234");
			user2.setRoles(userroles);
			userservice.insert(user2);
		}

	 }

}
