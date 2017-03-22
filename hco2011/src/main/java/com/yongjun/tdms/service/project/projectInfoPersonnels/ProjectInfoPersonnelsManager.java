package com.yongjun.tdms.service.project.projectInfoPersonnels;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;

import java.util.Collection;
import java.util.List;

public abstract interface ProjectInfoPersonnelsManager
{
	 public abstract void storeProjectInfoPersonnels(ProjectInfoPersonnels paramProjectInfoPersonnels);

	  public abstract ProjectInfoPersonnels loadProjectInfoPersonnels(Long paramLong);

	  public abstract List<ProjectInfoPersonnels> loadAllProjectInfoPersonnels(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfoPersonnels> loadAllProjectInfoPersonnelss();

	  public abstract void deleteProjectInfoPersonnels(ProjectInfoPersonnels paramProjectInfoPersonnels);

	  public abstract void deleteAllProjectInfoPersonnels(Collection<ProjectInfoPersonnels> paramCollection);

	  public abstract void disabledProjectInfoPersonnelss(List<ProjectInfoPersonnels> paramList);

	  public abstract void enableProjectInfoPersonnelss(List<ProjectInfoPersonnels> paramList);


	  public abstract List<ProjectInfoPersonnels> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfoPersonnels> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

