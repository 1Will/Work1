package com.yongjun.tdms.dao.personnelFiles.becomesmanager;

import com.yongjun.tdms.model.personnelFiles.Becomes;
import java.util.List;

public abstract interface BecomesDao
{
  public abstract void storeBecomes(Becomes paramBecomes);

  public abstract List<Becomes> loadAllBecomes(Long[] paramArrayOfLong);

  public abstract Becomes loadBecomes(Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.becomesmanager.BecomesDao
 * JD-Core Version:    0.6.2
 */