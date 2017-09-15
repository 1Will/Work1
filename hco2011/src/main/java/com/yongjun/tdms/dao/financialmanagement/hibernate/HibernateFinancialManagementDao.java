package com.yongjun.tdms.dao.financialmanagement.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.financialmanagement.FinancialManagementDao;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HibernateFinancialManagementDao extends BaseHibernateDao implements FinancialManagementDao {
	public void storeFinancialManagement(FinancialManagement financialManagement) {
		store(financialManagement);
	}

	public FinancialManagement loadFinancialManagement(Long financialManagementId) {
		return (FinancialManagement) load(FinancialManagement.class, financialManagementId);
	}

	public List<FinancialManagement> loadAllFinancialManagements() {
		return loadAll(FinancialManagement.class);
	}

	public List<FinancialManagement> loadAllFinancialManagement(Long[] financialManagementIds) {
		return loadAll(FinancialManagement.class, financialManagementIds);
	}

	public void deleteFinancialManagement(FinancialManagement financialManagement) {
		delete(financialManagement);
	}

	public void deleteAllFinancialManagement(List<FinancialManagement> financialManagements) {
		deleteAll(financialManagements);
	}

	public List<FinancialManagement> loadByKey(String key, Object value) throws DaoException {
		return loadByKey(FinancialManagement.class, key, value);
	}

	public List<FinancialManagement> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(FinancialManagement.class, keyNames, keyValues);
	}

	public String getMaxPFCode(String code) {
		String hql = "select c.code from FinancialManagement as c where  c.code like '%" + code + "%'";
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

	public HashMap getDataMap(String staDate, String endDate) {
		HashMap map = new HashMap();
		Session session = getSession();
		String hqlCount = "select count(*) from FinancialManagement c where  c.collectionDate >= CAST('" + staDate
				+ "' AS DATETIME )  AND c.collectionDate <= CAST('" + endDate + "' AS DATETIME )  ";
		List countList = session.createQuery(hqlCount).list();
		if ((null != countList) && (countList.size() > 0)) {
			Integer object = (Integer) countList.get(0);
			map.put("count", object);
		}
		String hqlMoney = "select isnull(sum(c.trueSum),0) from FinancialManagement c where  c.collectionDate >= CAST('"
				+ staDate + "' AS DATETIME )  AND c.collectionDate <= CAST('" + endDate + "' AS DATETIME )  ";
		List moneytList = session.createQuery(hqlMoney).list();
		if ((null != moneytList) && (moneytList.size() > 0)) {
			Double object = (Double) moneytList.get(0);
			map.put("money", object);
		}
		return map;

	}
}
