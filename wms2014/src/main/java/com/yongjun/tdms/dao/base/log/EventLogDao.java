/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.dao.base.log;

import java.util.List;

import com.yongjun.tdms.model.base.log.EventLog;

/**
 * <p>Title: EventLogDao
 * <p>Description: 后台日志数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface EventLogDao {

	/**
	 * 根据传入的日志ID,获取日志
	 */
	EventLog loadUserLog(Long userLogId);
	
	/**
	 * 根据传入的日志ID集合,获取集合日志
	 * @param userLogIds ID集合
	 * @return List 集合
	 */
	List<EventLog> loadAllUserLog(Long [] userLogIds);
	
	/**
	 * 获取集合日志
	 * @return List 集合日志
	 */
	List<EventLog> loadAllUserLogs();
}
