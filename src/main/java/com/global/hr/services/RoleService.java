package com.global.hr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.hr.Reposatories.RoleRepo;
import com.global.hr.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepo roleRepo;
	
	public Role findById(Long id) {
		return roleRepo.findById(id).orElseThrow();
	}
	
	public Role insert(Role Role) {
		return roleRepo.save(Role);
	}
	
	public Role update(Role role) {
		Role current= roleRepo.findById(role.getId()).get();
		current.setName(role.getName());
		return roleRepo.save(current);
	}
	
	public Role findByName(String name) {
		return roleRepo.findByName(name);
	}

	public List<Role> findAll(){
		return roleRepo.findAll();
	}
	
}
