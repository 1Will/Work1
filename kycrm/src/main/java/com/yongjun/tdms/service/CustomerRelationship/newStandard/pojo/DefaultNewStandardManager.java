package com.yongjun.tdms.service.CustomerRelationship.newStandard.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.newStandard.NewStandardDao;
import com.yongjun.tdms.model.CustomerRelationship.newStandard.NewStandard;
import com.yongjun.tdms.service.CustomerRelationship.newStandard.NewStandardManager;

public class DefaultNewStandardManager extends BaseManager implements NewStandardManager {
   private NewStandardDao newStandardDao;
	public DefaultNewStandardManager(NewStandardDao newStandardDao) {
	this.newStandardDao = newStandardDao;
    }
	public void storeNewStandard(NewStandard newStandard) {
		newStandardDao.storeNewStandard(newStandard);
	}

	public NewStandard loadNewStandard(Long paramLong) {
		return newStandardDao.loadNewStandard(paramLong);
	}

	public List<NewStandard> loadNewStandard() {
		return newStandardDao.loadNewStandard();
	}

	public List<NewStandard> loadAllNewStandard(Long[] paramArrayOfLong) {
		return newStandardDao.loadAllNewStandard(paramArrayOfLong);
	}

	public void deleteNewStandard(NewStandard paramNewStandard) {
		newStandardDao.deleteNewStandard(paramNewStandard);
	}

	public void deleteAllNewStandard(List<NewStandard> paramList) {
		newStandardDao.deleteAllNewStandard(paramList);
	}

	public List<NewStandard> loadByKey(String paramString, Object paramObject) throws DaoException {
		return newStandardDao.loadByKey(paramString, paramObject);
	}

	public List<NewStandard> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return newStandardDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
