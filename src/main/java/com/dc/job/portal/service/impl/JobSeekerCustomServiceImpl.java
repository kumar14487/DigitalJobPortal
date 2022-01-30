package com.dc.job.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.JobSearchResponse;
import com.dc.job.portal.exception.BusinessException;
import com.dc.job.portal.repository.JobSeekerRepository;
import com.dc.job.portal.repository.impl.JobSeekerCustomRepositoryImpl;
import com.dc.job.portal.service.JobSeekerCustomService;
import com.google.gson.Gson;

@Service
public class JobSeekerCustomServiceImpl implements JobSeekerCustomService{
	
	@Autowired 
	JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	JobSeekerCustomRepositoryImpl jobSeekerRepositoryCustom;
	
	@Autowired
	Gson gson;

	@Override
	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max) throws BusinessException {
		return jobSeekerRepositoryCustom.searchJobs(  searchSkills, locations, companies, min, max);
	}

	

	@Override
	public List<String> passwordLookUp(String emailid) {
		return jobSeekerRepositoryCustom.passwordLookUp(emailid);
	}

	@Override
	public List<Long> getUserIdFromEmail(String emailid) {
		return jobSeekerRepositoryCustom.getUserIdFromEmail(emailid);
	}
	
	
}
