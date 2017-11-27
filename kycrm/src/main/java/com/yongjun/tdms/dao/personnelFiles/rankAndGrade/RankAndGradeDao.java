package com.yongjun.tdms.dao.personnelFiles.rankAndGrade;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.rankAndGrade.RankAndGrade;

public abstract interface RankAndGradeDao
{
  public abstract void storeRankAndGrade(RankAndGrade paramRankAndGrade);

  public abstract void deleteRankAndGrade(RankAndGrade paramRankAndGrade);

  public abstract void deleteAllRankAndGrade(Collection<RankAndGrade> paramCollection);

  public abstract List<RankAndGrade> loadAllRankAndGrade(Long[] paramArrayOfLong);

  public abstract RankAndGrade loadRankAndGrade(Long paramLong);

  public abstract List<RankAndGrade> loadAllRankAndGrade();

  public abstract List<RankAndGrade> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<RankAndGrade> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.personnel.RankAndGradeDao
 * JD-Core Version:    0.6.2
 */