package com.yongjun.tdms.dao.project.state.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.state.ProjectStateDao;
import com.yongjun.tdms.model.project.state.ProjectState;

public class HibernateProjectState extends BaseHibernateDao implements ProjectStateDao{
	public void deleteAllProjectState(Collection<ProjectState> ProjectStates) {
		deleteAll(ProjectStates);
	}

	public void deleteProjectState(ProjectState ProjectState) {
		delete(ProjectState);
	}

	public List<ProjectState> loadAllProjectState(Long[] ProjectStateIds) {
		return loadAll(ProjectState.class, ProjectStateIds);
	}

	public List<ProjectState> loadAllProjectStates() {
		return loadAll(ProjectState.class);
	}

	public ProjectState loadProjectState(Long ProjectStateId) {
		return (ProjectState) load(ProjectState.class, ProjectStateId);
	}

	public List<ProjectState> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProjectState.class, keyName, keyValue);
	}

	public List<ProjectState> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ProjectState.class, keyNames, keyValues);
	}

	public void storeProjectState(ProjectState ProjectState) {
		store(ProjectState);
	}
}
