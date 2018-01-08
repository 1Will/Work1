package com.yongjun.tdms.service.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;

import java.util.Collection;
import java.util.List;

public abstract interface HistoryTaskinstManager
{
  public abstract void storeHistoryTaskinst(HistoryTaskinst paramTask);

  public abstract void deleteHistoryTaskinst(HistoryTaskinst paramTask);

  public abstract void deleteAllHistoryTaskinsts(Collection<HistoryTaskinst> paramCollection);

  public abstract List<HistoryTaskinst> loadAllHistoryTaskinsts(Long[] paramArrayOfLong);

  public abstract HistoryTaskinst loadHistoryTaskinst(Long paramLong);

  public abstract List<HistoryTaskinst> loadAllTasks();

  public abstract List<HistoryTaskinst> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<HistoryTaskinst> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<HistoryTaskinst> paramList);

  public abstract String enabled(List<HistoryTaskinst> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.PointManager
 * JD-Core Version:    0.6.2
 */