package com.yongjun.tdms.dao.base.event.hibernate;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.event.EventNewDao;
import com.yongjun.tdms.model.base.event.EventNew;

public class HibernateEventNew extends BaseHibernateDao implements EventNewDao {
	public void storeEventNew(EventNew eventNew) {
		store(eventNew);
	}
}
