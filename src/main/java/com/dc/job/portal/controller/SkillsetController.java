package com.dc.job.portal.controller;

import java.util.ArrayList;
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

import com.dc.job.portal.entity.SkillSet;
import com.dc.job.portal.service.SkillSetService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SkillsetController {

	@Autowired
	SkillSetService skillSetService;

	@GetMapping("/skillset")
	public ResponseEntity<List<SkillSet>> getAllLocations() {
			return new ResponseEntity<>(skillSetService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/skillset/{id}")
	public ResponseEntity<SkillSet> getSkillsetById(@PathVariable("id") long id) {
		Optional<SkillSet> locData = skillSetService.get(id);
		if (locData.isPresent()) {
			return new ResponseEntity<>(locData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/skillset")
	public ResponseEntity<List<SkillSet>> createSkillset(@Valid @RequestBody ArrayList<SkillSet> skillSet) {
			List<SkillSet> skillSetData = skillSetService.create(skillSet);
			return new ResponseEntity<>(skillSetData, HttpStatus.CREATED);
	}

	@PutMapping("/skillset/{id}")
	public ResponseEntity<List<SkillSet>> updateSkillSet(@PathVariable("id") long id, @RequestBody ArrayList<SkillSet> skillSet) {
		List<SkillSet> skillSetRes = skillSetService.create(skillSet);
		if (skillSetRes != null) {
			return new ResponseEntity<>(skillSetRes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/skillset/{id}")
	public ResponseEntity<HttpStatus> deleteSkillSet(@PathVariable("id") long id) {
			skillSetService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
