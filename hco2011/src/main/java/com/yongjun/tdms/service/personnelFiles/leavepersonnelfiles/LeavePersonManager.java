package com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.personnelFiles.leavepersonnelfiles.LeavePerson;
import java.util.Collection;
import java.util.List;

public abstract interface LeavePersonManager
{
  public abstract void storePersonnel(LeavePerson paramLeavePerson);

  public abstract void deletePersonnel(LeavePerson paramLeavePerson);

  public abstract void deleteAllPersonnel(Collection<LeavePerson> paramCollection);

  public abstract List<LeavePerson> loadAllPersonnel(Long[] paramArrayOfLong);

  public abstract LeavePerson loadPersonnel(Long paramLong);

  public abstract List<LeavePerson> loadAllPersonnel();

  public abstract List<LeavePerson> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract List<LeavePerson> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.personnelFiles.leavepersonnelfiles.LeavePersonManager
 * JD-Core Version:    0.6.2
 */