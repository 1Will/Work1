package com.yongjun.tdms.service.project.projectInfoPlan;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.taglibs.standard.lang.jstl.Literal;

public abstract interface ProjectInfoPlanManager
{
	 public abstract void storeProjectInfoPlan(ProjectInfoPlan paramProjectInfoPlan);

	  public abstract ProjectInfoPlan loadProjectInfoPlan(Long paramLong);

	  public abstract List<ProjectInfoPlan> loadAllProjectInfoPlan(Long[] paramArrayOfLong);

	  public abstract List<ProjectInfoPlan> loadAllProjectInfoPlans();

	  public abstract void deleteProjectInfoPlan(ProjectInfoPlan paramProjectInfoPlan);

	  public abstract void deleteAllProjectInfoPlan(Collection<ProjectInfoPlan> paramCollection);

	  public abstract void disabledProjectInfoPlans(List<ProjectInfoPlan> paramList);

	  public abstract void enableProjectInfoPlans(List<ProjectInfoPlan> paramList);


	  public abstract List<ProjectInfoPlan> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProjectInfoPlan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
	  public abstract List loadForMyTeam(HashMap map);
}

