package com.dc.job.portal.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dc.job.portal.dto.JobSearchResponse;

@Repository
public interface JobSeekerCustomRepository  {
	
	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max);
	
	public List<String> passwordLookUp(String emailid);
	
	public List<Long> getUserIdFromEmail(String emailid);
	
	
	
}
