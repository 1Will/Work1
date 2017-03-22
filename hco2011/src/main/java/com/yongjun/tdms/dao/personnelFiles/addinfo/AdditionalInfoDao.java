package com.yongjun.tdms.dao.personnelFiles.addinfo;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.AdditionalInfo;
import java.util.Collection;
import java.util.List;

public abstract interface AdditionalInfoDao
{
  public abstract void storeAdditional(AdditionalInfo paramAdditionalInfo);

  public abstract void deleteAdditional(AdditionalInfo paramAdditionalInfo);

  public abstract void deleteAllAdditional(Collection<AdditionalInfo> paramCollection);

  public abstract List<AdditionalInfo> loadAllAdditional(Long[] paramArrayOfLong);

  public abstract AdditionalInfo loadAdditional(Long paramLong);

  public abstract List<AdditionalInfo> loadAllAdditional();

  public abstract List<AdditionalInfo> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<AdditionalInfo> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.addinfo.AdditionalInfoDao
 * JD-Core Version:    0.6.2
 */