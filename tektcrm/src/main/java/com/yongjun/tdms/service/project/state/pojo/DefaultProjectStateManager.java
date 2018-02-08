package com.yongjun.tdms.service.project.state.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.state.ProjectStateDao;
import com.yongjun.tdms.model.project.state.ProjectState;
import com.yongjun.tdms.service.project.state.ProjectStateManager;

public class DefaultProjectStateManager implements ProjectStateManager {
	private ProjectStateDao projectStateDao;

	public DefaultProjectStateManager(ProjectStateDao projectStateDao) {
		this.projectStateDao = projectStateDao;
	}

	public void deleteAllProjectState(Collection<ProjectState> ProjectStates) {
		this.projectStateDao.deleteAllProjectState(ProjectStates);
	}

	public void deleteProjectState(ProjectState ProjectState) {
		this.projectStateDao.deleteProjectState(ProjectState);
	}

	public List<ProjectState> loadAllProjectState(Long[] ProjectStateIds) {
		return this.projectStateDao.loadAllProjectState(ProjectStateIds);
	}

	public List<ProjectState> loadAllProjectStates() {
		return this.projectStateDao.loadAllProjectStates();
	}

	public ProjectState loadProjectState(Long ProjectStateId) {
		return this.projectStateDao.loadProjectState(ProjectStateId);
	}

	public void storeProjectState(ProjectState ProjectState) {
		this.projectStateDao.storeProjectState(ProjectState);
	}

	public List<ProjectState> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectStateDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectState> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectStateDao.loadByKeyArray(keyNames, keyValues);
	}
}
