package com.yongjun.tdms.service.workspace.overTimeBill;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
import java.util.Collection;
import java.util.List;

public abstract interface OverTimeBillManager
{
  public abstract void storeOverTimeBill(OverTimeBill paramOverTimeBill);

  public abstract void deleteOverTimeBill(OverTimeBill paramOverTimeBill);

  public abstract void deleteAllOverTimeBill(Collection<OverTimeBill> paramCollection);

  public abstract List<OverTimeBill> loadAllOverTimeBill(Long[] paramArrayOfLong);

  public abstract OverTimeBill loadOverTimeBill(Long paramLong);

  public abstract List<OverTimeBill> loadAllOverTimeBill();

  public abstract List<OverTimeBill> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<OverTimeBill> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllOverTimeBills(List<OverTimeBill> paramList);

  public abstract void enabledAllOverTimeBills(List<OverTimeBill> paramList);

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager
 * JD-Core Version:    0.6.2
 */