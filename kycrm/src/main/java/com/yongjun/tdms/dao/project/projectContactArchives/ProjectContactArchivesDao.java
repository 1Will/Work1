package com.yongjun.tdms.dao.project.projectContactArchives;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectContactArchives.ProjectContactArchives;

public interface ProjectContactArchivesDao {
	public abstract void storeProjectContactArchives(ProjectContactArchives paramProjectContactArchives);

	public abstract ProjectContactArchives loadProjectContactArchives(Long paramLong);

	public abstract List<ProjectContactArchives> loadAllProjectContactArchives(Long[] paramArrayOfLong);

	public abstract List<ProjectContactArchives> loadAllProjectContactArchivess();

	public abstract void deleteProjectContactArchives(ProjectContactArchives paramProjectContactArchives);

	public abstract void deleteAllProjectContactArchives(Collection<ProjectContactArchives> paramCollection);

	public abstract List<ProjectContactArchives> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ProjectContactArchives> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}