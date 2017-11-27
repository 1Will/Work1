package com.yongjun.tdms.dao.expensemanagement.electricFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.electricFee.ElectricFeeDao;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;

public class HibernateElectricFee extends BaseHibernateDao implements ElectricFeeDao {
	public void storeElectricFee(ElectricFee t) {
		store(t);
	}

	public ElectricFee loadElectricFee(Long id) {
		return (ElectricFee) load(ElectricFee.class, id);
	}

	public List<ElectricFee> loadElectricFee() {
		return loadAll(ElectricFee.class);
	}

	public List<ElectricFee> loadAllElectricFee(Long[] tIds) {
		return loadAll(ElectricFee.class, tIds);
	}

	public void deleteElectricFee(ElectricFee t) {
		delete(t);
	}

	public void deleteAllElectricFee(List<ElectricFee> ts) {
		deleteAll(ts);
	}

	public List<ElectricFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(ElectricFee.class, key, value);
	}

	public List<ElectricFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ElectricFee.class, keyNames, keyValues);
	}
	public ElectricFee loadByStartTime(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String hql="select t from ElectricFee t where  t.starTime>=convert(datetime,'"+sdf.format(date)+"') order by t.endTime desc";
		List <ElectricFee>codeList = getSession().createQuery(hql).list();
		if (null != codeList && codeList.size() > 0) {
			return codeList.get(0);
		}
		return null;
	}
}
