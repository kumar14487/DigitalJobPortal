package com.dc.job.portal.dto;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.dc.job.portal.constant.Constants;

import lombok.Data;

@Data
public class ErrorDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = Constants.ERROR_CODE_NOT_NULL)
	private String errorCode;
	
	@NotEmpty(message = Constants.ERROR_CODE_NOT_EMPTY)
	private String errorMessage;
	private Map<String, String> errors;
}
