package com.yongjun.tdms.dao.marketmanager.intelligence;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.intelligence.Intelligence;
import java.util.Collection;
import java.util.List;

public abstract interface IntelligenceDao
{
  public abstract void storeIntelligence(Intelligence paramIntelligence);

  public abstract void deleteIntelligence(Intelligence paramIntelligence);

  public abstract void deleteAllIntelligence(Collection<Intelligence> paramCollection);

  public abstract List<Intelligence> loadAllIntelligence(Long[] paramArrayOfLong);

  public abstract Intelligence loadIntelligence(Long paramLong);

  public abstract List<Intelligence> loadAllIntelligence();

  public abstract List<Intelligence> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Intelligence> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.intelligence.IntelligenceDao
 * JD-Core Version:    0.6.2
 */