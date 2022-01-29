package com.dc.job.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.RoleDto;
import com.dc.job.portal.entity.Role;

public interface RoleService {
	
	public List<Role> create(ArrayList<Role> role);
	public Optional<RoleDto> get(Long id);
	public List<RoleDto> getAll();
	public void delete(Long id);

}
