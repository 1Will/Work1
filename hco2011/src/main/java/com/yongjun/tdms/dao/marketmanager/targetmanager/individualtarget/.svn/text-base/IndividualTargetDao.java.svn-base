package com.yongjun.tdms.dao.marketmanager.targetmanager.individualtarget;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.targetmanager.individualtarget.IndividualTarget;
import java.util.List;

public abstract interface IndividualTargetDao
{
  public abstract void storeIndividualTarget(IndividualTarget paramIndividualTarget);

  public abstract IndividualTarget loadIndividualTarget(Long paramLong);

  public abstract List<IndividualTarget> loadIndividualTarget();

  public abstract List<IndividualTarget> loadAllIndividualTarget(Long[] paramArrayOfLong);

  public abstract void deleteIndividualTarget(IndividualTarget paramIndividualTarget);

  public abstract void deleteAllIndividualTarget(List<IndividualTarget> paramList);

  public abstract List<IndividualTarget> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<IndividualTarget> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.targetmanager.individualtarget.IndividualTargetDao
 * JD-Core Version:    0.6.2
 */