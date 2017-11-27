package com.yongjun.tdms.dao.CustomerRelationship.financialInformation.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.financialInformation.FinancialInformationDao;
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;

public class HibernateFinancialInformation extends BaseHibernateDao implements FinancialInformationDao {
	public void storeFinancialInformation(FinancialInformation ca) {
		super.store(ca);
	}

	public FinancialInformation loadFinancialInformation(Long caId) {
		return (FinancialInformation) super.load(FinancialInformation.class, caId);
	}

	public List<FinancialInformation> loadAllFinancialInformation() {
		return super.loadAll(FinancialInformation.class);
	}

	public List<FinancialInformation> loadAllFinancialInformation(Long[] caIds) {
		return super.loadAll(FinancialInformation.class, caIds);
	}

	public void deleteFinancialInformation(FinancialInformation ca) {
		super.delete(ca);
	}

	public void deleteAllFinancialInformation(List<FinancialInformation> caIds) {
		super.deleteAll(caIds);
	}

	public List<FinancialInformation> loadByKey(String key, Object value) throws DaoException {
		return super.loadByKey(FinancialInformation.class, key, value);
	}

	public List<FinancialInformation> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(FinancialInformation.class, keyNames, keyValues);
	}

}
