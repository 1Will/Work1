package com.yongjun.tdms.dao.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;
import com.yongjun.tdms.model.workflow.Point;

import java.util.Collection;
import java.util.List;

public abstract interface CopySendPersonDao
{
  public abstract void storeCopySendPerson(CopySendPerson paramPoint);

  public abstract void deleteCopySendPerson(CopySendPerson paramPoint);

  public abstract void deleteAllCopySendPersons(Collection<CopySendPerson> paramCollection);

  public abstract List<CopySendPerson> loadAllCopySendPersons(Long[] paramArrayOfLong);

  public abstract CopySendPerson loadCopySendPerson(Long paramLong);

  public abstract List<CopySendPerson> loadAllCopySendPersons();
  public abstract List<CopySendPerson> loadAllByHql(String hql);

  public abstract List<CopySendPerson> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CopySendPerson> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.workflow.point.PointDao
 * JD-Core Version:    0.6.2
 */