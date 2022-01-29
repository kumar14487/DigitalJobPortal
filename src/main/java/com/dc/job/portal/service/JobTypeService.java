package com.dc.job.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.JobTypeDto;
import com.dc.job.portal.entity.JobType;

public interface JobTypeService {

	public List<JobType> create(ArrayList<JobType> jobType);

	public Optional<JobTypeDto> get(Long id);

	public List<JobTypeDto> getAll();

	public void delete(Long id);

}
