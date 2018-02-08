package com.yongjun.tdms.service.project.projectInfoPlan;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.project.projectInfoPlan.ProPlanTemplet;

public abstract interface ProPlanTempletManager {
	 public abstract void storeProPlanTemplet(ProPlanTemplet paramProPlanTemplet);

	  public abstract ProPlanTemplet loadProPlanTemplet(Long paramLong);

	  public abstract List<ProPlanTemplet> loadAllProPlanTemplet(Long[] paramArrayOfLong);

	  public abstract List<ProPlanTemplet> loadAllProPlanTemplets();

	  public abstract void deleteProPlanTemplet(ProPlanTemplet paramProPlanTemplet);

	  public abstract void deleteAllProPlanTemplet(Collection<ProPlanTemplet> paramCollection);

	  public abstract List<ProPlanTemplet> loadByKey(String paramString, Object paramObject)
	    throws DaoException;

	  public abstract List<ProPlanTemplet> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
	    throws DaoException;
}
