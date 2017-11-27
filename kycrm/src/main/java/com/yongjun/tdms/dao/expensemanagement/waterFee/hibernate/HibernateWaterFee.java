package com.yongjun.tdms.dao.expensemanagement.waterFee.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.expensemanagement.waterFee.WaterFeeDao;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;

public class HibernateWaterFee extends BaseHibernateDao implements WaterFeeDao {
	public void storeWaterFee(WaterFee t) {
		store(t);
	}

	public WaterFee loadWaterFee(Long id) {
		return (WaterFee) load(WaterFee.class, id);
	}

	public List<WaterFee> loadWaterFee() {
		return loadAll(WaterFee.class);
	}

	public List<WaterFee> loadAllWaterFee(Long[] tIds) {
		return loadAll(WaterFee.class, tIds);
	}

	public void deleteWaterFee(WaterFee t) {
		delete(t);
	}

	public void deleteAllWaterFee(List<WaterFee> ts) {
		deleteAll(ts);
	}

	public List<WaterFee> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(WaterFee.class, key, value);
	}

	public List<WaterFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(WaterFee.class, keyNames, keyValues);
	}
	public WaterFee loadByStartTime(Date date) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
		String hql="select t from WaterFee t where  t.starTime>=convert(datetime,'"+sdf.format(date)+"')  order by t.endTime desc";
		List <WaterFee>codeList = getSession().createQuery(hql).list();
		if (null != codeList && codeList.size() > 0) {
			return codeList.get(0);
		}
		return null;
	}
}
