package com.yongjun.tdms.dao.customercontract.plan;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import java.util.Collection;
import java.util.List;

public abstract interface ReturnPlanDao
{
  public abstract void storeReturnPlan(ReturnPlan paramReturnPlan);

  public abstract void deleteReturnPlan(ReturnPlan paramReturnPlan);

  public abstract void deleteAllReturnPlans(Collection<ReturnPlan> paramCollection);

  public abstract List<ReturnPlan> loadAllReturnPlans(Long[] paramArrayOfLong);

  public abstract ReturnPlan loadReturnPlan(Long paramLong);

  public abstract List<ReturnPlan> loadAllReturnPlans();

  public abstract List<ReturnPlan> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ReturnPlan> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List<ReturnPlan> contractManagementAndBatch(Long paramLong1, Long paramLong2, String paramString, boolean paramBoolean);

  public abstract List<CodeValue> contractAndBatch(Long paramLong, boolean paramBoolean);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.customercontract.plan.ReturnPlanDao
 * JD-Core Version:    0.6.2
 */