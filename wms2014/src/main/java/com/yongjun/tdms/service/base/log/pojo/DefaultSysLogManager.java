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
package com.yongjun.tdms.service.base.log.pojo;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.log.SysLogDao;
import com.yongjun.tdms.model.base.log.SysLog;
import com.yongjun.tdms.service.base.log.SysLogManager;

/**
 * @author qs
 * @version $Id: DefaultSysLogManager.java 10247 2008-01-08 03:40:03Z qsun $
 */
public class DefaultSysLogManager extends BaseManager implements SysLogManager {
	private final SysLogDao sysLogDao;
	
	public DefaultSysLogManager(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}
	
	public void write(SysLog sysLog) {
		sysLogDao.storeLog(sysLog);
	}

}
