package com.dc.job.portal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.CompanyDto;
import com.dc.job.portal.entity.Company;
import com.dc.job.portal.repository.CompanyRepository;
import com.dc.job.portal.service.CompanyService;
import com.google.gson.Gson;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired 
	CompanyRepository companyRepository;
	
	@Autowired
	Gson gson;
	
	
	public CompanyDto createOrUpdateCompany(CompanyDto companyDto) {
		Company company=convertToCompany(companyDto);
		company= companyRepository.saveAndFlush(company);
		 return convertToCompanyDto(company);
	}

	private CompanyDto convertToCompanyDto(Company company) {
		String companyDtoStr=gson.toJson(company);
		return gson.fromJson(companyDtoStr, CompanyDto.class);
	}
	
	private Company convertToCompany(CompanyDto companyDto) {
		String companyStr=gson.toJson(companyDto);
		return gson.fromJson(companyStr, Company.class);
	}
	
	public Optional<Company> get(Long id)
	{
		return companyRepository.findById(id);		
	}
	
	public List<Company> getAll()
	{
		return companyRepository.findAll();
	}
	
	public void delete(Long id)
	{
		companyRepository.deleteById(id);
	}

	

}
