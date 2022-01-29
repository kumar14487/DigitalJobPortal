package com.dc.job.portal.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class RoleDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String roleName;
	
	private String roleDescription;

	}
