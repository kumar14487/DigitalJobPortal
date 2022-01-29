package com.dc.job.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.job.portal.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
}
