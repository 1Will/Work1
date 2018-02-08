package com.yongjun.tdms.dao.personnelFiles.qualification.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.personnelFiles.qualification.QualificationDao;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;

public class HibernateQualification extends BaseHibernateDao implements QualificationDao {
	public void storeQualification(Qualification qf) {
		super.store(qf);
	}

	public void deleteQualification(Qualification qf) {
		super.delete(qf);
	}

	public void deleteAllQualification(Collection<Qualification> qfs) {
		super.deleteAll(qfs);
	}

	public List<Qualification> loadAllQualification(Long[] qfIds) {
		return super.loadAll(Qualification.class, qfIds);
	}

	public Qualification loadQualification(Long qfId) {
		return (Qualification) super.load(Qualification.class, qfId);
	}

	public List<Qualification> loadAllQualification() {
		return super.loadAll(Qualification.class);
	}

	public List<Qualification> loadByKey(String keyName, Object keyValue) throws DaoException {
		return super.loadByKey(Qualification.class, keyName, keyValue);
	}

	public List<Qualification> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return super.loadByKeyArray(Qualification.class, keyNames, keyValues);
	}
}
