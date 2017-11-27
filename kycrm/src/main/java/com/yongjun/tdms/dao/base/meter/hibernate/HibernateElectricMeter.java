package com.yongjun.tdms.dao.base.meter.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.meter.ElectricMeterDao;
import com.yongjun.tdms.model.base.meter.ElectricMeter;

public class HibernateElectricMeter extends BaseHibernateDao implements ElectricMeterDao {
	public void deleteAllElectricMeter(Collection<ElectricMeter> productes) {
		deleteAll(productes);
	}

	public void deleteElectricMeter(ElectricMeter house) {
		delete(house);
	}

	public List<ElectricMeter> loadAllElectricMeter(Long[] houseIds) {
		return loadAll(ElectricMeter.class, houseIds);
	}

	public List<ElectricMeter> loadAllElectricMeter() {
		return loadAll(ElectricMeter.class);
	}

	public ElectricMeter loadElectricMeter(Long houseId) {
		return (ElectricMeter) load(ElectricMeter.class, houseId);
	}

	public void storeElectricMeter(ElectricMeter house) {
		store(house);
	}

	public List<ElectricMeter> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ElectricMeter.class, keyName, keyValue);
	}

	public List<ElectricMeter> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ElectricMeter.class, keyNames, keyValues);
	}
}
