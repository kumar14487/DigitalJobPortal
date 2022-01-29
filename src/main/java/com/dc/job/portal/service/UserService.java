package com.dc.job.portal.service;

import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.UserDto;
import com.dc.job.portal.entity.User;

public interface UserService {
	
	public UserDto create(UserDto user);
	public Optional<UserDto> get(Long id);
	public List<UserDto> getAll();
	public void delete(Long id);
	public List<User> passwordLookUp(String emailId,String userPd);

}
