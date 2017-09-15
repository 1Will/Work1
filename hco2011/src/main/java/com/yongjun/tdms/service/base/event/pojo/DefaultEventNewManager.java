package com.yongjun.tdms.service.base.event.pojo;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.event.EventNewDao;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.service.base.event.EventNewManager;

public class DefaultEventNewManager extends BaseManager implements EventNewManager {
	private EventNewDao eventNewDao;

	public void storeEventNew(EventNew eventNew) {
		this.eventNewDao.storeEventNew(eventNew);
	}

	public void setEventNewDao(EventNewDao eventNewDao) {
		this.eventNewDao = eventNewDao;
	}

	public DefaultEventNewManager(EventNewDao eventNewDao) {
		this.eventNewDao = eventNewDao;
	}
	
}
