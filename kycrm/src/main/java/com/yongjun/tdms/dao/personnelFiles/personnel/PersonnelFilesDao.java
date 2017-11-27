package com.yongjun.tdms.dao.personnelFiles.personnel;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import java.util.Collection;
import java.util.List;

public abstract interface PersonnelFilesDao
{
  public abstract void storePersonnel(PersonnelFiles paramPersonnelFiles);

  public abstract void deletePersonnel(PersonnelFiles paramPersonnelFiles);

  public abstract void deleteAllPersonnel(Collection<PersonnelFiles> paramCollection);

  public abstract List<PersonnelFiles> loadAllPersonnel(Long[] paramArrayOfLong);

  public abstract PersonnelFiles loadPersonnel(Long paramLong);

  public abstract List<PersonnelFiles> loadAllPersonnel();

  public abstract List<PersonnelFiles> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<PersonnelFiles> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString, Long paramLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.personnelFiles.personnel.PersonnelFilesDao
 * JD-Core Version:    0.6.2
 */