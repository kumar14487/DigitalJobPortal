package com.dc.job.portal.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dc.job.portal.constant.Constants;

import lombok.Data;

/**
 * @author raj14487
 *
 */
@Data
public class JobPostingDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private int jobId;

	@NotNull(message = Constants.COMPANY_NOT_NULL)
	private CompanyDto company;

	@NotEmpty(message = Constants.JOB_STATE_NOT_NULL)
	private int state;

	@NotNull(message = Constants.COMPANY_NOT_NULL)
	private String title;

	private String description;

	private String responsibilties;

	@NotNull(message = Constants.COMPANY_NOT_NULL)
	private String location;

	private String salary;

	private String keywords;

	
}