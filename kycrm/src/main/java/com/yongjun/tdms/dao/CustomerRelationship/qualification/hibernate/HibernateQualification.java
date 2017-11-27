package com.yongjun.tdms.dao.CustomerRelationship.qualification.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.CustomerRelationship.qualification.QualificationDao;
import com.yongjun.tdms.model.CustomerRelationship.qualification.CustomerRelationshipQualification;

public class HibernateQualification extends BaseHibernateDao implements QualificationDao {
	public void storeQualification(CustomerRelationshipQualification ca) {
		super.store(ca);
	}

	public CustomerRelationshipQualification loadQualification(Long caId) {
		return (CustomerRelationshipQualification) super.load(CustomerRelationshipQualification.class, caId);
	}

	public List<CustomerRelationshipQualification> loadAllQualification() {
		return super.loadAll(CustomerRelationshipQualification.class);
	}

	public List<CustomerRelationshipQualification> loadAllQualification(Long[] caIds) {
		return super.loadAll(CustomerRelationshipQualification.class, caIds);
	}

	public void deleteQualification(CustomerRelationshipQualification ca) {
		super.delete(ca);
	}

	public void deleteAllQualification(List<CustomerRelationshipQualification> caIds) {
		super.deleteAll(caIds);
	}

	public List<CustomerRelationshipQualification> loadByKey(String key, Object value) throws DaoException {
		return super.loadByKey(CustomerRelationshipQualification.class, key, value);
	}

	public List<CustomerRelationshipQualification> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(CustomerRelationshipQualification.class, keyNames, keyValues);
	}

}
