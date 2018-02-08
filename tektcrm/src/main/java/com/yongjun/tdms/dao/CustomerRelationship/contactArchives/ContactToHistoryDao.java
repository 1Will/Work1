package com.yongjun.tdms.dao.CustomerRelationship.contactArchives;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactToHistory;

import java.util.Collection;
import java.util.List;

public abstract interface ContactToHistoryDao
{
  public abstract void storeContactToHistory(ContactToHistory paramContactToHistory);

  public abstract void deleteContactToHistory(ContactToHistory paramContactToHistory);

  public abstract void deleteAllContactToHistory(Collection<ContactToHistory> paramCollection);

  public abstract List<ContactToHistory> loadAllContactToHistory(Long[] paramArrayOfLong);

  public abstract ContactToHistory loadContactToHistory(Long paramLong);

  public abstract List<ContactToHistory> loadAllContactToHistory();

  public abstract List<ContactToHistory> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ContactToHistory> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.work.ContactToHistoryDao
 * JD-Core Version:    0.6.2
 */