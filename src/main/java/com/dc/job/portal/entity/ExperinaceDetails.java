package com.dc.job.portal.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

@Entity
@Table(name = "experincedetails")
public class ExperinaceDetails  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@ManyToOne
	@Lazy
	@JoinColumn(name="job_seeker_id")
	private JobSeeker jobSeeker;
	
	
	@Column(name = "is_current_job")
	private boolean isCurrentJob;
	
	@Column(name = "job_tile")
	private String jobTitle;
	
	@Column(name = "company_name")
	private String companyName;
	
	
	@Column(name = "job_location_city")
	private String jobLocationCity;
	
	@Column(name = "job_location_state")
	private String jobLocationState;
	
	@Column(name = "job_location_country")
	private String jobLocationCountry;
	
	
	@Column(name = "description")
	private String description;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isCurrentJob() {
		return isCurrentJob;
	}


	public void setCurrentJob(boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}


	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getJobLocationCity() {
		return jobLocationCity;
	}


	public void setJobLocationCity(String jobLocationCity) {
		this.jobLocationCity = jobLocationCity;
	}


	public String getJobLocationState() {
		return jobLocationState;
	}


	public void setJobLocationState(String jobLocationState) {
		this.jobLocationState = jobLocationState;
	}


	public String getJobLocationCountry() {
		return jobLocationCountry;
	}


	public void setJobLocationCountry(String jobLocationCountry) {
		this.jobLocationCountry = jobLocationCountry;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}


	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
	

	
	
	

}
