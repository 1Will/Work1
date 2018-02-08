package com.yongjun.tdms.dao.COM.co.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.COM.co.CoDao;
import com.yongjun.tdms.model.COM.co.Co;

public class HibernateCoDao extends BaseHibernateDao implements CoDao {
	public void deleteAllCo(Collection<Co> cos) {
		deleteAll(cos);
	}

	public void deleteCo(Co co) {
		delete(co);
	}

	public List<Co> loadAllCo(Long[] coIds) {
		return loadAll(Co.class, coIds);
	}

	public List<Co> loadAllCo() {
		return loadAll(Co.class);
	}

	public List<Co> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(Co.class, keyName, keyValue);
	}

	public List<Co> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(Co.class, keyNames, keyValues);
	}

	public Co loadCo(Long coId) {
		return (Co) load(Co.class, coId);
	}

	public void storeCo(Co co) {
		store(co);
	}

	public String getMaxPFCode(String code) {
		String hql = "select c.code from Co as c where  c.code like '%" + code + "%'";
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
