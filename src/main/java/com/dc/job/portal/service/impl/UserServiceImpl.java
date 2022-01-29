package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.UserDto;
import com.dc.job.portal.entity.User;
import com.dc.job.portal.repository.UserRepository;
import com.dc.job.portal.service.UserService;
import com.google.gson.Gson;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	Gson gson;
	
	
	public UserDto create(UserDto userDto) {
		User user=convertToUser(userDto);
		 user= userRepository.save(user);
		 UserDto roleDtoReturn=convertToUserDto(user);
		 roleDtoReturn.setPwd("");
		return roleDtoReturn;
	}

	private UserDto convertToUserDto(User user) {
		String userDtoStr=gson.toJson(user);
		return gson.fromJson(userDtoStr, UserDto.class);
	}
	
	private User convertToUser(UserDto userDto) {
		String userStr=gson.toJson(userDto);
		return gson.fromJson(userStr, User.class);
	}
	
	public Optional<UserDto> get(Long id)
	{
		Optional<User>  user=userRepository.findById(id);
		if(user.isPresent()) {
			return Optional.of(convertToUserDto(user.get()));
		}else
		{
			return Optional.empty();
		}
		
	}
	
	public List<UserDto> getAll()
	{
		List<User> userList= userRepository.findAll();
		List<UserDto> userDtoList=new ArrayList<>();
		if(!userList.isEmpty())
		{
			userDtoList=userList.stream().map(x->convertToUserDto(x)).collect(Collectors.toList());
		}
		return userDtoList;
		
	}
	
	public void delete(Long id)
	{
		userRepository.deleteById(id);
	}

	@Override
	public List<User> passwordLookUp(String emailId, String userPwd) {
		return userRepository.findByEmailIdAndPwd(emailId, userPwd);
	}

}
