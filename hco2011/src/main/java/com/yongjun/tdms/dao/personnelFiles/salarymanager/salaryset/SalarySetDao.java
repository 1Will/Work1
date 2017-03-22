package com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryset;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
import java.util.List;

public abstract interface SalarySetDao
{
  public abstract void storeSalarySet(SalarySet paramSalarySet);

  public abstract SalarySet loadSalarySet(Long paramLong);

  public abstract List<SalarySet> loadAllSalarySets();

  public abstract List<SalarySet> loadAllSalarySet(Long[] paramArrayOfLong);

  public abstract void deleteSalarySet(SalarySet paramSalarySet);

  public abstract void deleteAllSalarySet(List<SalarySet> paramList);

  public abstract List<SalarySet> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<SalarySet> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.salarymanager.salaryset.SalarySetDao
 * JD-Core Version:    0.6.2
 */