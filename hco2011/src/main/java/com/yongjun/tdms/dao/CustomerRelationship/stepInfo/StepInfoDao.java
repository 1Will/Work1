package com.yongjun.tdms.dao.CustomerRelationship.stepInfo;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
import java.util.List;

public abstract interface StepInfoDao
{
  public abstract void storeStepInfo(StepInfo paramStepInfo);

  public abstract StepInfo loadStepInfo(Long paramLong);

  public abstract List<StepInfo> loadAllStepInfo();

  public abstract List<StepInfo> loadAllStepInfo(Long[] paramArrayOfLong);

  public abstract void deleteStepInfo(StepInfo paramStepInfo);

  public abstract void deleteAllStepInfo(List<StepInfo> paramList);

  public abstract List<StepInfo> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.stepInfo.StepInfoDao
 * JD-Core Version:    0.6.2
 */