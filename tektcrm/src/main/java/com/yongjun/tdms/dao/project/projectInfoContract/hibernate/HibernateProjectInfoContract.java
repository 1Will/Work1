package com.yongjun.tdms.dao.project.projectInfoContract.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectInfoContract.ProjectInfoContractDao;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;

import java.util.Collection;
import java.util.List;

public class HibernateProjectInfoContract extends BaseHibernateDao implements ProjectInfoContractDao {
	public void deleteAllProjectInfoContract(Collection<ProjectInfoContract> ProjectInfoContracts) {
		deleteAll(ProjectInfoContracts);
	}

	public void deleteProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
		delete(ProjectInfoContract);
	}

	public List<ProjectInfoContract> loadAllProjectInfoContract(Long[] ProjectInfoContractIds) {
		return loadAll(ProjectInfoContract.class, ProjectInfoContractIds);
	}

	public List<ProjectInfoContract> loadAllProjectInfoContracts() {
		return loadAll(ProjectInfoContract.class);
	}

	public ProjectInfoContract loadProjectInfoContract(Long ProjectInfoContractId) {
		return (ProjectInfoContract) load(ProjectInfoContract.class, ProjectInfoContractId);
	}

	public List<ProjectInfoContract> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProjectInfoContract.class, keyName, keyValue);
	}

	public List<ProjectInfoContract> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ProjectInfoContract.class, keyNames, keyValues);
	}

	public void storeProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
		store(ProjectInfoContract);
	}

}