package com.yongjun.tdms.service.base.event;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.base.event.EventType;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public abstract interface EventTypeManager
{
	public List<EventType> loadByKey(String keyName,Object codeValue)throws DaoException;
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.event.EventNewManager
 * JD-Core Version:    0.6.2
 */