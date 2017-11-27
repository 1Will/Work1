package com.yongjun.tdms.dao.customercontract.contractmanagement.houseList.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.houseList.HouseListDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;

public class HibernateHouseList extends BaseHibernateDao implements HouseListDao {
	public void storeHouseList(HouseList t) {
		store(t);
	}

	public HouseList loadHouseList(Long id) {
		return (HouseList) load(HouseList.class, id);
	}

	public List<HouseList> loadHouseList() {
		return loadAll(HouseList.class);
	}

	public List<HouseList> loadAllHouseList(Long[] tIds) {
		return loadAll(HouseList.class, tIds);
	}

	public void deleteHouseList(HouseList t) {
		delete(t);
	}

	public void deleteAllHouseList(List<HouseList> ts) {
		deleteAll(ts);
	}

	public List<HouseList> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(HouseList.class, key, value);
	}

	public List<HouseList> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(HouseList.class, keyNames, keyValues);
	}

	public double getSum(long id) throws DaoException {
		String hql = "select sum(c.sum) from HouseList as c where c.contractManagement.id=" + id;
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
	
	public double getAllSquare(long id) throws DaoException {
		String hql = "select sum(c.house.square) from HouseList as c where c.contractManagement.id=" + id;
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

	public String getHouseListEndTime(long id) {
		String hql = "select c.endTime from HouseList as c where c.isuse=1 and c.house.id=" + id;
		List codeList = getSession().createQuery(hql).list();
		if(null != codeList && codeList.size() > 0){
			Date date = (Date) codeList.get(0);
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			return  sdf.format(date);
		}
		return null;
	}
	
	
}
