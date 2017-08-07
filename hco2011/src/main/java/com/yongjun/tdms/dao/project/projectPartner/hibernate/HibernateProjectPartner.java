package com.yongjun.tdms.dao.project.projectPartner.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectPartner.ProjectPartnerDao;
import com.yongjun.tdms.model.project.projectPartner.ProjectPartner;

public class HibernateProjectPartner extends BaseHibernateDao implements ProjectPartnerDao{
	public void deleteAllProjectPartner(Collection<ProjectPartner> ProjectPartners) {
		deleteAll(ProjectPartners);
	}

	public void deleteProjectPartner(ProjectPartner ProjectPartner) {
		delete(ProjectPartner);
	}

	public List<ProjectPartner> loadAllProjectPartner(Long[] ProjectPartnerIds) {
		return loadAll(ProjectPartner.class, ProjectPartnerIds);
	}

	public List<ProjectPartner> loadAllProjectPartners() {
		return loadAll(ProjectPartner.class);
	}

	public ProjectPartner loadProjectPartner(Long ProjectPartnerId) {
		return (ProjectPartner) load(ProjectPartner.class, ProjectPartnerId);
	}

	public List<ProjectPartner> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProjectPartner.class, keyName, keyValue);
	}

	public List<ProjectPartner> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ProjectPartner.class, keyNames, keyValues);
	}

	public void storeProjectPartner(ProjectPartner ProjectPartner) {
		store(ProjectPartner);
	}

}
