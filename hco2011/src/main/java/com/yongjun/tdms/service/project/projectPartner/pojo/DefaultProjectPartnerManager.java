package com.yongjun.tdms.service.project.projectPartner.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectPartner.ProjectPartnerDao;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;
import com.yongjun.tdms.service.project.projectPartner.ProjectPartnerManager;

public class DefaultProjectPartnerManager extends BaseManager implements ProjectPartnerManager {

	private ProjectPartnerDao projectPartnerDao;

	public DefaultProjectPartnerManager(ProjectPartnerDao projectPartnerDao) {
		this.projectPartnerDao = projectPartnerDao;
	}

	public void deleteAllProjectPartner(Collection<ProjectPartner> ProjectPartners) {
		this.projectPartnerDao.deleteAllProjectPartner(ProjectPartners);
	}

	public void deleteProjectPartner(ProjectPartner ProjectPartner) {
		this.projectPartnerDao.deleteProjectPartner(ProjectPartner);
	}

	public List<ProjectPartner> loadAllProjectPartner(Long[] ProjectPartnerIds) {
		return this.projectPartnerDao.loadAllProjectPartner(ProjectPartnerIds);
	}

	public List<ProjectPartner> loadAllProjectPartners() {
		return this.projectPartnerDao.loadAllProjectPartners();
	}

	public ProjectPartner loadProjectPartner(Long ProjectPartnerId) {
		return this.projectPartnerDao.loadProjectPartner(ProjectPartnerId);
	}

	public void storeProjectPartner(ProjectPartner ProjectPartner) {
		this.projectPartnerDao.storeProjectPartner(ProjectPartner);
	}

	public void setprojectPartnerDao(ProjectPartnerDao projectPartnerDao) {
		this.projectPartnerDao = projectPartnerDao;
	}

	public void disabledProjectPartners(List<ProjectPartner> ProjectPartners) {
		for (ProjectPartner bv : ProjectPartners) {
			bv.setDisabled(true);
			this.projectPartnerDao.storeProjectPartner(bv);
		}
	}

	public void enableProjectPartners(List<ProjectPartner> ProjectPartners) {
		for (ProjectPartner bv : ProjectPartners) {
			bv.setDisabled(false);
			this.projectPartnerDao.storeProjectPartner(bv);
		}
	}

	public List<ProjectPartner> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectPartnerDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectPartner> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectPartnerDao.loadByKeyArray(keyNames, keyValues);
	}

	public ProjectPartnerDao getProjectPartnerDao() {
		return projectPartnerDao;
	}

	public void setProjectPartnerDao(ProjectPartnerDao projectPartnerDao) {
		this.projectPartnerDao = projectPartnerDao;
	}
}
