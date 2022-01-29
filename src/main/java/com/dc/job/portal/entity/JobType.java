/**
 * 
 */
package com.dc.job.portal.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author raj14487
 *
 */
@Entity
@Table(name = "job_type")
public class JobType  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "job_type_id", unique = true, nullable = false)
	private int jobTypeId;

	@Column(name = "job_type")
	private String jobType;

	public int getJobTypeId() {
		return jobTypeId;
	}

	public void setJobTypeId(int jobTypeId) {
		this.jobTypeId = jobTypeId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	

}