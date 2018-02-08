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
package com.yongjun.tdms.dao.base.log.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.log.EventLogDao;
import com.yongjun.tdms.model.base.log.EventLog;

/**
 * <p>Title: HibernateEventLog
 * <p>Description: 后台日志数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zsmuig@yj-technology.com
 * @version $Id:$
 */
public class HibernateEventLog extends BaseHibernateDao implements EventLogDao {

	public EventLog loadUserLog(Long userLogId) {
		return this.load(EventLog.class, userLogId);
	}

	public List<EventLog> loadAllUserLog(Long[] userLogIds) {
		return this.loadAll(EventLog.class, userLogIds);
	}

	public List<EventLog> loadAllUserLogs() {
		return this.loadAll(EventLog.class);
	}
}
