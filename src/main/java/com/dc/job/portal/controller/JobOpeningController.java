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
import org.springframework.web.bind.annotation.RestController;

import com.dc.job.portal.dto.JobOpeningDto;
import com.dc.job.portal.entity.JobOpening;
import com.dc.job.portal.service.JobOpeningService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class JobOpeningController {

	@Autowired
	JobOpeningService jobOpeningService;

	@GetMapping("/jobopening")
	public ResponseEntity<List<JobOpeningDto>> getAllJobOpening() {
			return new ResponseEntity<>(jobOpeningService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/jobopening/{id}")
	public ResponseEntity<JobOpening> getJobOpeningById(@PathVariable("id") long id) {
		Optional<JobOpening> jobOpeningData = jobOpeningService.get(id);
		if (jobOpeningData.isPresent()) {
			return new ResponseEntity<>(jobOpeningData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/jobopening")
	public ResponseEntity<JobOpeningDto> createJobOpening(@Valid @RequestBody JobOpeningDto jobOpeningDto) {
			JobOpeningDto jobOpeningData = jobOpeningService
					.createOrUpdateJobOpening(jobOpeningDto);
			return new ResponseEntity<>(jobOpeningData, HttpStatus.CREATED);
		
	}

	@PutMapping("/jobopening/{id}")
	public ResponseEntity<JobOpeningDto> updateJobOpening(@PathVariable("id") long id, @RequestBody JobOpeningDto jobOpeningDto) {
		JobOpeningDto jobOpeningData  = jobOpeningService
				.createOrUpdateJobOpening(jobOpeningDto);
		if (jobOpeningData!=null) {
			return new ResponseEntity<>(jobOpeningData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/jobopening/{id}")
	public ResponseEntity<HttpStatus> deleteJobOpening(@PathVariable("id") long id) {
			jobOpeningService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


	
}
