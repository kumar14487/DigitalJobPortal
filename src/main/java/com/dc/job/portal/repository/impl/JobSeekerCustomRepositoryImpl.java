package com.dc.job.portal.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dc.job.portal.dto.JobSearchResponse;
import com.dc.job.portal.entity.JobOpening;
import com.dc.job.portal.repository.JobSeekerCustomRepository;


@Repository
@Transactional
@Service
public class JobSeekerCustomRepositoryImpl implements JobSeekerCustomRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<JobSearchResponse> searchJobs(String searchSkills,String locations,String companies,String min,String max) {
		String searchSkillsArray[] = searchSkills.split(",");
		String selectQuery = "SELECT jo.* FROM job_opening jo \r\n"
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

		for (int i = 0; i < searchSkillsArray.length; i++) {
			selectQuery = selectQuery.concat("ss.skill_name LIKE :searchParam" + i);
			if (i != searchSkillsArray.length - 1) {
				selectQuery = selectQuery.concat(" OR ");
			}
		}
		
		if (!StringUtils.isEmpty(locations)) {
			selectQuery = selectQuery.concat(" AND jl.city in (:city) ");
		}
		
		if (!StringUtils.isEmpty(companies)) {
			selectQuery = selectQuery.concat(" AND c.company_name IN (:companies) ");
		}
		if (!StringUtils.isEmpty(min) && !StringUtils.isEmpty(max)) {
			selectQuery = selectQuery.concat(" AND jo.job_salary >= :min AND jo.job_salary <= :max ");
		}
		

		Query query = entityManager.createNativeQuery(selectQuery);
		for (int i = 0; i < searchSkillsArray.length; i++) {
			query.setParameter("searchParam" + i, "%"+searchSkillsArray[i]+"%");
		}
		
		if (!StringUtils.isEmpty(locations)) {
			String locationsArray[]=locations.split(",");
			String locComaSep="";
			boolean first=true;
			for(String loc:locationsArray)
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
			query.setParameter("city", locComaSep);
		}
		
		if (!StringUtils.isEmpty(companies)) {
			query.setParameter("companies", "'"+companies+"'");
		}
		if (!StringUtils.isEmpty(min) && !StringUtils.isEmpty(max)) {
			query.setParameter("min", min);
			query.setParameter("max", max);
		}

		return query.getResultList();
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
