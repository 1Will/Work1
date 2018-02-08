package com.yongjun.tdms.service.base.log;

import com.yongjun.tdms.model.base.log.SysLog;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract interface SysLogManager
{
  public abstract void write(SysLog paramSysLog);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.log.SysLogManager
 * JD-Core Version:    0.6.2
 */