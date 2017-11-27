package com.yongjun.tdms.dao.marketmanager.competitor;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.competitor.Competitor;
import java.util.Collection;
import java.util.List;

public abstract interface CompetitorDao
{
  public abstract void storeCompetitor(Competitor paramCompetitor);

  public abstract void deleteCompetitor(Competitor paramCompetitor);

  public abstract void deleteAllCompetitor(Collection<Competitor> paramCollection);

  public abstract List<Competitor> loadAllCompetitor(Long[] paramArrayOfLong);

  public abstract Competitor loadCompetitor(Long paramLong);

  public abstract List<Competitor> loadAllCompetitor();

  public abstract List<Competitor> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Competitor> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.competitor.CompetitorDao
 * JD-Core Version:    0.6.2
 */