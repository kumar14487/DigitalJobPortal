package com.dc.job.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.job.portal.entity.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
	
	public JobSeeker findByJobseekerId(long id);
	
	
}
