/*    */ package com.yongjun.tdms.dao.base.event.hibernate;
/*    */ 
/*    */ import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.base.event.EventTypeDao;
import com.yongjun.tdms.model.base.event.EventType;
/*    */ 
/*    */ public class HibernateEventType extends BaseHibernateDao
/*    */   implements EventTypeDao
/*    */ {

	public List<EventType> loadByKey(String keyName, Object codeValue) throws DaoException {
		// TODO Auto-generated method stub
		return this.loadByKey(EventType.class, keyName, codeValue);
		}
/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.dao.base.event.hibernate.HibernateEventNew
 * JD-Core Version:    0.6.2
 */