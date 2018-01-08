package com.yongjun.tdms.service.workspace.leaveBill;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
import java.util.Collection;
import java.util.List;

public abstract interface LeaveBillManager
{
  public abstract void storeLeaveBill(LeaveBill paramLeaveBill)throws DaoException;

  public abstract void deleteLeaveBill(LeaveBill paramLeaveBill);

  public abstract void deleteAllLeaveBill(Collection<LeaveBill> paramCollection);

  public abstract List<LeaveBill> loadAllLeaveBill(Long[] paramArrayOfLong);

  public abstract LeaveBill loadLeaveBill(Long paramLong);

  public abstract List<LeaveBill> loadAllLeaveBill();

  public abstract List<LeaveBill> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<LeaveBill> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllLeaveBills(List<LeaveBill> paramList);

  public abstract void enabledAllLeaveBills(List<LeaveBill> paramList);

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager
 * JD-Core Version:    0.6.2
 */