package com.yongjun.tdms.dao.customercontract.billingrecord.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.billingrecord.BillingRecordDao;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;

public class HibernateBillingRecordDao extends BaseHibernateDao implements BillingRecordDao {
	public void storeBillingRecord(BillingRecord t) {
		store(t);
	}

	public BillingRecord loadBillingRecord(Long id) {
		return (BillingRecord) load(BillingRecord.class, id);
	}

	public List<BillingRecord> loadBillingRecord() {
		return loadAll(BillingRecord.class);
	}

	public List<BillingRecord> loadAllBillingRecord(Long[] tIds) {
		return loadAll(BillingRecord.class, tIds);
	}

	public void deleteBillingRecord(BillingRecord t) {
		delete(t);
	}

	public void deleteAllBillingRecord(List<BillingRecord> ts) {
		deleteAll(ts);
	}

	public List<BillingRecord> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(BillingRecord.class, key, value);
	}

	public List<BillingRecord> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(BillingRecord.class, keyNames, keyValues);
	}
	
	public String getMaxCode(String code) {
		String hql = "select b.myCode from BillingRecord as b where  b.myCode like '%" + code + "%'";
		List codeList = getSession().createQuery(hql).list();
		if (null != codeList && codeList.size() > 0) {
			List items = new ArrayList();
			for (int i = 0; i < codeList.size(); i++) {
				String item = ((String) codeList.get(i)).substring(((String) codeList.get(i)).lastIndexOf("-") + 1);
				items.add(item);
			}
			Collections.sort(items);
			return (String) items.get(items.size() - 1);
		}
		return null;
	}
}
