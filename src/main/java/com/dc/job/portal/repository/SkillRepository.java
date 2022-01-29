package com.dc.job.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dc.job.portal.entity.SkillSet;

@Repository
public interface SkillRepository extends JpaRepository<SkillSet, Long> {
	
}
