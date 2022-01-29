package com.dc.job.portal.service;

import java.util.List;

import com.dc.job.portal.dto.JobSearchResponse;

public interface JobSeekerCustomService {

	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max);
    public List<String> passwordLookUp(String emailid);
	public List<Long> getUserIdFromEmail(String emailid);

}
