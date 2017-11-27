package com.yongjun.tdms.dao.CustomerRelationship.newStandard.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.newStandard.NewStandardDao;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.newStandard.NewStandard;
import com.yongjun.tdms.model.CustomerRelationship.technology.Technology;

public class HibernateNewStandardDao extends BaseHibernateDao implements NewStandardDao{

	public void storeNewStandard(NewStandard newStandard) {
		 super.store(newStandard);		
	}

	public NewStandard loadNewStandard(Long paramLong) {
		return (NewStandard)  super.load(NewStandard.class, paramLong);
	}

	public List<NewStandard> loadNewStandard() {
		return  super.loadAll(NewStandard.class);
	}

	public List<NewStandard> loadAllNewStandard(Long[] paramArrayOfLong) {
		return  super.loadAll(NewStandard.class, paramArrayOfLong);
	}

	public void deleteNewStandard(NewStandard paramNewStandard) {
		 super.delete(paramNewStandard);		
	}

	public void deleteAllNewStandard(List<NewStandard> paramList) {
		 super.deleteAll(paramList);		
	}

	public List<NewStandard> loadByKey(String paramString, Object paramObject) throws DaoException {
		return super.loadByKey(NewStandard.class, paramString, paramObject);
	}

	public List<NewStandard> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return super.loadByKeyArray(NewStandard.class,paramArrayOfString, paramArrayOfObject);
	}

}
