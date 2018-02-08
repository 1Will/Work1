package com.yongjun.tdms.service.base.duty;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;

import java.util.Collection;
import java.util.List;

public abstract interface DutyManager
{
  public abstract void storeDuty(Duty paramDuty);

  public abstract void deleteDuty(Duty paramDuty);

  public abstract void deleteAllDuty(Collection<Duty> paramCollection);

  public abstract List<Duty> loadAllDuty(Long[] paramArrayOfLong);

  public abstract Duty loadDuty(Long paramLong);

  public abstract List<Duty> loadAllDuty();

  public abstract List<Duty> loadByKey(String paramString, Object paramObject)
    throws DaoException;

  public abstract void disabledAllDuties(Collection<Duty> paramCollection);

  public abstract void enabledAllChecks(Collection<Duty> paramCollection);

  public abstract List loadDutiesByDept(String paramString1, Long paramLong, String paramString2)
    throws DaoException;
  
  public abstract List<Duty> loadByDutyId (String name1,Long code2);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.duty.DutyManager
 * JD-Core Version:    0.6.2
 */