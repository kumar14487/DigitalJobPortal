package com.dc.job.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.job.portal.dto.RoleDto;
import com.dc.job.portal.entity.Role;
import com.dc.job.portal.service.RoleService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("/role")
	public ResponseEntity<List<RoleDto>> getAllRoles() {
			return new ResponseEntity<>(roleService.getAll(), HttpStatus.OK);
		
	}

	@GetMapping("/role/{id}")
	public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") long id) {
		Optional<RoleDto> roleData = roleService.get(id);
		if (roleData.isPresent()) {
			return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/role")
	public ResponseEntity<List<Role>> createRole(@Valid @RequestBody ArrayList<Role> role) {
			return new ResponseEntity<List<Role>>(roleService
					.create(role), HttpStatus.CREATED);
		
	}

	@PutMapping("/role/{id}")
	public ResponseEntity<List<Role>> updateRole(@PathVariable("id") long id, @RequestBody ArrayList<Role> role) {
		List<Role> roleData = roleService
				.create(role);
		if (roleData!=null) {
			return new ResponseEntity<>(roleData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/role/{id}")
	public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") long id) {
			roleService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
}
