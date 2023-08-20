package com.global.hr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.hr.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PutMapping("/role/{roleName}")
	public ResponseEntity<?> addRoleToAllUsers(@PathVariable String roleName) {
		
		service.addRoleForAllUsers(roleName);
		return ResponseEntity.ok(null);
	}
}
