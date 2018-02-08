package com.yongjun.tdms.dao.project.projectInfoCustomer;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;

import java.util.Collection;
import java.util.List;

public abstract interface ProjectInfoCustomerDao
{
	 public abstract void storeProjectInfoCustomer(ProjectInfoCustomer paramprojectInfoCustomer);

	  public abstract ProjectInfoCustomer loadProjectInfoCustomer(Long paramLong);

	  public abstract List<ProjectInfoCustomer> loadAllProjectInfoCustomer(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfoCustomer> loadAllProjectInfoCustomers();

	  public abstract void deleteProjectInfoCustomer(ProjectInfoCustomer paramprojectInfoCustomer);

	  public abstract void deleteAllProjectInfoCustomer(Collection<ProjectInfoCustomer> paramCollection);

	  public abstract List<ProjectInfoCustomer> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfoCustomer> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

