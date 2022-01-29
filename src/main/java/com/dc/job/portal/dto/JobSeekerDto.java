package com.dc.job.portal.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.entity.EducationDetails;
import com.dc.job.portal.entity.ExperinaceDetails;
import com.dc.job.portal.entity.JobOpening;
import com.dc.job.portal.entity.SkillSet;
import com.dc.job.portal.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class JobSeekerDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private long jobseekerId;
	
	@JsonInclude(Include. NON_NULL)
	private int workEx;
	
	@JsonInclude(Include. NON_NULL)
	private Date lastWorkingDay;
	
	@JsonInclude(Include. NON_NULL)
	private List<EducationDetails> educationDetails;
	
	@JsonInclude(Include. NON_NULL)
	private List<ExperinaceDetails> experinceDetails;

	@JsonInclude(Include. NON_NULL)
	private int noticePeriod;
	
	@JsonInclude(Include. NON_NULL)
	private double currentCtc;
	
	@JsonInclude(Include. NON_NULL)
	private double expectedCtc;
	
	@JsonInclude(Include. NON_NULL)
	private double immediateJoiner;
	
	@JsonInclude(Include. NON_NULL)
	private double offerInHandCmpName;
	
	@JsonInclude(Include. NON_NULL)
	@Valid
	@NotEmpty(message = Constants.JOB_SEEKER_SKILLS) 
	private List<SkillSet> skills;

	@JsonInclude(Include. NON_NULL)
	private boolean verified;

	@JsonInclude(Include. NON_NULL)
	private int verificationCode;

	@JsonInclude(Include. NON_NULL)
	private List<JobOpening> interestedjobs;
	
	@JsonInclude(Include. NON_NULL)
	private User user;
}
