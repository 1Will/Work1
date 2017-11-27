package com.yongjun.tdms.dao.base.document;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.document.ApplicationDoc;

import java.util.Collection;
import java.util.List;

public abstract interface ApplicationDocDao
{
  public abstract ApplicationDoc loadApplicationDoc(Long paramLong);

  public abstract List<ApplicationDoc> loadAllApplicationDocs(Long[] paramArrayOfLong);

  public abstract List<ApplicationDoc> loadAllApplicationDocs();

  public abstract void storeApplicationDoc(ApplicationDoc paramApplicationDoc);

  public abstract void deleteApplicationDoc(ApplicationDoc paramApplicationDoc);

  public abstract void deleteAllApplicationDocs(Collection<ApplicationDoc> paramCollection);

  public abstract Integer getNumberOfManualDoc();

  public abstract List<ApplicationDoc> getAllManualDoc();
  
  public abstract List<ApplicationDoc> loadByKey(String keyName, Object keyValue) throws DaoException;
  
  public abstract List<ApplicationDoc> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.document.ApplicationDocDao
 * JD-Core Version:    0.6.2
 */