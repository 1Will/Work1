package com.yongjun.tdms.dao.customercontract.contractmanagement.houseListBack.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.houseListBack.HouseListBackDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseListBack.HouseListBack;

public class HibernateHouseBackList extends BaseHibernateDao implements HouseListBackDao {
	public void storeHouseListBack(HouseListBack t) {
		store(t);
	}

	public HouseListBack loadHouseListBack(Long id) {
		return (HouseListBack) load(HouseListBack.class, id);
	}

	public List<HouseListBack> loadHouseListBack() {
		return loadAll(HouseListBack.class);
	}

	public List<HouseListBack> loadAllHouseListBack(Long[] tIds) {
		return loadAll(HouseListBack.class, tIds);
	}

	public void deleteHouseListBack(HouseListBack t) {
		delete(t);
	}

	public void deleteAllHouseListBack(List<HouseListBack> ts) {
		deleteAll(ts);
	}

	public List<HouseListBack> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(HouseListBack.class, key, value);
	}

	public List<HouseListBack> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(HouseListBack.class, keyNames, keyValues);
	}
	public double getSum(long id) throws DaoException {
		String hql = "select sum(c.backRent) from HouseListBack as c where c.contractManagement.id=" + id;
		List codeList = getSession().createQuery(hql).list();
		if (null != codeList && codeList.size() > 0) {
			Double item = (Double) codeList.get(0);
			if (null == item) {
				return 0.0D;
			}
			return item.doubleValue();
		}
		return 0.0D;
	}
}
