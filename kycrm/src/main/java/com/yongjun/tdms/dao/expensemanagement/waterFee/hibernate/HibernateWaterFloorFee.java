package com.yongjun.tdms.dao.expensemanagement.waterFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.waterFee.WaterFloorFeeDao;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;

public class HibernateWaterFloorFee extends BaseHibernateDao implements WaterFloorFeeDao {
	public void storeWaterFloorFee(WaterFloorFee t) {
		store(t);
	}

	public WaterFloorFee loadWaterFloorFee(Long id) {
		return (WaterFloorFee) load(WaterFloorFee.class, id);
	}

	public List<WaterFloorFee> loadWaterFloorFee() {
		return loadAll(WaterFloorFee.class);
	}

	public List<WaterFloorFee> loadAllWaterFloorFee(Long[] tIds) {
		return loadAll(WaterFloorFee.class, tIds);
	}

	public void deleteWaterFloorFee(WaterFloorFee t) {
		delete(t);
	}

	public void deleteAllWaterFloorFee(List<WaterFloorFee> ts) {
		deleteAll(ts);
	}

	public List<WaterFloorFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(WaterFloorFee.class, key, value);
	}

	public List<WaterFloorFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(WaterFloorFee.class, keyNames, keyValues);
	}

}
