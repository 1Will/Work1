package com.yongjun.tdms.dao.marketmanager.targetmanager.companytarget;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.marketmanager.targetmanager.companytarget.CompanyTarget;
import java.util.List;

public abstract interface CompanyTargetDao
{
  public abstract void storeCompanyTarget(CompanyTarget paramCompanyTarget);

  public abstract CompanyTarget loadCompanyTarget(Long paramLong);

  public abstract List<CompanyTarget> loadCompanyTarget();

  public abstract List<CompanyTarget> loadAllCompanyTarget(Long[] paramArrayOfLong);

  public abstract void deleteCompanyTarget(CompanyTarget paramCompanyTarget);

  public abstract void deleteAllCompanyTarget(List<CompanyTarget> paramList);

  public abstract List<CompanyTarget> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CompanyTarget> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.marketmanager.targetmanager.companytarget.CompanyTargetDao
 * JD-Core Version:    0.6.2
 */