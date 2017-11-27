package com.yongjun.tdms.service.personnelFiles.edu;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.Education;
import java.util.Collection;
import java.util.List;

public abstract interface EducationManager
{
  public abstract void storeEducation(Education paramEducation);

  public abstract void deleteEducation(Education paramEducation);

  public abstract void deleteAllEducation(Collection<Education> paramCollection);

  public abstract List<Education> loadAllEducation(Long[] paramArrayOfLong);

  public abstract Education loadEducation(Long paramLong);

  public abstract List<Education> loadAllEducation();

  public abstract List<Education> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<Education> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledEducations(Collection<Education> paramCollection);

  public abstract void enabledEducations(Collection<Education> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.edu.EducationManager
 * JD-Core Version:    0.6.2
 */