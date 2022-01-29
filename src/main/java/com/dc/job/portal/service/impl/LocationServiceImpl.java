package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.dto.LocationDto;
import com.dc.job.portal.entity.JobLocation;
import com.dc.job.portal.repository.LocationRepository;
import com.dc.job.portal.service.LocationService;
import com.google.gson.Gson;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locRepository;

	@Autowired
	Gson gson;

	public LocationDto create(LocationDto locationDto) {
		JobLocation loc = convertToLoc(locationDto);
		loc = locRepository.save(loc);
		LocationDto LocationDtoReturn = convertToLocationDto(loc);
		return LocationDtoReturn;
	}

	private LocationDto convertToLocationDto(JobLocation loc) {
		String LocationDtoStr = gson.toJson(loc);
		return gson.fromJson(LocationDtoStr, LocationDto.class);
	}

	private JobLocation convertToLoc(LocationDto LocationDto) {
		String locStr = gson.toJson(LocationDto);
		return gson.fromJson(locStr, JobLocation.class);
	}

	public Optional<LocationDto> get(Long id) {
		Optional<JobLocation> loc = locRepository.findById(id);
		if (loc.isPresent()) {
			return Optional.of(convertToLocationDto(loc.get()));
		} else {
			return Optional.empty();
		}

	}

	public List<LocationDto> getAll() {
		List<JobLocation> locList = locRepository.findAll();
		List<LocationDto> locationDtoList = new ArrayList<>();
		if (!locList.isEmpty()) {
			locationDtoList=locList.stream().map(x->convertToLocationDto(x)).collect(Collectors.toList());
		}
		return locationDtoList;

	}

	public void delete(Long id) {
		locRepository.deleteById(id);
	}

}
