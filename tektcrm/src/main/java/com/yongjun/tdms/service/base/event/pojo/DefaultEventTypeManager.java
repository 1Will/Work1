/*    */ package com.yongjun.tdms.service.base.event.pojo;
/*    */ 
/*    */ import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.event.EventNewDao;
import com.yongjun.tdms.dao.base.event.EventTypeDao;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.service.base.event.EventTypeManager;
/*    */ 
/*    */ public class DefaultEventTypeManager extends BaseManager
/*    */   implements EventTypeManager
/*    */ {
/*    */   private EventTypeDao eventTypeDao;
/*    */

	public List<EventType> loadByKey(String keyName, Object codeValue) throws DaoException {
	// TODO Auto-generated method stub
	return eventTypeDao.loadByKey(keyName, codeValue);
		}

	public EventTypeDao getEventTypeDao() {
		return eventTypeDao;
	}

	public void setEventTypeDao(EventTypeDao eventTypeDao) {
		this.eventTypeDao = eventTypeDao;
	} 
	public DefaultEventTypeManager(EventTypeDao eventTypeDao) {
		this.eventTypeDao = eventTypeDao;
}
	 

/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.event.pojo.DefaultEventNewManager
 * JD-Core Version:    0.6.2
 */