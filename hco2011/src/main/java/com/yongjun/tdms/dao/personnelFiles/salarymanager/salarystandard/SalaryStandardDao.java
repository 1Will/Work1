package com.yongjun.tdms.dao.personnelFiles.salarymanager.salarystandard;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salarystandard.SalaryStandard;
import java.util.List;

public abstract interface SalaryStandardDao
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
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.salarymanager.salarystandard.SalaryStandardDao
 * JD-Core Version:    0.6.2
 */