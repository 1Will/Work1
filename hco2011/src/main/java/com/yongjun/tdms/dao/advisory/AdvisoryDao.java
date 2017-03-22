package com.yongjun.tdms.dao.advisory;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.advisory.Advisory;
import java.util.Collection;
import java.util.List;

public abstract interface AdvisoryDao
{
  public abstract void storeAdvisory(Advisory paramAdvisory);

  public abstract Advisory loadAdvisory(Long paramLong);

  public abstract List<Advisory> loadAllAdvisory(Long[] paramArrayOfLong);

  public abstract List<Advisory> loadAllAdvisorys();

  public abstract void deleteAdvisory(Advisory paramAdvisory);

  public abstract void deleteAllAdvisory(Collection<Advisory> paramCollection);

  public abstract List<Advisory> loadByKey(String paramString, Object paramObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.advisory.AdvisoryDao
 * JD-Core Version:    0.6.2
 */