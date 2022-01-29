/**
 * 
 */
package com.dc.job.portal.dto;



import javax.validation.constraints.NotEmpty;

import com.dc.job.portal.constant.Constants;

import lombok.Data;

/**
 * @author raj14487
 *
 */
@Data
public class CompanyDto {

	
	private int companyId;
	
	@NotEmpty(message = Constants.COMPANY_NAME_NOT_EMPTY) 
	private String companyName;

	
	@NotEmpty(message = Constants.COMPANY_HEAD_QUARTERS_NOT_EMPTY) 
	private String headQuarters;
	
	private String description;
	
	

	
	
}
