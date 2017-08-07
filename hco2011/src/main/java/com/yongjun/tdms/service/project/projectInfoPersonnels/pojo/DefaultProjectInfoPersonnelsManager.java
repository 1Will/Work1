package com.yongjun.tdms.service.project.projectInfoPersonnels.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoPersonnels.ProjectInfoPersonnelDao;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

public class DefaultProjectInfoPersonnelsManager extends BaseManager implements ProjectInfoPersonnelsManager {
	private ProjectInfoPersonnelDao projectInfoPersonnelDao;

	public DefaultProjectInfoPersonnelsManager(ProjectInfoPersonnelDao projectInfoPersonnelDao) {
		this.projectInfoPersonnelDao = projectInfoPersonnelDao;
	}

	public void deleteAllProjectInfoPersonnels(Collection<ProjectInfoPersonnels> projectInfoPersonnels) {
		this.projectInfoPersonnelDao.deleteAllProjectInfoPersonnels(projectInfoPersonnels);
	}

	public void deleteProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
		this.projectInfoPersonnelDao.deleteProjectInfoPersonnels(ProjectInfoPersonnels);
	}

	public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnels(Long[] ProjectInfoPersonnelsIds) {
		return this.projectInfoPersonnelDao.loadAllProjectInfoPersonnels(ProjectInfoPersonnelsIds);
	}

	public List<ProjectInfoPersonnels> loadAllProjectInfoPersonnelss() {
		return this.projectInfoPersonnelDao.loadAllProjectInfoPersonnelss();
	}

	public ProjectInfoPersonnels loadProjectInfoPersonnels(Long ProjectInfoPersonnelsId) {
		return this.projectInfoPersonnelDao.loadProjectInfoPersonnels(ProjectInfoPersonnelsId);
	}

	public void storeProjectInfoPersonnels(ProjectInfoPersonnels ProjectInfoPersonnels) {
		this.projectInfoPersonnelDao.storeProjectInfoPersonnels(ProjectInfoPersonnels);
	}

	public void setProjectInfoPersonnelDao(ProjectInfoPersonnelDao projectInfoPersonnelDao) {
		this.projectInfoPersonnelDao = projectInfoPersonnelDao;
	}

	public void disabledProjectInfoPersonnelss(List<ProjectInfoPersonnels> ProjectInfoPersonnelss) {
		for (ProjectInfoPersonnels bv : ProjectInfoPersonnelss) {
			bv.setDisabled(true);
			this.projectInfoPersonnelDao.storeProjectInfoPersonnels(bv);
		}
	}

	public void enableProjectInfoPersonnelss(List<ProjectInfoPersonnels> ProjectInfoPersonnelss) {
		for (ProjectInfoPersonnels bv : ProjectInfoPersonnelss) {
			bv.setDisabled(false);
			this.projectInfoPersonnelDao.storeProjectInfoPersonnels(bv);
		}
	}

	public List<ProjectInfoPersonnels> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectInfoPersonnelDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectInfoPersonnels> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectInfoPersonnelDao.loadByKeyArray(keyNames, keyValues);
	}

	public ProjectInfoPersonnelDao getprojectInfoPersonnelDao() {
		return projectInfoPersonnelDao;
	}

	public void setprojectInfoPersonnelDao(ProjectInfoPersonnelDao projectInfoPersonnelDao) {
		this.projectInfoPersonnelDao = projectInfoPersonnelDao;
	}

	public List<String> loadPersonnelsCodeByProjectInfoId(Long projectInfoId) {
		// TODO Auto-generated method stub
		return this.projectInfoPersonnelDao.loadPersonnelsCodeByProjectInfoId(projectInfoId);
	}

	public List<String> loadPersonnelsCodeByEnable() {
		// TODO Auto-generated method stub
		return this.projectInfoPersonnelDao.loadPersonnelsCodeByEnable();
	}

}
