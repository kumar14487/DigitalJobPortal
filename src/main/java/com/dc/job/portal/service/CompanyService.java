package com.dc.job.portal.service;

import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.CompanyDto;
import com.dc.job.portal.entity.Company;

public interface CompanyService {
	
	public CompanyDto createOrUpdateCompany(CompanyDto company);
	public Optional<Company> get(Long id);
	public List<Company> getAll();
	public void delete(Long id);

}
