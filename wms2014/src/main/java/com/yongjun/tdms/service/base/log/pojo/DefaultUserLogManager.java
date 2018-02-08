package com.yongjun.tdms.service.base.log.pojo;

import java.util.List;

import com.yongjun.tdms.dao.base.log.EventLogDao;
import com.yongjun.tdms.model.base.log.EventLog;
import com.yongjun.tdms.service.base.log.UserLogManager;


/**
 * <p>Title: DefaultWarnningRuleManager
 * <p>Description: 用户日志查询业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zsmuig@yj-technology.com
 * @version $Id:$
 */
public class DefaultUserLogManager implements UserLogManager {

	private final EventLogDao eventLogDao;
	
	public DefaultUserLogManager(EventLogDao eventLogDao) {
		this.eventLogDao = eventLogDao;
	}
	
	public EventLog loadUserLog(Long userLogId) {
		return this.eventLogDao.loadUserLog(userLogId);
	}

	public List<EventLog> loadAllUserLog(Long[] userLogIds) {
		return this.eventLogDao.loadAllUserLog(userLogIds);
	}

	public List<EventLog> loadAllUserLogs() {
		return this.eventLogDao.loadAllUserLogs();
	}

}
