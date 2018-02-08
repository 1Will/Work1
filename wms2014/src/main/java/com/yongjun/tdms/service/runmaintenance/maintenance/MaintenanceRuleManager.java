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
package com.yongjun.tdms.service.runmaintenance.maintenance;

/**
 * @author wzou
 * @version $Id: LubricationRuleManager.java 9745 2007-12-26 05:20:17Z mwei $
 */
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;

@Transactional(readOnly = true)
public interface MaintenanceRuleManager {
	@Transactional
	void storeMaintenanceRule(MaintenanceRule maintenanceRule);
	
	List<MaintenanceRule> loadAllmaintenanceRule(Long[] maintenanceRuleIds);
	
	MaintenanceRule loadMaintenanceRule(Long id);
	@Transactional
	void deleteAllMaintenanceRule(List<MaintenanceRule> list);
}
