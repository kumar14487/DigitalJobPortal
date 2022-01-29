package com.dc.job.portal.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.entity.Role;

import lombok.Data;

@Data
public class UserDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	@NotEmpty(message = Constants.USER_FIRST_NAME) 
	private String firstName;

	private String lastName;

	
	@Email
	private String emailId;

	@NotEmpty(message = Constants.PWD)
	private String pwd;
	
	private String gender;
	
	@NotEmpty(message = Constants.MOBILE_NUMBER)
	private String mobileNumber;
	
	private Date registrationDate;
	
	private String isActive;
	
	private String userImage;
	
	private char smsNotificationAlert;
	
	private char  emailNotificationAlert;
	
	private Role role;
	

}
