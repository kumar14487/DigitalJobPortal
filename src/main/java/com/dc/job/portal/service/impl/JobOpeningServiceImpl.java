package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.JobOpeningDto;
import com.dc.job.portal.entity.JobLocation;
import com.dc.job.portal.entity.JobOpening;
import com.dc.job.portal.entity.SkillSet;
import com.dc.job.portal.repository.JobOpeningRepository;
import com.dc.job.portal.repository.SkillRepository;
import com.dc.job.portal.repository.UserRepository;
import com.dc.job.portal.service.JobOpeningService;
import com.google.gson.Gson;

@Service
public class JobOpeningServiceImpl implements JobOpeningService{
	
	@Autowired 
	JobOpeningRepository jobOpeningRepository;
	
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	SkillRepository skillRepository;
	
	
	
	@Autowired
	Gson gson;
	
	
	public JobOpeningDto createOrUpdateJobOpening(JobOpeningDto jobOpeningDto) {
		JobOpening jobOpening=convertToJobOpening(jobOpeningDto);
		setJobOpening(jobOpening);
		jobOpening.setPostedBy(userRepository.findById((long) jobOpening.getCompany().getCompanyId()).get());
		setSkillSet(jobOpening);
		jobOpening= jobOpeningRepository.saveAndFlush(jobOpening);
		JobOpeningDto jobOpeningReturnDto=new JobOpeningDto();
		jobOpeningReturnDto.setJobOpeningId(jobOpening.getJobOpeningId());
		return jobOpeningReturnDto;
	}
	
	private void setSkillSet(JobOpening jobOpening)
	{
		List<SkillSet> listOfSkills=jobOpening.getSkills();
		if(listOfSkills!=null && !listOfSkills.isEmpty())
		{
			jobOpening.setSkills(listOfSkills.stream().map(i->skillRepository.findById(i.getId()).get()).collect(Collectors.toList()));
		}
		
	}
	
	public void setJobOpening(JobOpening jobOpening)
	{
		Set<JobLocation> listOfJobLocation=jobOpening.getJobLocation();
		if(listOfJobLocation!=null && !listOfJobLocation.isEmpty())
		{
			for (JobLocation jobLocation : listOfJobLocation) {
				jobLocation.setJobOpening(jobOpening);
			}
		}
		
	}

	private JobOpeningDto convertToJobOpeningDto(JobOpening jobOpening) {
		String jobOpeningDtoStr=gson.toJson(jobOpening);
		return gson.fromJson(jobOpeningDtoStr, JobOpeningDto.class);
	}
	
	private JobOpening convertToJobOpening(JobOpeningDto jobOpeningDto) {
		String jobOpeningStr=gson.toJson(jobOpeningDto);
		return gson.fromJson(jobOpeningStr, JobOpening.class);
	}

	
	
	public Optional<JobOpening> get(Long id)
	{
		return jobOpeningRepository.findById(id);
	}
	
	public List<JobOpeningDto> getAll()
	{
		List<JobOpening> jobOpeningList= jobOpeningRepository.findAll();
		List<JobOpeningDto> jobOpeningDtoList=new ArrayList<>();
		if(null!=jobOpeningList && !jobOpeningList.isEmpty())
		{
			jobOpeningDtoList= jobOpeningList.stream().map(x->convertToJobOpeningDto(x)).collect(Collectors.toList());
		}
		return jobOpeningDtoList;
	}
	

	@Override
	public void delete(Long id) {
		jobOpeningRepository.deleteById(id);
	}

}
