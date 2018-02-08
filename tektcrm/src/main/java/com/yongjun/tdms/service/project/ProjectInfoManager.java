package com.yongjun.tdms.service.project;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.ProjectInfo;

public abstract interface ProjectInfoManager
{
	 public abstract void storeProjectInfo(ProjectInfo paramProjectInfo);
  
	  public abstract ProjectInfo loadProjectInfo(Long paramLong);

	  public abstract List<ProjectInfo> loadAllProjectInfo(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfo> loadAllProjectInfos();

	  public abstract void deleteProjectInfo(ProjectInfo paramProjectInfo);

	  public abstract void deleteAllProjectInfo(Collection<ProjectInfo> paramCollection);

	  public abstract void disabledProjectInfos(List<ProjectInfo> paramList);

	  public abstract void enableProjectInfos(List<ProjectInfo> paramList);


	  public abstract List<ProjectInfo> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfo> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
	  public int loadProjectByDate(String userId,String date);
	  
	  public List<String> loadProjectInfoByCustomerId(String customerId) throws DaoException;
	  public abstract void saveProjectPerAndPlan(long id_1,long id_2)throws DaoException;
	  public abstract void saveProductionOperationDetailPlan(long fromProductionOperationDetail,long toProductionOperationDetail)throws DaoException;
}

