package com.dc.job.portal.dto;

import java.io.Serializable;

import com.dc.job.portal.entity.JobOpening;

import lombok.Data;

@Data
public class LocationDto  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private JobOpening jobOpening;
	private String streetAddress;
	private String city;
	private String state;
	private String country;
	private String zip;

}
