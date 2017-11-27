package com.yongjun.tdms.service.customerservicemanagement.qastorage;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customerservicemanagement.qastorage.QaStorage;
import java.util.List;

public abstract interface QaStorageManager
{
  public abstract void storeQaStorage(QaStorage paramQaStorage);

  public abstract QaStorage loadQaStorage(Long paramLong);

  public abstract List<QaStorage> loadQaStorage();

  public abstract List<QaStorage> loadAllQaStorage(Long[] paramArrayOfLong);

  public abstract void deleteQaStorage(QaStorage paramQaStorage);

  public abstract void deleteAllQaStorage(List<QaStorage> paramList);

  public abstract List<QaStorage> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<QaStorage> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledAllQaStorage(List<QaStorage> paramList);

  public abstract void enabledAllQaStorage(List<QaStorage> paramList);

  public abstract String getMaxPFCode(String paramString);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customerservicemanagement.qastorage.QaStorageManager
 * JD-Core Version:    0.6.2
 */