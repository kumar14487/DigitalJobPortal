package com.dc.job.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.job.portal.entity.JobOpening;

@Repository
public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {
	
	public JobOpening findByJobOpeningId(long id);
	
}
