package com.yongjun.tdms.service.CustomerRelationship.financialInformation.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.CustomerRelationship.financialInformation.FinancialInformationDao;
import com.yongjun.tdms.model.CustomerRelationship.financialInformation.FinancialInformation;
import com.yongjun.tdms.service.CustomerRelationship.financialInformation.FinancialInformationManager;

public class DefaultFinancialInformationManager extends BaseManager implements FinancialInformationManager {
	public final FinancialInformationDao financialInformationDao;

	public DefaultFinancialInformationManager(FinancialInformationDao financialInformationDao) {
		this.financialInformationDao = financialInformationDao;
	}

	public void storeFinancialInformation(FinancialInformation ca) {
		this.financialInformationDao.storeFinancialInformation(ca);
	}


	public FinancialInformation loadFinancialInformation(Long caId) {
		return this.financialInformationDao.loadFinancialInformation(caId);
	}

	public List<FinancialInformation> loadAllFinancialInformation() {
		return this.financialInformationDao.loadAllFinancialInformation();
	}

	public List<FinancialInformation> loadAllFinancialInformation(Long[] caIds) {
		return this.financialInformationDao.loadAllFinancialInformation(caIds);
	}

	public void deleteFinancialInformation(FinancialInformation ca) {
		this.financialInformationDao.deleteFinancialInformation(ca);
	}

	public void deleteAllFinancialInformation(List<FinancialInformation> caIds) {
		this.financialInformationDao.deleteAllFinancialInformation(caIds);
	}

	public List<FinancialInformation> loadByKey(String key, Object value) throws DaoException {
		return this.financialInformationDao.loadByKey(key, value);
	}

	public void disabledAllFinancialInformation(List<FinancialInformation> cas) {
		for (FinancialInformation archives : cas) {
			archives.setDisabled(true);
			this.financialInformationDao.storeFinancialInformation(archives);
		}
	}

	public void enabledAllFinancialInformation(List<FinancialInformation> cas) {
		for (FinancialInformation archives : cas) {
			archives.setDisabled(false);
			this.financialInformationDao.storeFinancialInformation(archives);
		}
	}

	public List<FinancialInformation> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.financialInformationDao.loadByKeyArray(keyNames, keyValues);
	}

}
