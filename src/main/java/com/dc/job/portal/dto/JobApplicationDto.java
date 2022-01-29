/**
 * 
 */
package com.dc.job.portal.dto;
import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.entity.JobOpening;
import com.dc.job.portal.entity.JobSeeker;

import lombok.Data;

/**
 * @author raj14487
 *
 */
@Data
public class JobApplicationDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long appId;
	
	@NotNull(message = Constants.JOB_OPENING_NOT_NULL)
	private JobOpening jobposting;
	
	@NotNull(message = Constants.JOB_SEEKER_NOT_NULL)
	private JobSeeker jobSeeker;
	
	private boolean resume;
	
	private String resumePath;
	
	private int state;
	
	private boolean interviewFlag;
	
	private String interviewLocation;
	
	private Date interviewTime;
	
	private boolean interviewAccepted;

	
}
