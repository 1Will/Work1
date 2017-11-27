package com.yongjun.tdms.service.backvisit;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
import com.yongjun.tdms.model.backvisit.BackVisit;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

public abstract interface BackVisitManager
{
  public abstract void storeBackVisit(BackVisit paramBackVisit);

  public abstract BackVisit loadBackVisit(Long paramLong);

  public abstract List<BackVisit> loadAllBackVisit(Long[] paramArrayOfLong);

  public abstract List<BackVisit> loadAllBackVisits();
  
  public abstract List<BackVisit> loadBackVisitByPj(String hqlWord);

  public abstract void deleteBackVisit(BackVisit paramBackVisit);

  public abstract void deleteAllBackVisit(Collection<BackVisit> paramCollection);

  public abstract void disabledBackVisits(List<BackVisit> paramList);

  public abstract void enableBackVisits(List<BackVisit> paramList);

  public abstract void storeStepInfo(StepInfo paramStepInfo);

  public abstract List<BackVisit> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<BackVisit> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  
  public List<BackVisit> loadBackVisitByDate(String userId,String da) throws DaoException, ParseException;

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.backvisit.BackVisitManager
 * JD-Core Version:    0.6.2
 */