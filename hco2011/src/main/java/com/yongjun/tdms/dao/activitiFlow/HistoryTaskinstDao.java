package com.yongjun.tdms.dao.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.model.workflow.Point;

import java.util.Collection;
import java.util.List;

public abstract interface HistoryTaskinstDao
{
  public abstract void storeHistoryTaskinst(HistoryTaskinst paramPoint);

  public abstract void deleteHistoryTaskinst(HistoryTaskinst paramPoint);

  public abstract void deleteAllHistoryTaskinst(Collection<HistoryTaskinst> paramCollection);

  public abstract List<HistoryTaskinst> loadAllHistoryTaskinst(Long[] paramArrayOfLong);

  public abstract HistoryTaskinst loadHistoryTaskinst(Long paramLong);

  public abstract List<HistoryTaskinst> loadAllHistoryTaskinst();

  public abstract List<HistoryTaskinst> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<HistoryTaskinst> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.PointDao
 * JD-Core Version:    0.6.2
 */