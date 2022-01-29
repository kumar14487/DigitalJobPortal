package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.RoleDto;
import com.dc.job.portal.entity.Role;
import com.dc.job.portal.repository.RoleRepository;
import com.dc.job.portal.service.RoleService;
import com.google.gson.Gson;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired 
	RoleRepository roleRepository;
	
	@Autowired
	Gson gson;
	
	
	public List<Role> create(ArrayList<Role> role) {
		 return roleRepository.saveAll(role);
	}

	private RoleDto convertToRoleDto(Role role) {
		String roleDtoStr=gson.toJson(role);
		return gson.fromJson(roleDtoStr, RoleDto.class);
	}
	
	private Role convertToRole(RoleDto roleDto) {
		String roleStr=gson.toJson(roleDto);
		return gson.fromJson(roleStr, Role.class);
	}
	
	public Optional<RoleDto> get(Long id)
	{
		Optional<Role>  role=roleRepository.findById(id);
		if(role.isPresent()) {
			return Optional.of(convertToRoleDto(role.get()));
		}else
		{
			return Optional.empty();
		}
		
	}
	
	public List<RoleDto> getAll()
	{
		List<Role> roleList= roleRepository.findAll();
		List<RoleDto> roleDtoList=new ArrayList<>();
		if(!roleList.isEmpty())
		{
			roleDtoList=roleList.stream().map(x->convertToRoleDto(x)).collect(Collectors.toList());
		}
		return roleDtoList;
		
	}
	
	public void delete(Long id)
	{
		roleRepository.deleteById(id);
	}

}
