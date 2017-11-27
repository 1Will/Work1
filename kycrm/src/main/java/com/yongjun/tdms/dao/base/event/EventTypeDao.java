package com.yongjun.tdms.dao.base.event;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.event.EventType;

public abstract interface EventTypeDao
{
	public List<EventType> loadByKey(String keyName,Object codeValue)throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.event.EventNewDao
 * JD-Core Version:    0.6.2
 */