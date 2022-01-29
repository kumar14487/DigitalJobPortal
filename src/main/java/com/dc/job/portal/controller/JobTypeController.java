package com.dc.job.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RestController;

import com.dc.job.portal.dto.JobTypeDto;
import com.dc.job.portal.entity.JobType;
import com.dc.job.portal.service.JobTypeService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobTypeController {

	@Autowired
	JobTypeService jobTypeService;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/jobtypes")
	public ResponseEntity<List<JobTypeDto>> getAllJobTypes() {
			return new ResponseEntity<>(jobTypeService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/jobtype/{id}")
	public ResponseEntity<JobTypeDto> getJobTypeById(@PathVariable("id") long id) {
		Optional<JobTypeDto> jobTypeData = jobTypeService.get(id);
		if (jobTypeData.isPresent()) {
			return new ResponseEntity<>(jobTypeData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/jobtype")
	public ResponseEntity<List<JobType>> createJobType(@Valid @RequestBody ArrayList<JobType> jobType) {
			return new ResponseEntity<List<JobType>>(jobTypeService.create(jobType), HttpStatus.CREATED);
	
	}

	@PutMapping("/jobtype/{id}")
	public ResponseEntity<List<JobType>> updateJobType(@PathVariable("id") long id, @RequestBody ArrayList<JobType> jobTypeDto) {
		List<JobType> jobTypeData = jobTypeService.create(jobTypeDto);
		if (jobTypeData != null) {
			return new ResponseEntity<>(jobTypeData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/jobtype/{id}")
	public ResponseEntity<HttpStatus> deleteJobType(@PathVariable("id") long id) {
			jobTypeService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
