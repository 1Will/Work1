package com.yongjun.tdms.service.base.event;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.event.EventNew;

@Transactional(readOnly=true)
public abstract interface EventNewManager
{
  @Transactional
  public abstract void storeEventNew(EventNew paramEventNew);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.event.EventNewManager
 * JD-Core Version:    0.6.2
 */