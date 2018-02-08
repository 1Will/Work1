package com.yongjun.tdms.dao.project;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.ProjectInfo;

import java.util.Collection;
import java.util.List;

public abstract interface ProjectInfoDao
{
	 public abstract void storeProjectInfo(ProjectInfo paramProjectInfo);

	  public abstract ProjectInfo loadProjectInfo(Long paramLong);

	  public abstract List<ProjectInfo> loadAllProjectInfo(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfo> loadAllProjectInfos();

	  public abstract void deleteProjectInfo(ProjectInfo paramProjectInfo);

	  public abstract void deleteAllProjectInfo(Collection<ProjectInfo> paramCollection);

	  public abstract List<ProjectInfo> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfo> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
	  public List<ProjectInfo> loadByDate(String date,String name);

	  public abstract int loadProjectInfoCountByName(String name);
}

