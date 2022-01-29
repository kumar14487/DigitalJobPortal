/**
 * 
 */
package com.dc.job.portal.entity;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author raj14487
 *
 */
@Entity
@Table(name = "jobapplication")
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "app_id", unique = true, nullable = false)
	private long appId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="job_id")
	private JobOpening jobOpening;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="job_seeker_id", nullable = false)
	@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="jobSeekerId")
	private JobSeeker jobSeeker;
	
	@Column(name="resume")
	private boolean resume;
	
	@Column(name="resume_path")
	private String resumePath;
	
	@Column(name="state")
	private int state;
	
	@Column(name="interview_flag")
	private boolean interviewFlag;
	
	@Column(name="interview_location")
	private String interviewLocation;
	
	@Column(name="interview_time")
	private Date interviewTime;
	
	@Column(name="interview_accepted")
	private boolean interviewAccepted;

	/**
	 * @return Application ID
	 */
	public long getAppId() {
		return appId;
	}

	/**
	 * @param appId
	 */
	public void setAppId(long appId) {
		this.appId = appId;
	}



	public JobOpening getJobOpening() {
		return jobOpening;
	}

	public void setJobOpening(JobOpening jobOpening) {
		this.jobOpening = jobOpening;
	}

	/**
	 * @return JobSeeker
	 */
	@JsonProperty
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	/**
	 * @param jobSeeker
	 */
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	/**
	 * @return True if applied through resume
	 */
	public boolean isResume() {
		return resume;
	}

	/**
	 * @param resume
	 */
	public void setResume(boolean resume) {
		this.resume = resume;
	}

	/**
	 * @return Resume path
	 */
	public String getResumePath() {
		return resumePath;
	}

	/**
	 * @param resumePath
	 */
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	/**
	 * @return State of the application
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}

	public boolean isInterviewFlag() {
		return interviewFlag;
	}

	public void setInterviewFlag(boolean interviewFlag) {
		this.interviewFlag = interviewFlag;
	}

	public String getInterviewLocation() {
		return interviewLocation;
	}

	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}

	public Date getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}

	public boolean isInterviewAccepted() {
		return interviewAccepted;
	}

	public void setInterviewAccepted(boolean interviewAccepted) {
		this.interviewAccepted = interviewAccepted;
	}

}
