package com.yongjun.tdms.service.base.log;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.log.EventLog;

/**
 * <p>Title: WarnningRuleManager
 * <p>Description: 用户日志查询业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zsmuig@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface UserLogManager {
	/**
	 * 根据传入的日志ID,获取日志
	 */
	EventLog loadUserLog(Long userLogId);
	
	/**
	 * 根据传入的日志ID集合,获取集合日志
	 * @param userLogIds 提醒规则ID集合
	 * @return List 集合提醒规则
	 */
	List<EventLog> loadAllUserLog(Long [] userLogIds);
	
	/**
	 * 获取集合日志
	 * @return List 集合日志
	 */
	List<EventLog> loadAllUserLogs();

}
