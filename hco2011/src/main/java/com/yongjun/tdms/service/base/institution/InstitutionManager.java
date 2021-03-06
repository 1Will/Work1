package com.yongjun.tdms.service.base.institution;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import java.util.Collection;
import java.util.List;

public abstract interface InstitutionManager
{
  public abstract void storeInstitution(Institution paramInstitution);

  public abstract void deleteInstitution(Institution paramInstitution);

  public abstract void deleteAllInstitution(Collection<Institution> paramCollection);

  public abstract List<Institution> loadAllInstitution(Long[] paramArrayOfLong);

  public abstract Institution loadInstitution(Long paramLong);

  public abstract List<Institution> loadAllInstitution();

  public abstract List<Institution> loadAllInstitution(String paramString);

  public abstract void disabledAllInstitution(Collection<Institution> paramCollection);

  public abstract void enabledAllInstitution(Collection<Institution> paramCollection);

  public abstract List<Institution> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract int getInstMaxId();

  public abstract List<Institution> getInstitutions();

  public abstract int getInstSteps();

  public abstract List<Institution> getInstsByStep(int paramInt);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.institution.InstitutionManager
 * JD-Core Version:    0.6.2
 */