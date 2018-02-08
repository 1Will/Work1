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
package com.yongjun.tdms.service.runmaintenance.maintenance.pojo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceRuleDao;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceRuleManager;


/**
 * @author wzou
 * @version $Id: CheckPointRuleManager.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class DefaultMaintenanceRuleManager extends BaseManager implements MaintenanceRuleManager{
	private final MaintenanceRuleDao maintenanceRuleDao;
	
	public DefaultMaintenanceRuleManager(MaintenanceRuleDao maintenanceRuleDao) {
		this.maintenanceRuleDao = maintenanceRuleDao;
	}
	
	public void storeMaintenanceRule(MaintenanceRule maintenanceRule) {
		this.maintenanceRuleDao.storeMaintenanceRule(maintenanceRule);
	}

	public List<MaintenanceRule> loadAllmaintenanceRule(Long[] maintenanceRuleIds) {
		return maintenanceRuleDao.loadAllmaintenanceRule(maintenanceRuleIds);
	}

	public void deleteAllMaintenanceRule(List<MaintenanceRule> list) {
		this.maintenanceRuleDao.deleteAllMaintenanceRule(list);
	}

	public MaintenanceRule loadMaintenanceRule(Long id) {
		return this.maintenanceRuleDao.loadMaintenanceRule(id);
	}
	
}
