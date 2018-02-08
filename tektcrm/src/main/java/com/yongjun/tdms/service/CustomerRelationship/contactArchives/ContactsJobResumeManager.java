package com.yongjun.tdms.service.CustomerRelationship.contactArchives;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactsJobResume;

import java.util.Collection;
import java.util.List;

public abstract interface ContactsJobResumeManager
{
  public abstract void storeContactsJobResume(ContactsJobResume paramContactsJobResume);

  public abstract void deleteContactsJobResume(ContactsJobResume paramContactsJobResume);

  public abstract void deleteAllContactsJobResume(Collection<ContactsJobResume> paramCollection);

  public abstract List<ContactsJobResume> loadAllContactsJobResume(Long[] paramArrayOfLong);

  public abstract ContactsJobResume loadContactsJobResume(Long paramLong);

  public abstract List<ContactsJobResume> loadAllContactsJobResume();

  public abstract List<ContactsJobResume> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<ContactsJobResume> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledContactsJobResumes(Collection<ContactsJobResume> paramCollection);

  public abstract void enabledContactsJobResumes(Collection<ContactsJobResume> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.work.ContactsJobResumeManager
 * JD-Core Version:    0.6.2
 */