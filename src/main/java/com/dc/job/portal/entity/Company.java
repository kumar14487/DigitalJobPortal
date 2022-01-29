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
@Table(name = "company")
public class Company  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "company_id", unique = true, nullable = false)
	private int companyId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="head_quarters")
	private String headQuarters;
	
	
	@Column(name="description")
	private String description;

	@Column(name="verified")
	private boolean verified;
	
	@Column(name="verification_code")
	private int verificationCode;
	
	
	
	/**
	 * @return CompanyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return Company Name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return Headquarters of the company
	 */
	public String getHeadQuarters() {
		return headQuarters;
	}

	/**
	 * @param headquarters
	 */
	public void setHeadQuarters(String headQuarters) {
		this.headQuarters = headQuarters;
	}

	
	/**
	 * @return Description of the company
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            of the company
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isVerified() {
		return verified;
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

}
