package com.dc.job.portal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dc.job.portal.entity.JobApplication;
import com.dc.job.portal.exception.BusinessException;
import com.dc.job.portal.service.JobApplicationService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/application")
public class JobApplicationController {
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@GetMapping("/{jobId}")
	public Optional<JobApplication> getJobApplication(@PathVariable long jobId) {
		return jobApplicationService.getJobApplication(jobId);
		
	}
	
	@PostMapping("/apply")
	public JobApplication createJobApplication(@RequestBody JobApplication jobApplication)throws BusinessException {
		
		return jobApplicationService.apply(jobApplication);
		
	}
	
	@PostMapping("/cancel/{jobId}")
	public void cancel(@PathVariable long jobId) {
		jobApplicationService.cancel(jobId);
		
	}
	
	@PutMapping("/{jobId}")
	public JobApplication updateJobApplication(@RequestBody JobApplication jobApplication,@PathVariable long jobId) throws BusinessException  {
		return jobApplicationService.updateJobApplication(jobApplication,jobId);
		
	}
	
	@DeleteMapping("/delete/{jobId}")
	public void deleteApplication(@PathVariable long jobId) {
		jobApplicationService.cancel(jobId);
	}
	

}
