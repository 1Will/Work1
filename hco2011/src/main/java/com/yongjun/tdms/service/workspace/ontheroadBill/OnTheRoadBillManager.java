package com.yongjun.tdms.service.workspace.ontheroadBill;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
import java.util.Collection;
import java.util.List;

public abstract interface OnTheRoadBillManager
{
  public abstract void storeOnTheRoadBill(OnTheRoadBill paramOnTheRoadBill);

  public abstract void deleteOnTheRoadBill(OnTheRoadBill paramOnTheRoadBill);

  public abstract void deleteAllOnTheRoadBill(Collection<OnTheRoadBill> paramCollection);

  public abstract List<OnTheRoadBill> loadAllOnTheRoadBill(Long[] paramArrayOfLong);

  public abstract OnTheRoadBill loadOnTheRoadBill(Long paramLong);

  public abstract List<OnTheRoadBill> loadAllOnTheRoadBill();

  public abstract List<OnTheRoadBill> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<OnTheRoadBill> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllOnTheRoadBills(List<OnTheRoadBill> paramList);

  public abstract void enabledAllOnTheRoadBills(List<OnTheRoadBill> paramList);

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.workspace.ontheroadBill.OnTheRoadBillManager
 * JD-Core Version:    0.6.2
 */