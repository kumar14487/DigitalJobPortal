package com.dc.job.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.entity.Company;
import com.dc.job.portal.repository.CompanyCustomRepository;
import com.dc.job.portal.service.CompanyCustomService;
import com.google.gson.Gson;

@Service
public class CompanyCustomServiceImpl implements CompanyCustomService{
	
	@Autowired
	CompanyCustomRepository companyCustomRepository;
	
	@Autowired
	Gson gson;

	

	@Override
	public List<Integer> getCompanyIdFromEmail(String emailid) {
		return companyCustomRepository.getCompanyIdFromEmail(emailid);
	}

	@Override
	public Company getCompany(int id) {
		return companyCustomRepository.getCompany(id);
	}
	
	
}
