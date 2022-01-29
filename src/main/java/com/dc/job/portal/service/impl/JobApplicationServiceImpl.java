package com.dc.job.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dc.job.portal.constant.Constants;
import com.dc.job.portal.dto.JobApplicationDto;
import com.dc.job.portal.entity.JobApplication;
import com.dc.job.portal.entity.JobOpening;
import com.dc.job.portal.entity.JobSeeker;
import com.dc.job.portal.exception.BusinessException;
import com.dc.job.portal.repository.JobApplicationRepository;
import com.dc.job.portal.repository.JobOpeningRepository;
import com.dc.job.portal.repository.JobSeekerRepository;
import com.dc.job.portal.service.JobApplicationService;
import com.google.gson.Gson;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {
	
	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
	@Autowired
	private JobOpeningRepository jobOpeningRepository;
	
	@Autowired
	Gson gson;

	@Override
	public void cancel(long jobAppId) {
		jobApplicationRepository.deleteById(new Long(jobAppId));
		
	}
	
	@Override
	public JobApplication updateJobApplication(JobApplication jobApplication,long jobId)throws BusinessException {
		JobApplication ja = new JobApplication();
		ja.setAppId(jobId);
		ja.setInterviewAccepted(jobApplication.isInterviewAccepted());
		ja.setJobOpening(jobApplication.getJobOpening());
		ja.setJobSeeker(jobApplication.getJobSeeker());
		ja.setInterviewFlag(jobApplication.isInterviewFlag());
		ja.setInterviewLocation(jobApplication.getInterviewLocation());
		ja.setInterviewTime(jobApplication.getInterviewTime());
		ja.setResume(jobApplication.isResume());
		ja.setResumePath(jobApplication.getResumePath());
		ja.setState(jobApplication.getState());
		return apply(ja);
	}

	@Override
	public JobApplication apply(JobApplication jobApplication) throws BusinessException {
		
		if(StringUtils.isEmpty(jobApplication.getJobOpening().getJobOpeningId())
				|| (jobApplication.getJobOpening().getJobOpeningId() == 0)) {
			throw new BusinessException(Constants.JOB_OPENING);
		}
			JobSeeker js = jobSeekerRepository.findById(new Long(jobApplication.getJobSeeker().getJobseekerId())).orElse(null);
			JobOpening jp = jobOpeningRepository.findByJobOpeningId(jobApplication.getJobOpening().getJobOpeningId());
			jobApplication.setJobSeeker(js);
			jobApplication.setJobOpening(jp);
			jobApplication.setResumePath(jobApplication.getResumePath());
			jobApplication.setState(0);
			jobApplication=jobApplicationRepository.save(jobApplication); 
 		
		return jobApplication;
	}

	@Override
	public Optional<JobApplication> getJobApplication(long jobAppId) {
		 return jobApplicationRepository.findById(new Long(jobAppId));
	}

	@Override
	public Optional<JobApplication> modifyJobApplicationStatus(int jobAppId, int state) {
		Optional<JobApplication> jobAppln = this.getJobApplication(jobAppId);
		
		JobApplication jobApplication=new JobApplication();
		if(jobAppln.isPresent()) {
			jobApplication.setState(state);
			jobApplication = jobApplicationRepository.saveAndFlush(jobApplication);
		}
		
		return Optional.of(jobApplication);
	}

	@Override
	public JobApplication updateApplication(JobApplication ja) {
		return jobApplicationRepository.saveAndFlush(ja);
	}
	
	private JobApplicationDto convertToJobApplicationDto(JobApplication jobApplication) {
		String jobApplicationDtoStr=gson.toJson(jobApplication);
		return gson.fromJson(jobApplicationDtoStr, JobApplicationDto.class);
	}


}
