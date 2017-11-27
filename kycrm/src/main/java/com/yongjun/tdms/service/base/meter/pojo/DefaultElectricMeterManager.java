package com.yongjun.tdms.service.base.meter.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.meter.ElectricMeterDao;
import com.yongjun.tdms.model.base.meter.ElectricMeter;
import com.yongjun.tdms.service.base.meter.ElectricMeterManager;

public class DefaultElectricMeterManager extends BaseManager implements ElectricMeterManager {
	private final ElectricMeterDao electricMeterDao;

	public DefaultElectricMeterManager(ElectricMeterDao electricMeterDao) {
		this.electricMeterDao = electricMeterDao;
	}

	public void deleteAllElectricMeter(List<ElectricMeter> list) {
		this.electricMeterDao.deleteAllElectricMeter(list);
	}

	public void deleteElectricMeter(ElectricMeter electricMeter) {
		this.electricMeterDao.deleteElectricMeter(electricMeter);
	}

	public List<ElectricMeter> loadAllElectricMeter(Long[] ids) {
		return this.electricMeterDao.loadAllElectricMeter(ids);
	}

	public List<ElectricMeter> loadAllElectricMeter() {
		return this.electricMeterDao.loadAllElectricMeter();
	}

	public ElectricMeter loadElectricMeter(Long id) {
		return this.electricMeterDao.loadElectricMeter(id);
	}

	public void storeElectricMeter(ElectricMeter electricMeter) {
		this.electricMeterDao.storeElectricMeter(electricMeter);
	}

	public List<ElectricMeter> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.electricMeterDao.loadByKey(keyName, keyValue);
	}

	public List<ElectricMeter> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return this.electricMeterDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

}
