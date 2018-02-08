package com.yongjun.tdms.dao.workflow.flow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workflow.Flow;
import java.util.Collection;
import java.util.List;

public abstract interface FlowDao
{
  public abstract void storeFlow(Flow paramFlow);

  public abstract void deleteFlow(Flow paramFlow);

  public abstract void deleteAllFlows(Collection<Flow> paramCollection);

  public abstract List<Flow> loadAllFlows(Long[] paramArrayOfLong);

  public abstract Flow loadFlow(Long paramLong);

  public abstract List<Flow> loadAllFlows();

  public abstract List<Flow> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Flow> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.flow.FlowDao
 * JD-Core Version:    0.6.2
 */