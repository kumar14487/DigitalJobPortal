package com.dc.job.portal.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dc.job.portal.entity.SkillSet;

public interface SkillSetService {
	
	public List<SkillSet> create(ArrayList<SkillSet> skillSet);
	public Optional<SkillSet> get(long id);
	public List<SkillSet> getAll();
	public void delete(Long id);

}
