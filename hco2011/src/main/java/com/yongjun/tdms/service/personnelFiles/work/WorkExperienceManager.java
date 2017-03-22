package com.yongjun.tdms.service.personnelFiles.work;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.WorkExperience;
import java.util.Collection;
import java.util.List;

public abstract interface WorkExperienceManager
{
  public abstract void storeWorkExperience(WorkExperience paramWorkExperience);

  public abstract void deleteWorkExperience(WorkExperience paramWorkExperience);

  public abstract void deleteAllWorkExperience(Collection<WorkExperience> paramCollection);

  public abstract List<WorkExperience> loadAllWorkExperience(Long[] paramArrayOfLong);

  public abstract WorkExperience loadWorkExperience(Long paramLong);

  public abstract List<WorkExperience> loadAllWorkExperience();

  public abstract List<WorkExperience> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<WorkExperience> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract void disabledWorkExperiences(Collection<WorkExperience> paramCollection);

  public abstract void enabledWorkExperiences(Collection<WorkExperience> paramCollection);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.work.WorkExperienceManager
 * JD-Core Version:    0.6.2
 */