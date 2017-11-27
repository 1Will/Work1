package com.yongjun.tdms.dao.expensemanagement.waterFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.waterFee.WaterHouseFeeDao;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterHouseFee;

public class HibernateWaterHouseFee extends BaseHibernateDao implements WaterHouseFeeDao {
	public void storeWaterHouseFee(WaterHouseFee t) {
		store(t);
	}

	public WaterHouseFee loadWaterHouseFee(Long id) {
		return (WaterHouseFee) load(WaterHouseFee.class, id);
	}

	public List<WaterHouseFee> loadWaterHouseFee() {
		return loadAll(WaterHouseFee.class);
	}

	public List<WaterHouseFee> loadAllWaterHouseFee(Long[] tIds) {
		return loadAll(WaterHouseFee.class, tIds);
	}

	public void deleteWaterHouseFee(WaterHouseFee t) {
		delete(t);
	}

	public void deleteAllWaterHouseFee(List<WaterHouseFee> ts) {
		deleteAll(ts);
	}

	public List<WaterHouseFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(WaterHouseFee.class, key, value);
	}

	public List<WaterHouseFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(WaterHouseFee.class, keyNames, keyValues);
	}

	public Double loadSumFeeByCusId(Long id, Date date) {
		double sumFee=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String hql="select sum(t.sumFee) from WaterHouseFee t where t.customerInfo.id="+id+" and t.endTime>=convert(datetime,'"+sdf.format(date)+"')";
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
