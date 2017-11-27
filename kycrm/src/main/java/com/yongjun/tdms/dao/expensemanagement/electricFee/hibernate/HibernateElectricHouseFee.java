package com.yongjun.tdms.dao.expensemanagement.electricFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.electricFee.ElectricHouseFeeDao;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricHouseFee;

public class HibernateElectricHouseFee extends BaseHibernateDao implements ElectricHouseFeeDao {
	public void storeElectricHouseFee(ElectricHouseFee t) {
		store(t);
	}

	public ElectricHouseFee loadElectricHouseFee(Long id) {
		return (ElectricHouseFee) load(ElectricHouseFee.class, id);
	}

	public List<ElectricHouseFee> loadElectricHouseFee() {
		return loadAll(ElectricHouseFee.class);
	}

	public List<ElectricHouseFee> loadAllElectricHouseFee(Long[] tIds) {
		return loadAll(ElectricHouseFee.class, tIds);
	}

	public void deleteElectricHouseFee(ElectricHouseFee t) {
		delete(t);
	}

	public void deleteAllElectricHouseFee(List<ElectricHouseFee> ts) {
		deleteAll(ts);
	}

	public List<ElectricHouseFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ElectricHouseFee.class, key, value);
	}

	public List<ElectricHouseFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ElectricHouseFee.class, keyNames, keyValues);
	}
	public Double loadSumFeeByCusId(Long id, Date date) {
		double sumFee=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String hql="select sum(t.sumFee) from ElectricHouseFee t where t.customerInfo.id="+id+" and t.endTime>=convert(datetime,'"+sdf.format(date)+"')";
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
