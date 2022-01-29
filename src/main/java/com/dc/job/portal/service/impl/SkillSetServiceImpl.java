package com.dc.job.portal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.job.portal.entity.SkillSet;
import com.dc.job.portal.repository.SkillRepository;
import com.dc.job.portal.service.SkillSetService;
import com.google.gson.Gson;

@Service
public class SkillSetServiceImpl implements SkillSetService{
	
	@Autowired 
	SkillRepository skillRepository;
	
	@Autowired
	Gson gson;
	
	
	public List<SkillSet> create(ArrayList<SkillSet> skillSet) {
		return  skillRepository.saveAll(skillSet);
	}

	
	
	public Optional<SkillSet> get(long id)
	{
		Optional<SkillSet>  skillSet=skillRepository.findById(new Long(id));
		return skillSet;
		
	}
	
	public List<SkillSet> getAll()
	{
		return skillRepository.findAll();
	}
	
	public void delete(Long id)
	{
		skillRepository.deleteById(id);
	}

}
