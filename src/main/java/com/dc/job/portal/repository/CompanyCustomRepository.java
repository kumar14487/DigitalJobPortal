package com.dc.job.portal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dc.job.portal.entity.Company;

@Repository
public interface CompanyCustomRepository {
	
	public List<String> passwordLookUp(String emailid);
	public List<Integer> getCompanyIdFromEmail(String emailid);
	public Company getCompany(int id);
	
}
