package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.JobTypeDto;
import com.dc.job.portal.entity.JobType;
import com.dc.job.portal.repository.JobTypeRepository;
import com.dc.job.portal.service.JobTypeService;
import com.google.gson.Gson;

@Service
public class JobTypeServiceImpl implements JobTypeService {

	@Autowired
	JobTypeRepository jobTypeRepository;

	@Autowired
	Gson gson;

	public List<JobType> create(ArrayList<JobType> jobType) {
		return jobTypeRepository.saveAll(jobType);
		
	}

	private JobTypeDto convertToJobTypeDto(JobType JobType) {
		String jobTypeDtoStr = gson.toJson(JobType);
		return gson.fromJson(jobTypeDtoStr, JobTypeDto.class);
	}

	private ArrayList<JobType> convertToJobType(ArrayList<JobTypeDto> jobTypeDto) {
		String jobTypeStr = gson.toJson(jobTypeDto);
		return gson.fromJson(jobTypeStr, ArrayList.class);
	}

	public Optional<JobTypeDto> get(Long id) {
		Optional<JobType> JobType = jobTypeRepository.findById(id);
		if (JobType.isPresent()) {
			return Optional.of(convertToJobTypeDto(JobType.get()));
		} else {
			return Optional.empty();
		}

	}

	public List<JobTypeDto> getAll() {
		List<JobType> jobTypeList = jobTypeRepository.findAll();
		List<JobTypeDto> jobTypeDtoList = new ArrayList<>();
		if (!jobTypeList.isEmpty()) {
			jobTypeDtoList=jobTypeList.stream().map(x->convertToJobTypeDto(x)).collect(Collectors.toList());
		}

		return jobTypeDtoList;

	}

	public void delete(Long id) {
		jobTypeRepository.deleteById(id);
	}

}
