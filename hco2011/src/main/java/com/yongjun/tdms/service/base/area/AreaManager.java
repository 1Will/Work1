package com.yongjun.tdms.service.base.area;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.area.Area;
import java.util.Collection;
import java.util.List;

public abstract interface AreaManager
{
  public abstract void storeArea(Area paramArea);

  public abstract Area loadArea(Long paramLong);

  public abstract List<Area> loadAllArea(Long[] paramArrayOfLong);

  public abstract List<Area> loadAllAreas();

  public abstract void deleteArea(Area paramArea);

  public abstract void deleteAllArea(Collection<Area> paramCollection);

  public abstract List<Area> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Area> loadAreaKeyProperty(String paramString);

  public abstract Long getLatestLogo();

  public abstract List<Area> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.area.AreaManager
 * JD-Core Version:    0.6.2
 */