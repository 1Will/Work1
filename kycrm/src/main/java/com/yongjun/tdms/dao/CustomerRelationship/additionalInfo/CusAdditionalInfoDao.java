package com.yongjun.tdms.dao.CustomerRelationship.additionalInfo;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.CusAdditionalInfo;
import java.util.List;

public abstract interface CusAdditionalInfoDao
{
  public abstract void storeCusAdditionalInfo(CusAdditionalInfo paramCusAdditionalInfo);

  public abstract CusAdditionalInfo loadCusAdditionalInfo(Long paramLong);

  public abstract List<CusAdditionalInfo> loadCusAdditionalInfo();

  public abstract List<CusAdditionalInfo> loadAllCusAdditionalInfo(Long[] paramArrayOfLong);

  public abstract void deleteCusAdditionalInfo(CusAdditionalInfo paramCusAdditionalInfo);

  public abstract void deleteAllCusAdditionalInfo(List<CusAdditionalInfo> paramList);

  public abstract List<CusAdditionalInfo> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CusAdditionalInfo> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.additionalInfo.CusAdditionalInfoDao
 * JD-Core Version:    0.6.2
 */