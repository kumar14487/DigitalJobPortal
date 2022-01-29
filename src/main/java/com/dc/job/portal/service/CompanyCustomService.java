package com.dc.job.portal.service;

import java.util.List;

import com.dc.job.portal.entity.Company;

public interface CompanyCustomService {
	
	public List<Integer> getCompanyIdFromEmail(String emailid);
	public Company getCompany(int id);

}
