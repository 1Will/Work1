package com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryitems.SalaryItems;
import java.util.Collection;
import java.util.List;

public abstract interface SalaryItemsManager
{
  public abstract void storeSalaryItems(SalaryItems paramSalaryItems);

  public abstract SalaryItems loadSalaryItems(Long paramLong);

  public abstract List<SalaryItems> loadAllSalaryItemss();

  public abstract List<SalaryItems> loadAllSalaryItems(Long[] paramArrayOfLong);

  public abstract void deleteSalaryItems(SalaryItems paramSalaryItems);

  public abstract void deleteAllSalaryItems(List<SalaryItems> paramList);

  public abstract List<SalaryItems> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<SalaryItems> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledSalaryItems(Collection<SalaryItems> paramCollection);

  public abstract void enabledSalaryItems(Collection<SalaryItems> paramCollection);

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.SalaryItemsManager
 * JD-Core Version:    0.6.2
 */