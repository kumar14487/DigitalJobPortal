package com.dc.job.portal.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.entity.Company;
import com.dc.job.portal.entity.JobLocation;
import com.dc.job.portal.entity.JobType;
import com.dc.job.portal.entity.SkillSet;
import com.dc.job.portal.entity.User;

import lombok.Data;

@Data
public class JobOpeningDto implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	private long jobOpeningId;
	
	
	@Valid
	@NotNull(message = Constants.USER_NOT_NULL)
	private User user;
	
	
	@Valid
	@NotNull(message = Constants.JOB_TYPE_NOT_NULL)
	private JobType jobType;
	
	
	@Valid
	@NotNull(message = Constants.COMPANY_NOT_NULL)
	private Company company;
	
	@Valid
	@NotNull(message = Constants.JOB_LOCATION_NOT_NULL)
	private List<JobLocation> jobLocation;
	
	
	@Valid
	@NotNull(message = Constants.JOB_SKILLS_NOT_NULL)
	private List<SkillSet> skills;
	
	private char isCompanyNameHidden;
	
	private Date createdDate;
	
	private String jobDescription;
	
	private char isActive;
	
	private int skillLevel;
	
	private String jobSalary;


}