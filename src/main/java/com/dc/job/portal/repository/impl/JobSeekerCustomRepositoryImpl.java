package com.dc.job.portal.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.json.JsonArray;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dc.job.portal.dto.JobSearchResponse;
import com.dc.job.portal.exception.BusinessException;
import com.dc.job.portal.repository.JobSeekerCustomRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;


@Repository
@Transactional
@Service
public class JobSeekerCustomRepositoryImpl implements JobSeekerCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	Gson gson;
	
	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max) throws BusinessException {
		
		String selectQuery = "SELECT jo.job_description as jobDescription, jo.job_salary as jobSalary, jo.skill_level  as skillLevel,"
				+ "c.company_name as companyName, ss.skill_name as skillName, jl.city as city,"
				+ "jl.state as state, jl.country as country FROM job_opening jo \r\n"
				+ "left join jobopening_skillset js \r\n"
				+ "on jo.job_opening_id = js.job_opening_id\r\n"
				+ "left join skillset ss\r\n"
				+ "on js.skill_set_id = ss.id\r\n"
				+ "left join job_location jl\r\n"
				+ "on jo.job_opening_id = jl.job_opening\r\n"
				+ "left join company c\r\n"
				+ "on jo.company_id = c.company_id";
		if (!searchSkills.isEmpty()) {
			selectQuery = selectQuery.concat(" WHERE ");
		}

		String searchSkillsArray[] = searchSkills.split(",");
		for (int i = 0; i < searchSkillsArray.length; i++) {
			selectQuery = selectQuery.concat("ss.skill_name LIKE '%" + searchSkillsArray[i].toString() +  "%'");
			if (i != searchSkillsArray.length - 1) {
				selectQuery = selectQuery.concat(" OR ");
			}
		}
		
		if (!StringUtils.isEmpty(locations)) {
			selectQuery = selectQuery.concat("AND  jl.city in ("+getCommaSeperatedValues(locations)+") ");
		}
		
		if (!StringUtils.isEmpty(companies)) {
			selectQuery = selectQuery.concat(" AND c.company_name IN ("+getCommaSeperatedValues(companies)+") ");
		}
		if (!StringUtils.isEmpty(min) && !StringUtils.isEmpty(max)) {
			selectQuery = selectQuery.concat(" AND jo.job_salary >= "+min+" AND jo.job_salary <=  "+max);
		}

		Query query = entityManager.createNativeQuery(selectQuery);
		List<Object>  listOfObjects=query.getResultList();
		
	    ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	    String arrayToJson;
	    
	    List<JobSearchResponse> jobSerachResponseList=new ArrayList<>();
		try {
			arrayToJson = objectMapper.writeValueAsString(listOfObjects);
			List<List<String>> arrayListOfStringValues=gson.fromJson(arrayToJson, List.class);
			for (List<String> arrayListValues: arrayListOfStringValues) {
				  JobSearchResponse jobSearchResponse=new JobSearchResponse();
				  jobSearchResponse.setJobDescription(arrayListValues.get(0).toString());
				  jobSearchResponse.setJobSalary(arrayListValues.get(1).toString());
				  jobSearchResponse.setCompanyName(arrayListValues.get(3).toString());
					  jobSearchResponse.setSkillName(arrayListValues.get(4));
					  jobSearchResponse.setCity(arrayListValues.get(5));
					  jobSearchResponse.setState(arrayListValues.get(6));
					  jobSearchResponse.setCountry(arrayListValues.get(7));
					 
				  jobSerachResponseList.add(jobSearchResponse);
				 
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Jobsearch data conversion error");
		}
		return jobSerachResponseList;
	}


	private String getCommaSeperatedValues(String strValue) {
		String locComaSep="";
		
			String strArray[]=strValue.split(",");
			
			boolean first=true;
			for(String loc:strArray)
			{
				if(first)
				{
					locComaSep="'"+loc+"'";
					first=false;
					
				}else
				{
					locComaSep=locComaSep+",'"+loc+"'";
				}
			}
		System.out.println("locComaSep >>>>>"+locComaSep);	
		
		return locComaSep;
	}

		
	@Override
	public List<String> passwordLookUp(String emailid) {

		Query query = entityManager.createQuery("SELECT password FROM JobSeeker js WHERE js.emailId = :emailId");
		query.setParameter("emailId", emailid);
		List<String> list = new ArrayList<String>();
		List<?> querylist = query.getResultList();
		for (Iterator<?> iterator = querylist.iterator(); iterator.hasNext();) {
			String pwd = (String) iterator.next();
			list.add(pwd);
		}
		return list;
	}
	

	
	@Override
	public List<Long> getUserIdFromEmail(String emailid) {

		Query query = entityManager.createQuery("SELECT jobseekerId FROM JobSeeker js WHERE js.emailId = :emailId");
		query.setParameter("emailId", emailid);
		List<Long> querylist = query.getResultList();
		return querylist;
	}
	
}
