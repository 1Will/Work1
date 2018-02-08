package com.yongjun.tdms.service.workspace.warnning;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract interface WorkWarnningManager
{
  public abstract WorkWarnning loadWorkWarnning(Long paramLong);

  public abstract List<WorkWarnning> loadAllWorkWarnnings(Long[] paramArrayOfLong);

  public abstract List<WorkWarnning> loadAllWorkWarnnings();

  @Transactional
  public abstract void storeWorkWarnning(WorkWarnning paramWorkWarnning);

  @Transactional
  public abstract void deleteWorkWarnning(WorkWarnning paramWorkWarnning);

  @Transactional
  public abstract void deleteAllWorkWarnnings(Collection<WorkWarnning> paramCollection);

  public abstract Long GetNumberOfUnReadWarnningByUserID(Long paramLong);

  @Transactional
  public abstract void readAllWorkWarnnings(Collection<WorkWarnning> paramCollection);

  @Transactional
  public abstract void unReadAllWorkWarnnings(Collection<WorkWarnning> paramCollection);

  public abstract void sendWarnningMessage(List<User> paramList, String paramString1, String paramString2);

  public abstract List<WorkWarnning> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
    throws DaoException;

  public abstract List<WorkWarnning> loadByKey(String paramString, Object paramObject)
    throws DaoException;
  
  public List<WorkWarnning> loadWarByUser(Long id);
}