package com.dc.job.portal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dc.job.portal.dto.JobSearchResponse;
import com.dc.job.portal.exception.BusinessException;

@Repository
public interface JobSeekerCustomRepository  {
	
	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max)throws BusinessException;
	
	public List<String> passwordLookUp(String emailid);
	
	public List<Long> getUserIdFromEmail(String emailid);
	
	
	
}
