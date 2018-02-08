package com.yongjun.tdms.dao.base.document.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.document.ApplicationDocDao;
import com.yongjun.tdms.model.base.document.ApplicationDoc;

public class HibernateApplicationDoc extends BaseHibernateDao implements ApplicationDocDao {
	public ApplicationDoc loadApplicationDoc(Long applicationDocId) {
		return (ApplicationDoc) load(ApplicationDoc.class, applicationDocId);
	}

	public List<ApplicationDoc> loadAllApplicationDocs(Long[] applicationDocIds) {
		return loadAll(ApplicationDoc.class, applicationDocIds);
	}

	public List<ApplicationDoc> loadAllApplicationDocs() {
		return loadAll(ApplicationDoc.class);
	}

	public void storeApplicationDoc(ApplicationDoc applicationDoc) {
		store(applicationDoc);
	}

	public void deleteApplicationDoc(ApplicationDoc applicationDoc) {
		delete(applicationDoc);
	}

	public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs) {
		deleteAll(applicationDocs);
	}

	public Integer getNumberOfManualDoc() {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.getNamedQuery("GetNumberOfManualDoc").uniqueResult();
			}
		});
	}

	public List<ApplicationDoc> getAllManualDoc() {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.getNamedQuery("GetAllManualDoc").list();
			}
		});
	}

	public List<ApplicationDoc> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ApplicationDoc.class, keyName, keyValue);
	}

	public List<ApplicationDoc> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ApplicationDoc.class, keyNames, keyValues);
	}
}
