package com.yongjun.tdms.service.CustomerRelationship.qualification.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.CustomerRelationship.qualification.QualificationDao;
import com.yongjun.tdms.model.CustomerRelationship.qualification.CustomerRelationshipQualification;
import com.yongjun.tdms.service.CustomerRelationship.qualification.QualificationManager;

public class DefaultQualificationManager extends BaseManager implements QualificationManager {
	public final QualificationDao customerRelationshipQualificationDao;

	public DefaultQualificationManager(QualificationDao qualificationDao) {
		this.customerRelationshipQualificationDao = qualificationDao;
	}

	public void storeQualification(CustomerRelationshipQualification ca) {
		this.customerRelationshipQualificationDao.storeQualification(ca);
	}


	public CustomerRelationshipQualification loadQualification(Long caId) {
		return this.customerRelationshipQualificationDao.loadQualification(caId);
	}

	public List<CustomerRelationshipQualification> loadAllQualification() {
		return this.customerRelationshipQualificationDao.loadAllQualification();
	}

	public List<CustomerRelationshipQualification> loadAllQualification(Long[] caIds) {
		return this.customerRelationshipQualificationDao.loadAllQualification(caIds);
	}

	public void deleteQualification(CustomerRelationshipQualification ca) {
		this.customerRelationshipQualificationDao.deleteQualification(ca);
	}

	public void deleteAllQualification(List<CustomerRelationshipQualification> caIds) {
		this.customerRelationshipQualificationDao.deleteAllQualification(caIds);
	}

	public List<CustomerRelationshipQualification> loadByKey(String key, Object value) throws DaoException {
		return this.customerRelationshipQualificationDao.loadByKey(key, value);
	}

	public void disabledAllQualification(List<CustomerRelationshipQualification> cas) {
		for (CustomerRelationshipQualification archives : cas) {
			archives.setDisabled(true);
			this.customerRelationshipQualificationDao.storeQualification(archives);
		}
	}

	public void enabledAllQualification(List<CustomerRelationshipQualification> cas) {
		for (CustomerRelationshipQualification archives : cas) {
			archives.setDisabled(false);
			this.customerRelationshipQualificationDao.storeQualification(archives);
		}
	}

	public List<CustomerRelationshipQualification> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.customerRelationshipQualificationDao.loadByKeyArray(keyNames, keyValues);
	}

}
