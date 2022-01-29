package com.dc.job.portal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_id",unique = true)
	private String emailId;

	@Column(name = "password")
	private String pwd;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "registration_date")
	private Date registrationDate;
	
	@Column(name = "is_active")
	private String isActive;
	
	@Column(name = "userImage")
	private String userImage;
	
	@Column(name = "sms_notification_alert")
	private char smsNotificationAlert;
	
	@Column(name = "email_notification_alert")
	private char  emailNotificationAlert;
	
	
	
	

	@OneToOne
	@JoinColumn(name="role_id",nullable = false)
	private Role role;
	

	public char getEmailNotificationAlert() {
		return emailNotificationAlert;
	}

	public void setEmailNotificationAlert(char emailNotificationAlert) {
		this.emailNotificationAlert = emailNotificationAlert;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}


	public char getSmsNotificationAlert() {
		return smsNotificationAlert;
	}

	public void setSmsNotificationAlert(char smsNotificationAlert) {
		this.smsNotificationAlert = smsNotificationAlert;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	


}
