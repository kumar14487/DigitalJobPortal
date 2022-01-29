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

import com.dc.job.portal.dto.LocationDto;
import com.dc.job.portal.service.LocationService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LocationController {

	@Autowired
	LocationService locationService;

	@GetMapping("/locations")
	public ResponseEntity<List<LocationDto>> getAllLocations() {
			return new ResponseEntity<>(locationService.getAll(), HttpStatus.OK);
		
	}

	@GetMapping("/location/{id}")
	public ResponseEntity<LocationDto> getLocationById(@PathVariable("id") long id) {
		Optional<LocationDto> locData = locationService.get(id);
		if (locData.isPresent()) {
			return new ResponseEntity<>(locData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/location")
	public ResponseEntity<LocationDto> createLocation(@Valid @RequestBody LocationDto locDto) {
			return new ResponseEntity<LocationDto>(locationService.create(locDto), HttpStatus.CREATED);
		
	}

	@PutMapping("/location/{id}")
	public ResponseEntity<LocationDto> updateLocation(@PathVariable("id") long id, @RequestBody LocationDto locDto) {
		LocationDto locData = locationService.create(locDto);
		if (locData != null) {
			return new ResponseEntity<>(locData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/location/{id}")
	public ResponseEntity<HttpStatus> deleteLocation(@PathVariable("id") long id) {
			locationService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
