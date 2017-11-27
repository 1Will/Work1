package com.yongjun.tdms.service.project.projectInfoProduct;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoProduct.ProjectInfoProduct;

import java.util.Collection;
import java.util.List;

public abstract interface ProjectInfoProductManager
{
	 public abstract void storeProjectInfoProduct(ProjectInfoProduct paramProjectInfoProduct);
	 public abstract void storeProjectInfoProducts(ProjectInfo projectInfo, String [] arrId);

	  public abstract ProjectInfoProduct loadProjectInfoProduct(Long paramLong);

	  public abstract List<ProjectInfoProduct> loadAllProjectInfoProduct(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfoProduct> loadAllProjectInfoProducts();

	  public abstract void deleteProjectInfoProduct(ProjectInfoProduct paramProjectInfoProduct);

	  public abstract void deleteAllProjectInfoProduct(Collection<ProjectInfoProduct> paramCollection);

	  public abstract void disabledProjectInfoProducts(List<ProjectInfoProduct> paramList);

	  public abstract void enableProjectInfoProducts(List<ProjectInfoProduct> paramList);


	  public abstract List<ProjectInfoProduct> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfoProduct> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}

