package com.dc.job.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.dto.UserDto;
import com.dc.job.portal.entity.User;
import com.dc.job.portal.exception.InvalidCredentialsException;
import com.dc.job.portal.service.JobSeekerCustomService;
import com.dc.job.portal.service.JobSeekerService;
import com.dc.job.portal.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	JobSeekerCustomService jobSeekerCustomService;

	@Autowired
	JobSeekerService jobSeekerService;

	@PostMapping(value = "/login")
	public UserDto login(@Valid @RequestBody UserDto userDto) throws InvalidCredentialsException {
		List<User> list = new ArrayList<User>();
		list = userService.passwordLookUp(userDto.getEmailId(), userDto.getPwd());
		if (list.size() == 0) {
			throw new InvalidCredentialsException(Constants.INVALID_CREDENTIALS);
		}
		userDto.setPwd("");
		return userDto;

	}

}
