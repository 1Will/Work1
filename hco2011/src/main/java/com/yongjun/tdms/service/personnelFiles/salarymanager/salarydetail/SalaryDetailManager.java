package com.yongjun.tdms.service.personnelFiles.salarymanager.salarydetail;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salarydetail.SalaryDetail;
import java.util.List;

public abstract interface SalaryDetailManager
{
  public abstract void storeSalaryDetail(SalaryDetail paramSalaryDetail);

  public abstract SalaryDetail loadSalaryDetail(Long paramLong);

  public abstract List<SalaryDetail> loadSalaryDetail();

  public abstract List<SalaryDetail> loadAllSalaryDetail(Long[] paramArrayOfLong);

  public abstract void deleteSalaryDetail(SalaryDetail paramSalaryDetail);

  public abstract void deleteAllSalaryDetail(List<SalaryDetail> paramList);

  public abstract List<SalaryDetail> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<SalaryDetail> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllSalaryDetail(List<SalaryDetail> paramList);

  public abstract void enabledAllSalaryDetail(List<SalaryDetail> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.salarymanager.salarydetail.SalaryDetailManager
 * JD-Core Version:    0.6.2
 */