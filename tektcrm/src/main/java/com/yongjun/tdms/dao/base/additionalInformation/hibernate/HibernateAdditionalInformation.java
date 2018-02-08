package com.yongjun.tdms.dao.base.additionalInformation.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.additionalInformation.AdditionalInformationDao;
import com.yongjun.tdms.model.base.additionalInformation.AdditionalInformation;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HibernateAdditionalInformation extends BaseHibernateDao implements AdditionalInformationDao{

	public void storeAdditionalInformation(AdditionalInformation additionalInformation) {
		super.store(additionalInformation);
		
	}

	public AdditionalInformation loadAdditionalInformation(Long additionalInformationId) {
		// TODO Auto-generated method stub
		return (AdditionalInformation)super.load(AdditionalInformation.class, additionalInformationId);
	}

	public List<AdditionalInformation> loadAllAdditionalInformation(Long[] additionalInformationIds) {
		// TODO Auto-generated method stub
		return  loadAll(AdditionalInformation.class, additionalInformationIds);
	}

	public List<AdditionalInformation> loadAllAdditionalInformation() {
		// TODO Auto-generated method stub
		return loadAll(AdditionalInformation.class);
	}

	public void deleteAdditionalInformation(AdditionalInformation additionalInformation) {
		delete(additionalInformation);
		
	}

	public void deleteAllAdditionalInformation(Collection<AdditionalInformation> additionalInformations) {
		deleteAll(additionalInformations);
		
	}

	public List<AdditionalInformation> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return loadByKey(AdditionalInformation.class, key, value);
	}

	public Long getLatestLogo() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AdditionalInformation> loadAdditionalInformationKeyProperty(Long paramLong) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AdditionalInformation> loadByKeyArray(String[] keyNames, Object[] keyValues)
			throws DaoException {
		// TODO Auto-generated method stub
		return loadByKeyArray(AdditionalInformation.class, keyNames, keyValues);
	}

}
