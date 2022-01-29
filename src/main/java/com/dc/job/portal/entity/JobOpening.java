/**
 * 
 */
package com.dc.job.portal.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author raj14487
 *
 */
@Entity
@Table(name = "job_opening")
public class JobOpening implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "job_opening_id", unique = true, nullable = false)
	private long jobOpeningId;
	
	
	
	@OneToOne
	@JoinColumn(name="job_type_id", nullable=false)
	private JobType jobType;
	
	@OneToOne
	@JoinColumn(name="company_id", nullable=false)
	private Company company;
	
	@OneToOne
	@JoinColumn(name="posted_by", nullable=false)
	private User postedBy;
	
	@OneToMany(mappedBy="jobOpening", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="job_location_id")
	private Set<JobLocation> jobLocation;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "jobopening_skillset",
            joinColumns = {
                    @JoinColumn(name = "job_opening_id", referencedColumnName = "job_opening_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "skill_set_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private List<SkillSet> skills;
	
	@Column(name="is_company_name_hidden")
	private char isCompanyNameHidden;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="job_description")
	private String jobDescription;
	
	@Column(name="is_active")
	private char isActive;
	
	@Column(name="skill_level")
	private int skillLevel;
	
	@Column(name="job_salary")
	private String jobSalary;
	


	public String getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}

	public long getJobOpeningId() {
		return jobOpeningId;
	}

	public void setJobOpeningId(long jobOpeningId) {
		this.jobOpeningId = jobOpeningId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public char getIsCompanyNameHidden() {
		return isCompanyNameHidden;
	}

	

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Set<JobLocation> getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(Set<JobLocation> jobLocation) {
		this.jobLocation = jobLocation;
	}

	public void setIsCompanyNameHidden(char isCompanyNameHidden) {
		this.isCompanyNameHidden = isCompanyNameHidden;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}

	public List<SkillSet> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillSet> skills) {
		this.skills = skills;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}
	
}