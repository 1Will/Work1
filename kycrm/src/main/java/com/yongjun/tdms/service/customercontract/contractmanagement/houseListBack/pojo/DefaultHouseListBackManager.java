package com.yongjun.tdms.service.customercontract.contractmanagement.houseListBack.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.houseListBack.HouseListBackDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseListBack.HouseListBack;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseListBack.HouseListBackManager;

public class DefaultHouseListBackManager extends BaseManager implements HouseListBackManager {
	private final HouseListBackDao houseListBackDao;

	public DefaultHouseListBackManager(HouseListBackDao houseListBackDao) {
		this.houseListBackDao = houseListBackDao;
	}

	public void storeHouseListBack(HouseListBack paramHouseListBack) {
		houseListBackDao.storeHouseListBack(paramHouseListBack);
	}

	public HouseListBack loadHouseListBack(Long paramLong) {
		return houseListBackDao.loadHouseListBack(paramLong);
	}

	public List<HouseListBack> loadHouseListBack() {
		return houseListBackDao.loadHouseListBack();
	}

	public List<HouseListBack> loadAllHouseListBack(Long[] paramArrayOfLong) {
		return houseListBackDao.loadAllHouseListBack(paramArrayOfLong);
	}

	public void deleteHouseListBack(HouseListBack paramHouseListBack) {
		houseListBackDao.deleteHouseListBack(paramHouseListBack);
	}

	public void deleteAllHouseListBack(List<HouseListBack> paramList) {
		houseListBackDao.deleteAllHouseListBack(paramList);
	}

	public List<HouseListBack> loadByKey(String paramString, Object paramObject) throws DaoException {
		return houseListBackDao.loadByKey(paramString, paramObject);
	}

	public List<HouseListBack> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return houseListBackDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

	public double getSum(long id) throws DaoException {
		return houseListBackDao.getSum(id);
	}

}
