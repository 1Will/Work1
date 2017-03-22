package com.yongjun.tdms.service.marketmanager.activity;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.activity.Activity;
import java.util.Collection;
import java.util.List;

public abstract interface ActivityManager
{
  public abstract void storeActivity(Activity paramActivity);

  public abstract void deleteActivity(Activity paramActivity);

  public abstract void deleteAllActivity(Collection<Activity> paramCollection);

  public abstract List<Activity> loadAllActivity(Long[] paramArrayOfLong);

  public abstract Activity loadActivity(Long paramLong);

  public abstract List<Activity> loadAllActivity();

  public abstract List<Activity> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Activity> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllActivity(Collection<Activity> paramCollection);

  public abstract void enabledAllActivity(Collection<Activity> paramCollection);

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.marketmanager.activity.ActivityManager
 * JD-Core Version:    0.6.2
 */