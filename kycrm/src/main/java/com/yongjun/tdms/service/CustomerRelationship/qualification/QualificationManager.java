package com.yongjun.tdms.service.CustomerRelationship.qualification;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.CustomerRelationship.qualification.CustomerRelationshipQualification;

import java.util.List;

public abstract interface QualificationManager
{
  public abstract void storeQualification(CustomerRelationshipQualification paramQualification);

  public abstract CustomerRelationshipQualification loadQualification(Long paramLong);

  public abstract List<CustomerRelationshipQualification> loadAllQualification();

  public abstract List<CustomerRelationshipQualification> loadAllQualification(Long[] paramArrayOfLong);

  public abstract void deleteQualification(CustomerRelationshipQualification paramQualification);

  public abstract void deleteAllQualification(List<CustomerRelationshipQualification> paramList);

  public abstract List<CustomerRelationshipQualification> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllQualification(List<CustomerRelationshipQualification> paramList);

  public abstract void enabledAllQualification(List<CustomerRelationshipQualification> paramList);

  public abstract List<CustomerRelationshipQualification> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
  
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager
 * JD-Core Version:    0.6.2
 */