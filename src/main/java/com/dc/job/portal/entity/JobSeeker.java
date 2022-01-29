/**
 * 
 */
package com.dc.job.portal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@Table(name = "jobseeker")
public class JobSeeker  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "job_seeker_id")
	private long jobseekerId;

	
	@Column(name = "workEx")
	private int workEx;

	@Column(name = "last_working_day")
	private Date lastWorkingDay;
	
	@OneToMany(mappedBy="jobSeeker", cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
	private List<EducationDetails> educationDetails;
	
	@OneToMany(mappedBy="jobSeeker", cascade = CascadeType.ALL)
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
	private List<ExperinaceDetails> experinceDetails;

	@Column(name = "notice_period")
	private int noticePeriod;
	
	@Column(name = "current_ctc")
	private double currentCtc;
	
	@Column(name = "expected_ctc")
	private double expectedCtc;
	
	@Column(name = "immediate_joiner")
	private double immediateJoiner;
	
	@Column(name = "offer_in_hand_cmp_name")
	private double offerInHandCmpName;
	
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "skillset", joinColumns = @JoinColumn(name = "id", referencedColumnName = "jobSeekerId"), inverseJoinColumns = @JoinColumn(name = "id"))
//	private List<SkillSet> skills;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "jobseeker_skills",
            joinColumns = {
                    @JoinColumn(name = "job_seeker_id", referencedColumnName = "job_seeker_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "skill_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	private List<SkillSet> skills;

	@Column(name = "verified")
	private boolean verified;

	@Column(name = "verification_code")
	private int verificationCode;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "jobseeker_interested_jobs", joinColumns = @JoinColumn(name = "jobSeekerId", referencedColumnName = "job_seeker_id"), inverseJoinColumns = @JoinColumn(name = "jobId"))
	private List<JobOpening> jobseekerInterestedjobs;
	
	@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
//	@OneToMany(mappedBy="jobSeeker", cascade = CascadeType.ALL)
//	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="appId")
//	private List<JobApplication> jobApplicationList;
	
	
	

	/**
	 * @return JobSeeker Id
	 */
	public long getJobseekerId() {
		return jobseekerId;
	}

	public Date getLastWorkingDay() {
		return lastWorkingDay;
	}

	public void setLastWorkingDay(Date lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public double getCurrentCtc() {
		return currentCtc;
	}

	public void setCurrentCtc(double currentCtc) {
		this.currentCtc = currentCtc;
	}

	public double getExpectedCtc() {
		return expectedCtc;
	}

	public void setExpectedCtc(double expectedCtc) {
		this.expectedCtc = expectedCtc;
	}

	

	public double getImmediateJoiner() {
		return immediateJoiner;
	}

	public void setImmediateJoiner(double immediateJoiner) {
		this.immediateJoiner = immediateJoiner;
	}

	public double getOfferInHandCmpName() {
		return offerInHandCmpName;
	}

	public void setOfferInHandCmpName(double offerInHandCmpName) {
		this.offerInHandCmpName = offerInHandCmpName;
	}

	/**
	 * @param jobseekerId
	 */
	public void setJobseekerId(long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	/**
	 * @return First Name
	 */
	
	/**
	 * @return Work Experience In Years
	 */
	public int getWorkEx() {
		return workEx;
	}

	/**
	 * @param workEx
	 */
	public void setWorkEx(int workEx) {
		this.workEx = workEx;
	}

	
	/**
	 * @return true if the user has been verified using the verification code
	 */
	public boolean isVerified() {
		return verified;
	}

	public List<EducationDetails> getEducationDetails() {
		return educationDetails;
	}

	public void setEducationDetails(List<EducationDetails> educationDetails) {
		this.educationDetails = educationDetails;
	}

	public List<ExperinaceDetails> getExperinceDetails() {
		return experinceDetails;
	}

	public void setExperinceDetails(List<ExperinaceDetails> experinceDetails) {
		this.experinceDetails = experinceDetails;
	}

	public List<SkillSet> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillSet> skills) {
		this.skills = skills;
	}

	/**
	 * @param verified
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	/**
	 * @return verificationCode
	 */
	public int getVerificationCode() {
		return verificationCode;
	}

	/**
	 * @param verificationCode
	 */
	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}

	public List<JobOpening> getJobseekerInterestedjobs() {
		return jobseekerInterestedjobs;
	}

	public void setJobseekerInterestedjobs(List<JobOpening> jobseekerInterestedjobs) {
		this.jobseekerInterestedjobs = jobseekerInterestedjobs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

//	public List<JobApplication> getJobApplicationList() {
//		return jobApplicationList;
//	}
//
//	public void setJobApplicationList(List<JobApplication> jobApplicationList) {
//		this.jobApplicationList = jobApplicationList;
//	}

}
