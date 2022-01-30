package com.dc.job.portal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dc.job.portal.dto.JobSearchResponse;
import com.dc.job.portal.dto.JobSeekerDto;
import com.dc.job.portal.service.JobSeekerCustomService;
import com.dc.job.portal.service.JobSeekerService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobSeekerController {

	@Autowired
	JobSeekerCustomService jobSeekerCustomService;
	
	@Autowired
	JobSeekerService jobSeekerService;
	
	@PostMapping("/jobseeker/profile")
	public ResponseEntity<JobSeekerDto> createJobSeekerProfile(@Valid @RequestBody JobSeekerDto jobSeekerDto) {
			JobSeekerDto jobSeekerData = jobSeekerService
					.createOrUpdateJobSeekerProfile(jobSeekerDto);
			return new ResponseEntity<>(jobSeekerData, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/searchjobs", method = RequestMethod.GET)
	public List<JobSearchResponse> searchJobs(
			@RequestParam("searchSkills") String searchSkills,
			@RequestParam("locations") Optional<String> locations,
			@RequestParam("companies") String companies, 
			@RequestParam("min") String min,
			@RequestParam("max") String max) {
		return jobSeekerCustomService.searchJobs(searchSkills,locations.get(),companies,min,max);
	}

	@GetMapping("/jobseeker")
	public ResponseEntity<List<JobSeekerDto>> getAllJobSeekerDto() {
			return new ResponseEntity<>(jobSeekerService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/jobseeker/{id}")
	public ResponseEntity<JobSeekerDto> getJobSeekerById(@PathVariable("id") long id) {
		Optional<JobSeekerDto> jobSeekerData = jobSeekerService.get(id);
		if (jobSeekerData.isPresent()) {
			return new ResponseEntity<>(jobSeekerData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	

	@PutMapping("/jobseeker/profile/{id}")
	public ResponseEntity<JobSeekerDto> updateJobSeeker(@PathVariable("id") long id, @RequestBody JobSeekerDto jobSeekerDto) {
		JobSeekerDto jobSeekerData  = jobSeekerService
				.createOrUpdateJobSeekerProfile(jobSeekerDto);
		if (jobSeekerDto!=null) {
			return new ResponseEntity<>(jobSeekerData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/jobseeker/{id}")
	public ResponseEntity<HttpStatus> deleteJobSeeker(@PathVariable("id") long id) {
			jobSeekerService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	
}
