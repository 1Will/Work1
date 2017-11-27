package com.yongjun.tdms.service.expensemanagement.waterFee.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.waterFee.WaterFloorFeeDao;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFloorFeeManager;

public class DefaultWaterFloorFeeManager extends BaseManager implements WaterFloorFeeManager {
	private final WaterFloorFeeDao dao;

	public DefaultWaterFloorFeeManager(WaterFloorFeeDao dao) {
		this.dao = dao;
	}

	public void storeWaterFloorFee(WaterFloorFee t) {
		this.dao.storeWaterFloorFee(t);
	}

	public WaterFloorFee loadWaterFloorFee(Long id) {
		return this.dao.loadWaterFloorFee(id);
	}

	public List<WaterFloorFee> loadWaterFloorFee() {
		return this.dao.loadWaterFloorFee();
	}

	public List<WaterFloorFee> loadAllWaterFloorFee(Long[] tIds) {
		return this.dao.loadAllWaterFloorFee(tIds);
	}

	public void deleteWaterFloorFee(WaterFloorFee t) {
		this.dao.deleteWaterFloorFee(t);
	}

	public void deleteAllWaterFloorFee(List<WaterFloorFee> ts) {
		this.dao.deleteAllWaterFloorFee(ts);
	}

	public List<WaterFloorFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<WaterFloorFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

}
