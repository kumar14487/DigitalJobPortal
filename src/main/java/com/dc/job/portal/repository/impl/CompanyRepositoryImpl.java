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

import com.dc.job.portal.entity.Company;
import com.dc.job.portal.repository.CompanyCustomRepository;

@Repository
@Transactional
@Service
public class CompanyRepositoryImpl implements CompanyCustomRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<String> passwordLookUp(String emailid) {

		Query query = entityManager.createQuery("SELECT password FROM User user WHERE user.companyUser = :emailId ");
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
	public List<Integer> getCompanyIdFromEmail(String emailid) {

		Query query = entityManager.createQuery("SELECT companyId FROM Company c WHERE c.companyUser = :emailId ");
		query.setParameter("emailId", emailid);
		List<Integer> list = new ArrayList<Integer>();
		List<?> querylist = query.getResultList();
		for (Iterator<?> iterator = querylist.iterator(); iterator.hasNext();) {
			int cid = (int) iterator.next();
			list.add(cid);
		}
		return list;
	}
	
	@Override
	public Company getCompany(int id) {
		return entityManager.find(Company.class, id);
	}

}
