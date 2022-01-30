package com.dc.job.portal.service;

import java.util.List;

import com.dc.job.portal.dto.JobSearchResponse;
import com.dc.job.portal.exception.BusinessException;

public interface JobSeekerCustomService {

	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max)throws BusinessException;
    public List<String> passwordLookUp(String emailid);
	public List<Long> getUserIdFromEmail(String emailid);

}
