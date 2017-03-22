package com.yongjun.tdms.service.base.log;

import com.yongjun.tdms.model.base.log.EventLog;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract interface UserLogManager
{
  public abstract EventLog loadUserLog(Long paramLong);

  public abstract List<EventLog> loadAllUserLog(Long[] paramArrayOfLong);

  public abstract List<EventLog> loadAllUserLogs();
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.log.UserLogManager
 * JD-Core Version:    0.6.2
 */