package com.yongjun.tdms.dao.project.projectContactArchives.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.project.projectContactArchives.ProjectContactArchivesDao;
import com.yongjun.tdms.model.project.projectContactArchives.ProjectContactArchives;

public class HibernateProjectContactArchives extends BaseHibernateDao implements ProjectContactArchivesDao{
	public void deleteAllProjectContactArchives(Collection<ProjectContactArchives> ProjectContactArchivess) {
		deleteAll(ProjectContactArchivess);
	}

	public void deleteProjectContactArchives(ProjectContactArchives ProjectContactArchives) {
		delete(ProjectContactArchives);
	}

	public List<ProjectContactArchives> loadAllProjectContactArchives(Long[] ProjectContactArchivesIds) {
		return loadAll(ProjectContactArchives.class, ProjectContactArchivesIds);
	}

	public List<ProjectContactArchives> loadAllProjectContactArchivess() {
		return loadAll(ProjectContactArchives.class);
	}

	public ProjectContactArchives loadProjectContactArchives(Long ProjectContactArchivesId) {
		return (ProjectContactArchives) load(ProjectContactArchives.class, ProjectContactArchivesId);
	}

	public List<ProjectContactArchives> loadByKey(String keyName, Object keyValue) throws DaoException {
		return loadByKey(ProjectContactArchives.class, keyName, keyValue);
	}

	public List<ProjectContactArchives> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return loadByKeyArray(ProjectContactArchives.class, keyNames, keyValues);
	}

	public void storeProjectContactArchives(ProjectContactArchives ProjectContactArchives) {
		store(ProjectContactArchives);
	}
}
