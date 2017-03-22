package com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
import java.util.List;

public abstract interface SalaryStandardManager
{
  public abstract void storeSalaryStandard(SalaryStandard paramSalaryStandard);

  public abstract SalaryStandard loadSalaryStandard(Long paramLong);

  public abstract List<SalaryStandard> loadSalaryStandard();

  public abstract List<SalaryStandard> loadAllSalaryStandard(Long[] paramArrayOfLong);

  public abstract void deleteSalaryStandard(SalaryStandard paramSalaryStandard);

  public abstract void deleteAllSalaryStandard(List<SalaryStandard> paramList);

  public abstract List<SalaryStandard> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<SalaryStandard> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllSalaryStandard(List<SalaryStandard> paramList);

  public abstract void enabledAllSalaryStandard(List<SalaryStandard> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.SalaryStandardManager
 * JD-Core Version:    0.6.2
 */