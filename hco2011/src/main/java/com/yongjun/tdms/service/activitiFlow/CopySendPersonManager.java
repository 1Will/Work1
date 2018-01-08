package com.yongjun.tdms.service.activitiFlow;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.activitiFlow.CopySendPerson;

import java.util.Collection;
import java.util.List;

public abstract interface CopySendPersonManager
{
  public abstract void storeCopySendPerson(CopySendPerson paramPoint);

  public abstract void deleteCopySendPerson(CopySendPerson paramPoint);

  public abstract void deleteAllCopySendPersons(Collection<CopySendPerson> paramCollection);

  public abstract List<CopySendPerson> loadAllCopySendPersons(Long[] paramArrayOfLong);

  public abstract CopySendPerson loadCopySendPerson(Long paramLong);

  public abstract List<CopySendPerson> loadAllCopySendPersons();

  public abstract List<CopySendPerson> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CopySendPerson> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String disabled(List<CopySendPerson> paramList);

  public abstract String enabled(List<CopySendPerson> paramList);
  
  //新建抄送人
  public abstract void saveCopySendPerson(String idString,String flowId,String bussnessId);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workflow.point.PointManager
 * JD-Core Version:    0.6.2
 */