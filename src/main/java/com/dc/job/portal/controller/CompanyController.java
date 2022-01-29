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

import com.dc.job.portal.dto.CompanyDto;
import com.dc.job.portal.entity.Company;
import com.dc.job.portal.service.CompanyService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	
	@GetMapping("/company")
	public ResponseEntity<List<Company>> getAllCompany() {
			return new ResponseEntity<List<Company>>(companyService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<Optional<Company>> getCompanyById(@PathVariable("id") long id) {
		Optional<Company> companyData = companyService.get(id);
		if (companyData.isPresent()) {
			return new ResponseEntity<Optional<Company>>(companyData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/company")
	public ResponseEntity<CompanyDto> createCompany(@RequestBody @Valid CompanyDto companyDto) {
			CompanyDto companyData = companyService
					.createOrUpdateCompany(companyDto);
			return new ResponseEntity<>(companyData, HttpStatus.CREATED);
	}

	@PutMapping("/company/{id}")
	public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") long id, @RequestBody CompanyDto companyDto) {
		CompanyDto companyData = companyService
				.createOrUpdateCompany(companyDto);
		if (companyData!=null) {
			return new ResponseEntity<>(companyData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity<HttpStatus> deleteCompany(@PathVariable("id") long id) {
			companyService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	
}
