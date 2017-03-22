package com.yongjun.tdms.dao.base.log;

import com.yongjun.tdms.model.base.log.EventLog;
import java.util.List;

public abstract interface EventLogDao
{
  public abstract EventLog loadUserLog(Long paramLong);

  public abstract List<EventLog> loadAllUserLog(Long[] paramArrayOfLong);

  public abstract List<EventLog> loadAllUserLogs();
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.log.EventLogDao
 * JD-Core Version:    0.6.2
 */