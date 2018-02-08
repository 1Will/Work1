package com.yongjun.tdms.service.base.additionalInformation.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.additionalInformation.AdditionalInformationDao;
import com.yongjun.tdms.model.base.additionalInformation.AdditionalInformation;
import com.yongjun.tdms.service.base.additionalInformation.AdditionalInformationManager;

public class DefaultAdditionalInformationManager extends BaseManager implements AdditionalInformationManager{
	private AdditionalInformationDao additionalInformationDao;

	public DefaultAdditionalInformationManager(AdditionalInformationDao additionalInformationDao) {
		this.additionalInformationDao = additionalInformationDao;
	}

	public AdditionalInformation loadAdditionalInformation(Long additionalInformationId) {
		return this.additionalInformationDao.loadAdditionalInformation(additionalInformationId);
	}

	public void storeAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformationDao.storeAdditionalInformation(additionalInformation);
		
	}

	public List<AdditionalInformation> loadAllAdditionalInformation(Long[] additionalInformationIds) {
		return null;
	}

	public List<AdditionalInformation> loadAllAdditionalInformation() {
		return null;
	}

	public void deleteAdditionalInformation(AdditionalInformation additionalInformation) {
		
	}

	public void deleteAllAdditionalInformation(Collection<AdditionalInformation> additionalInformations) {
		
	}

	public List<AdditionalInformation> loadByKey(String key, Object value) throws DaoException {
		return this.additionalInformationDao.loadByKey(key, value);
	}

	public Long getLatestLogo() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AdditionalInformation> loadAdditionalInformationKeyProperty(Long paramLong) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AdditionalInformation> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
