package com.yongjun.tdms.dao.expensemanagement.airFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.airFee.AirHouseFeeDao;
import com.yongjun.tdms.model.expensemanagement.airFee.AirHouseFee;

public class HibernateAirHouseFee extends BaseHibernateDao implements AirHouseFeeDao {
	public void storeAirHouseFee(AirHouseFee t) {
		store(t);
	}

	public AirHouseFee loadAirHouseFee(Long id) {
		return (AirHouseFee) load(AirHouseFee.class, id);
	}

	public List<AirHouseFee> loadAirHouseFee() {
		return loadAll(AirHouseFee.class);
	}

	public List<AirHouseFee> loadAllAirHouseFee(Long[] tIds) {
		return loadAll(AirHouseFee.class, tIds);
	}

	public void deleteAirHouseFee(AirHouseFee t) {
		delete(t);
	}

	public void deleteAllAirHouseFee(List<AirHouseFee> ts) {
		deleteAll(ts);
	}

	public List<AirHouseFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(AirHouseFee.class, key, value);
	}

	public List<AirHouseFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(AirHouseFee.class, keyNames, keyValues);
	}

	public Double loadSumFeeByCusId(Long id, Date date) {
		double sumFee=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String hql="select sum(t.sumFee) from AirHouseFee t where t.customerInfo.id="+id+" and t.endTime>=convert(datetime,'"+sdf.format(date)+"')";
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
