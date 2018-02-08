package com.yongjun.tdms.dao.base.document.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.document.ApplicationDocDao;
import com.yongjun.tdms.model.base.document.ApplicationDoc;

public class HibernateApplicationDoc extends BaseHibernateDao implements ApplicationDocDao{

	public ApplicationDoc loadApplicationDoc(Long applicationDocId) {
		return this.load(ApplicationDoc.class,applicationDocId);
	}

	public List<ApplicationDoc> loadAllApplicationDocs(Long[] applicationDocIds) {
		return this.loadAll(ApplicationDoc.class,applicationDocIds);
	}

	public List<ApplicationDoc> loadAllApplicationDocs() {
		return this.loadAll(ApplicationDoc.class);
	}

	public void storeApplicationDoc(ApplicationDoc applicationDoc) {
		this.store(applicationDoc);
	}

	public void deleteApplicationDoc(ApplicationDoc applicationDoc) {
		this.delete(applicationDoc);
	}

	public void deleteAllApplicationDocs(Collection<ApplicationDoc> applicationDocs) {
		this.deleteAll(applicationDocs);
	}
	
	@SuppressWarnings("unchecked")
	public Integer getNumberOfManualDoc() {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetNumberOfManualDoc")
								.uniqueResult();
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public List<ApplicationDoc> getAllManualDoc() {
		return (List<ApplicationDoc>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllManualDoc")
								.list();
					}
				});
	}

}
