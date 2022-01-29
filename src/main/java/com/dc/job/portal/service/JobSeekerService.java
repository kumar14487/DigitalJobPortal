package com.dc.job.portal.service;

import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.JobSeekerDto;
import com.dc.job.portal.entity.JobSeeker;

public interface JobSeekerService {
	
	public JobSeekerDto createOrUpdateJobSeekerProfile(JobSeekerDto company);
	public Optional<JobSeekerDto> get(Long id);
	public List<JobSeekerDto> getAll();
	public void delete(Long id);
	public JobSeeker findByJobseekerId(long id);

}
