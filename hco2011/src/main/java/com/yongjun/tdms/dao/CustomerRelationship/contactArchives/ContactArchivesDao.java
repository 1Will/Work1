package com.yongjun.tdms.dao.CustomerRelationship.contactArchives;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import java.util.List;

public abstract interface ContactArchivesDao
{
  public abstract void storeContactArchives(ContactArchives paramContactArchives);

  public abstract ContactArchives loadContactArchives(Long paramLong);

  public abstract List<ContactArchives> loadAllContactArchives();

  public abstract List<ContactArchives> loadAllContactArchives(Long[] paramArrayOfLong);

  public abstract void deleteContactArchives(ContactArchives paramContactArchives);

  public abstract void deleteAllContactArchives(List<ContactArchives> paramList);

  public abstract List<ContactArchives> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ContactArchives> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao
 * JD-Core Version:    0.6.2
 */