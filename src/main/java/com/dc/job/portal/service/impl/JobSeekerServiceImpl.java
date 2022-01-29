package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.JobSeekerDto;
import com.dc.job.portal.entity.EducationDetails;
import com.dc.job.portal.entity.ExperinaceDetails;
import com.dc.job.portal.entity.JobSeeker;
import com.dc.job.portal.repository.JobSeekerRepository;
import com.dc.job.portal.repository.SkillRepository;
import com.dc.job.portal.repository.UserRepository;
import com.dc.job.portal.repository.impl.JobSeekerCustomRepositoryImpl;
import com.dc.job.portal.service.JobSeekerService;
import com.google.gson.Gson;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

	@Autowired
	JobSeekerRepository jobSeekerRepository;
	
	
	@Autowired
	SkillRepository skillRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JobSeekerCustomRepositoryImpl jobSeekerRepositoryCustom;

	@Autowired
	Gson gson;

	public JobSeekerDto createOrUpdateJobSeekerProfile(JobSeekerDto jobSeekerDto) {
		JobSeeker jobSeeker = convertToJobSeeker(jobSeekerDto);
		jobSeeker.setUser(userRepository.findById(jobSeeker.getUser().getId()).get());
		setEducationDetails(jobSeeker);
		setExperinceDetails(jobSeeker);
		setSkillDetails(jobSeeker);
		jobSeeker=jobSeekerRepository.save(jobSeeker);
		JobSeekerDto jobSeekerDtoReturn=new JobSeekerDto();
		jobSeekerDtoReturn.setJobseekerId(jobSeeker.getJobseekerId());
		return jobSeekerDtoReturn;
	}
	
	private void setSkillDetails(JobSeeker jobSeeker) {
		if(jobSeeker.getSkills()!=null && !jobSeeker.getSkills().isEmpty())
		{
			jobSeeker.setSkills(jobSeeker.getSkills().stream().map(x -> skillRepository.findById(x.getId()).get()).collect(Collectors.toList()));
		}
	}
	

	private void setEducationDetails(JobSeeker jobSeeker) {
		
		if(jobSeeker.getEducationDetails()!=null)
		{
			for (EducationDetails  educationDetails: jobSeeker.getEducationDetails()) {
				educationDetails.setJobSeeker(jobSeeker);
			}
		}
	}
	
    private void setExperinceDetails(JobSeeker jobSeeker) {
		
		if(jobSeeker.getExperinceDetails()!=null)
		{
			for (ExperinaceDetails  experinceDetails: jobSeeker.getExperinceDetails()) {
				experinceDetails.setJobSeeker(experinceDetails.getJobSeeker());
			}
		}
	}


	private JobSeekerDto convertToJobSeekerDto(JobSeeker jobSeeker) {
		String jobSeekerDtoStr = gson.toJson(jobSeeker);
		return gson.fromJson(jobSeekerDtoStr, JobSeekerDto.class);
	}

	private JobSeeker convertToJobSeeker(JobSeekerDto jobSeekerDto) {
		String jobSeekerStr = gson.toJson(jobSeekerDto);
		return gson.fromJson(jobSeekerStr, JobSeeker.class);
	}

	public Optional<JobSeekerDto> get(Long id) {
		Optional<JobSeeker> jobSeeker = jobSeekerRepository.findById(id);
		if (jobSeeker.isPresent()) {
			return Optional.of(convertToJobSeekerDto(jobSeeker.get()));
		} else {
			return Optional.empty();
		}
	}

	public List<JobSeekerDto> getAll() {
		List<JobSeeker> jobSeekersList = jobSeekerRepository.findAll();
		List<JobSeekerDto> jobSeekersDtoList = new ArrayList<>();
		if (!jobSeekersList.isEmpty()) {
			jobSeekersDtoList=jobSeekersList.stream().map(x->convertToJobSeekerDto(x)).collect(Collectors.toList());
		}
		return jobSeekersDtoList;
	}

	public void delete(Long id) {
		jobSeekerRepository.deleteById(id);
	}

	public JobSeeker findByJobseekerId(long id) {
		return jobSeekerRepository.findByJobseekerId(id);
	}
}
