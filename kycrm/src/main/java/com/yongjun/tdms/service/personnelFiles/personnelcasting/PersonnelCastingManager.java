package com.yongjun.tdms.service.personnelFiles.personnelcasting;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.personnelcasting.PersonnelCasting;
import java.util.List;

public abstract interface PersonnelCastingManager
{
  public abstract void storePersonnelCasting(PersonnelCasting paramPersonnelCasting);

  public abstract PersonnelCasting loadPersonnelCasting(Long paramLong);

  public abstract List<PersonnelCasting> loadPersonnelCasting();

  public abstract List<PersonnelCasting> loadAllPersonnelCasting(Long[] paramArrayOfLong);

  public abstract void deletePersonnelCasting(PersonnelCasting paramPersonnelCasting);

  public abstract void deleteAllPersonnelCasting(List<PersonnelCasting> paramList);

  public abstract List<PersonnelCasting> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<PersonnelCasting> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract String getMaxPFCode(String paramString);

  public abstract void disabledAllPersonnelCasting(List<PersonnelCasting> paramList);

  public abstract void enabledAllPersonnelCasting(List<PersonnelCasting> paramList);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.personnelcasting.PersonnelCastingManager
 * JD-Core Version:    0.6.2
 */