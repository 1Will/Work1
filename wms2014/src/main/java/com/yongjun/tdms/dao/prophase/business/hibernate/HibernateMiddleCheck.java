package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.MiddleCheckDao;

import com.yongjun.tdms.model.prophase.business.MiddleCheck;


public class HibernateMiddleCheck extends BaseHibernateDao implements MiddleCheckDao{

	public MiddleCheck loadMiddleCheck(Long id) {
		// TODO Auto-generated method stub
		return this.load(MiddleCheck.class,id);
	}

	public List<MiddleCheck> loadMiddleChecks(Long[] MiddleCheckIds) {
		// TODO Auto-generated method stub
		return this.loadAll(MiddleCheck.class,MiddleCheckIds);
	}

	public void storeMiddleCheck(MiddleCheck middleCheck) {
		// TODO Auto-generated method stub
		  this.store(middleCheck);
	}

	public void deleteMiddleCheck(MiddleCheck middleCheck) {
		  this.delete(middleCheck);
		
	}

	public void deleteAllMiddleCheck(Collection<MiddleCheck> MiddleCheckIds) {
		this.deleteAll(MiddleCheckIds);
		
	}

	

	
		




}
