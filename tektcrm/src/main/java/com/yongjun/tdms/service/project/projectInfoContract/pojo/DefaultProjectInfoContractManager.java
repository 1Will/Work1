package com.yongjun.tdms.service.project.projectInfoContract.pojo;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoContract.ProjectInfoContractDao;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;

import java.util.Collection;
import java.util.List;

public class DefaultProjectInfoContractManager extends BaseManager implements ProjectInfoContractManager {
	private ProjectInfoContractDao projectInfoContractDao;

	public DefaultProjectInfoContractManager(ProjectInfoContractDao projectInfoContractDao) {
		this.projectInfoContractDao = projectInfoContractDao;
	}

	public void deleteAllProjectInfoContract(Collection<ProjectInfoContract> ProjectInfoContract) {
		this.projectInfoContractDao.deleteAllProjectInfoContract(ProjectInfoContract);
	}

	public void deleteProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
		this.projectInfoContractDao.deleteProjectInfoContract(ProjectInfoContract);
	}

	public List<ProjectInfoContract> loadAllProjectInfoContract(Long[] ProjectInfoContractIds) {
		return this.projectInfoContractDao.loadAllProjectInfoContract(ProjectInfoContractIds);
	}

	public List<ProjectInfoContract> loadAllProjectInfoContracts() {
		return this.projectInfoContractDao.loadAllProjectInfoContracts();
	}

	public ProjectInfoContract loadProjectInfoContract(Long ProjectInfoContractId) {
		return this.projectInfoContractDao.loadProjectInfoContract(ProjectInfoContractId);
	}

	public void storeProjectInfoContract(ProjectInfoContract ProjectInfoContract) {
		this.projectInfoContractDao.storeProjectInfoContract(ProjectInfoContract);
	}

	public void disabledProjectInfoContracts(List<ProjectInfoContract> ProjectInfoContracts) {
		for (ProjectInfoContract bv : ProjectInfoContracts) {
			bv.setDisabled(true);
			this.projectInfoContractDao.storeProjectInfoContract(bv);
		}
	}

	public void enableProjectInfoContracts(List<ProjectInfoContract> ProjectInfoContracts) {
		for (ProjectInfoContract bv : ProjectInfoContracts) {
			bv.setDisabled(false);
			this.projectInfoContractDao.storeProjectInfoContract(bv);
		}
	}

	public List<ProjectInfoContract> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectInfoContractDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectInfoContract> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectInfoContractDao.loadByKeyArray(keyNames, keyValues);
	}

}
