package com.yongjun.tdms.service.marketmanager.activity.plan;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.activity.plan.Plan;
import java.util.Collection;
import java.util.List;

public abstract interface PlanManager
{
  public abstract void storePlan(Plan paramPlan);

  public abstract void deletePlan(Plan paramPlan);

  public abstract void deleteAllPlan(Collection<Plan> paramCollection);

  public abstract List<Plan> loadAllPlan(Long[] paramArrayOfLong);

  public abstract Plan loadPlan(Long paramLong);

  public abstract List<Plan> loadAllPlan();

  public abstract List<Plan> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Plan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllPlan(Collection<Plan> paramCollection);

  public abstract void enabledAllPlan(Collection<Plan> paramCollection);

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.activity.plan.PlanManager
 * JD-Core Version:    0.6.2
 */