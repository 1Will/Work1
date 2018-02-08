package com.yongjun.tdms.service.project.state;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.state.ProjectState;

public interface ProjectStateManager {
	public abstract void storeProjectState(ProjectState paramProjectState);

	public abstract ProjectState loadProjectState(Long paramLong);

	public abstract List<ProjectState> loadAllProjectState(Long[] paramArrayOfLong);

	public abstract List<ProjectState> loadAllProjectStates();

	public abstract void deleteProjectState(ProjectState paramProjectState);

	public abstract void deleteAllProjectState(Collection<ProjectState> paramCollection);

	public abstract List<ProjectState> loadByKey(String paramString, Object paramObject) throws DaoException;

	public abstract List<ProjectState> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException;
}
