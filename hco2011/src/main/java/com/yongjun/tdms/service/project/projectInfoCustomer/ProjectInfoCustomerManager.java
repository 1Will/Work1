package com.yongjun.tdms.service.project.projectInfoCustomer;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;

import java.util.Collection;
import java.util.List;

public abstract interface ProjectInfoCustomerManager
{
	 public abstract void storeProjectInfoCustomer(ProjectInfoCustomer paramProjectInfoCustomer);

	  public abstract ProjectInfoCustomer loadProjectInfoCustomer(Long paramLong);

	  public abstract List<ProjectInfoCustomer> loadAllProjectInfoCustomer(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfoCustomer> loadAllProjectInfoCustomers();

	  public abstract void deleteProjectInfoCustomer(ProjectInfoCustomer paramProjectInfoCustomer);

	  public abstract void deleteAllProjectInfoCustomer(Collection<ProjectInfoCustomer> paramCollection);

	  public abstract void disabledProjectInfoCustomers(List<ProjectInfoCustomer> paramList);

	  public abstract void enableProjectInfoCustomers(List<ProjectInfoCustomer> paramList);


	  public abstract List<ProjectInfoCustomer> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfoCustomer> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

