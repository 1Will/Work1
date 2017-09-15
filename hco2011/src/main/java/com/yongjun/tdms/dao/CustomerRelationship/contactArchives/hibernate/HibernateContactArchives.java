package com.yongjun.tdms.dao.CustomerRelationship.contactArchives.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;

public class HibernateContactArchives extends BaseHibernateDao implements ContactArchivesDao {
	public void storeContactArchives(ContactArchives ca) {
		super.store(ca);
	}

	public ContactArchives loadContactArchives(Long caId) {
		return (ContactArchives) super.load(ContactArchives.class, caId);
	}

	public List<ContactArchives> loadAllContactArchives() {
		return super.loadAll(ContactArchives.class);
	}

	public List<ContactArchives> loadAllContactArchives(Long[] caIds) {
		return super.loadAll(ContactArchives.class, caIds);
	}

	public void deleteContactArchives(ContactArchives ca) {
		super.delete(ca);
	}

	public void deleteAllContactArchives(List<ContactArchives> caIds) {
		super.deleteAll(caIds);
	}

	public List<ContactArchives> loadByKey(String key, Object value) throws DaoException {
		return super.loadByKey(ContactArchives.class, key, value);
	}

	public List<ContactArchives> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ContactArchives.class, keyNames, keyValues);
	}

	public List<ContactArchives> getContactArchivesByCodeAndDate(String date, String name) {
		String hql = "from ContactArchives c where convert(varchar,c.createdTime,120) like '" + date
				+ "%' and c.creator = '" + name + "'";
		return getSession().createQuery(hql).list();
	}
}
