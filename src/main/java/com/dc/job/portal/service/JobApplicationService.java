package com.dc.job.portal.service;

import java.util.Optional;

import com.dc.job.portal.entity.JobApplication;
import com.dc.job.portal.exception.BusinessException;

public interface JobApplicationService {
	
	void cancel(long jobAppId);
	
	JobApplication apply(JobApplication jobApplication) throws BusinessException ;
	
	public JobApplication updateJobApplication(JobApplication jobApplication,long jobId) throws BusinessException;

//	JobApplication apply(int jobseekerId, int jobId, boolean resumeFlag, String resumePath);

	Optional<JobApplication> getJobApplication(long jobAppId);

	Optional<JobApplication> modifyJobApplicationStatus(int jobAppId, int state);
	
	JobApplication updateApplication(JobApplication ja);
}
