package com.yongjun.tdms.dao.project.projectInfoContract;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;

import java.util.Collection;
import java.util.List;

public abstract interface ProjectInfoContractDao
{
	 public abstract void storeProjectInfoContract(ProjectInfoContract paramProjectInfoContract);

	  public abstract ProjectInfoContract loadProjectInfoContract(Long paramLong);

	  public abstract List<ProjectInfoContract> loadAllProjectInfoContract(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfoContract> loadAllProjectInfoContracts();

	  public abstract void deleteProjectInfoContract(ProjectInfoContract paramProjectInfoContract);

	  public abstract void deleteAllProjectInfoContract(Collection<ProjectInfoContract> paramCollection);

	  public abstract List<ProjectInfoContract> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfoContract> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

