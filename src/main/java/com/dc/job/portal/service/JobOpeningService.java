package com.dc.job.portal.service;

import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.JobOpeningDto;
import com.dc.job.portal.entity.JobOpening;

public interface JobOpeningService {
	
	public JobOpeningDto createOrUpdateJobOpening(JobOpeningDto company);
	public Optional<JobOpening> get(Long id);
	public List<JobOpeningDto> getAll();
	public void delete(Long id);

}
