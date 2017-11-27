package com.yongjun.tdms.service.project.projectContactArchives.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectContactArchives.ProjectContactArchivesDao;
import com.yongjun.tdms.model.project.projectContactArchives.ProjectContactArchives;
import com.yongjun.tdms.service.project.projectContactArchives.ProjectContactArchivesManager;

public class DefaultProjectContactArchivesManager extends BaseManager implements ProjectContactArchivesManager{
	private ProjectContactArchivesDao projectContactArchivesDao;

	public DefaultProjectContactArchivesManager(ProjectContactArchivesDao projectContactArchivesDao) {
		this.projectContactArchivesDao = projectContactArchivesDao;
	}

	public void deleteAllProjectContactArchives(Collection<ProjectContactArchives> projectInfoContactArchives) {
		this.projectContactArchivesDao.deleteAllProjectContactArchives(projectInfoContactArchives);
	}

	public void deleteProjectContactArchives(ProjectContactArchives ProjectContactArchives) {
		this.projectContactArchivesDao.deleteProjectContactArchives(ProjectContactArchives);
	}

	public List<ProjectContactArchives> loadAllProjectContactArchives(Long[] ProjectContactArchivesIds) {
		return this.projectContactArchivesDao.loadAllProjectContactArchives(ProjectContactArchivesIds);
	}

	public List<ProjectContactArchives> loadAllProjectContactArchivess() {
		return this.projectContactArchivesDao.loadAllProjectContactArchivess();
	}

	public ProjectContactArchives loadProjectContactArchives(Long ProjectContactArchivesId) {
		return this.projectContactArchivesDao.loadProjectContactArchives(ProjectContactArchivesId);
	}

	public void storeProjectContactArchives(ProjectContactArchives ProjectContactArchives) {
		this.projectContactArchivesDao.storeProjectContactArchives(ProjectContactArchives);
	}

	public void setProjectPersonnelDao(ProjectContactArchivesDao projectContactArchivesDao) {
		this.projectContactArchivesDao = projectContactArchivesDao;
	}

	public void disabledProjectContactArchivess(List<ProjectContactArchives> ProjectContactArchivess) {
		for (ProjectContactArchives bv : ProjectContactArchivess) {
			bv.setDisabled(true);
			this.projectContactArchivesDao.storeProjectContactArchives(bv);
		}
	}

	public void enableProjectContactArchivess(List<ProjectContactArchives> ProjectContactArchivess) {
		for (ProjectContactArchives bv : ProjectContactArchivess) {
			bv.setDisabled(false);
			this.projectContactArchivesDao.storeProjectContactArchives(bv);
		}
	}

	public List<ProjectContactArchives> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectContactArchivesDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectContactArchives> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectContactArchivesDao.loadByKeyArray(keyNames, keyValues);
	}
}
