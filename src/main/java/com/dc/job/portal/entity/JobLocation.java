/**
 * 
 */
package com.dc.job.portal.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

/**
 * @author raj14487
 *
 */
@Entity
@Table(name = "job_location")
public class JobLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "job_location_id", unique = true, nullable = false)
	private int jobLocationId;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name="job_opening", nullable=false)
	private JobOpening jobOpening;
	
	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "zip")
	private String zip;
	
	public int getJobLocationId() {
		return jobLocationId;
	}
	public void setJobLocationId(int jobLocationId) {
		this.jobLocationId = jobLocationId;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public JobOpening getJobOpening() {
		return jobOpening;
	}
	public void setJobOpening(JobOpening jobOpening) {
		this.jobOpening = jobOpening;
	}
}