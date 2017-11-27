package com.yongjun.tdms.dao.CustomerRelationship.qualification;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.qualification.CustomerRelationshipQualification;

import java.util.List;

public abstract interface QualificationDao
{
  public abstract void storeQualification(CustomerRelationshipQualification paramQualification);

  public abstract CustomerRelationshipQualification loadQualification(Long paramLong);

  public abstract List<CustomerRelationshipQualification> loadAllQualification();

  public abstract List<CustomerRelationshipQualification> loadAllQualification(Long[] paramArrayOfLong);

  public abstract void deleteQualification(CustomerRelationshipQualification paramQualification);

  public abstract void deleteAllQualification(List<CustomerRelationshipQualification> paramList);

  public abstract List<CustomerRelationshipQualification> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<CustomerRelationshipQualification> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao
 * JD-Core Version:    0.6.2
 */