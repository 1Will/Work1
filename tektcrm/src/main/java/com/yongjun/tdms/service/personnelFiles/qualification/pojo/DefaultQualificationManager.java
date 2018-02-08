package com.yongjun.tdms.service.personnelFiles.qualification.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.personnelFiles.qualification.QualificationDao;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;
import com.yongjun.tdms.service.personnelFiles.qualification.QualificationManager;

public class DefaultQualificationManager implements QualificationManager{

	private final QualificationDao qualificationDao;

	public DefaultQualificationManager(QualificationDao qualificationDao) {
		this.qualificationDao = qualificationDao;
	}

	public void storeQualification(Qualification qf) {
		this.qualificationDao.storeQualification(qf);
	}

	public void deleteQualification(Qualification qf) {
		this.qualificationDao.deleteQualification(qf);
	}

	public void deleteAllQualification(Collection<Qualification> qfs) {
		this.qualificationDao.deleteAllQualification(qfs);
	}

	public List<Qualification> loadAllQualification(Long[] qfIds) {
		return this.qualificationDao.loadAllQualification(qfIds);
	}

	public Qualification loadQualification(Long qfId) {
		return this.qualificationDao.loadQualification(qfId);
	}

	public List<Qualification> loadAllQualification() {
		return this.qualificationDao.loadAllQualification();
	}

	public List<Qualification> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.qualificationDao.loadByKey(keyName, keyValue);
	}

	public List<Qualification> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.qualificationDao.loadByKeyArray(keyNames, keyValues);
	}

}
