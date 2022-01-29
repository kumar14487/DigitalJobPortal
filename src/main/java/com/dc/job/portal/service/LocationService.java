package com.dc.job.portal.service;

import java.util.List;
import java.util.Optional;

import com.dc.job.portal.dto.LocationDto;

public interface LocationService {

	public LocationDto create(LocationDto location);

	public Optional<LocationDto> get(Long id);

	public List<LocationDto> getAll();

	public void delete(Long id);

}
