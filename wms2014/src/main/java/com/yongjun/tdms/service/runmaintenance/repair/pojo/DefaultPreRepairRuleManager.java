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
package com.yongjun.tdms.service.runmaintenance.repair.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairRuleDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairRuleManager;

/**
 * <p>Title: DefaultPreRepairRuleManager
 * <p>Description: 预防性维修标准业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: DefaultPreRepairRuleManager.java 9888 2007-12-28 03:17:44Z qsun $
 * @see com.yongjun.tdms.service.runmaintenance.repair.PreRepairRuleManager
 */
public class DefaultPreRepairRuleManager implements PreRepairRuleManager {
	private final PreRepairRuleDao preRepairRuleDao;
	
	public DefaultPreRepairRuleManager(PreRepairRuleDao preRepairRuleDao) {
		this.preRepairRuleDao = preRepairRuleDao;
	}
	
	public PreRepairRule loadPreRepairRule(Long preRepairRuleId) {
		return this.preRepairRuleDao.loadPreRepairRule(preRepairRuleId);
	}

	public List<PreRepairRule> loadAllPreRepairRules(Long[] preRepairRuleIds) {
		return this.preRepairRuleDao.loadAllPreRepairRules(preRepairRuleIds);
	}

	public List<PreRepairRule> loadAllPreRepairRules() {
		return this.preRepairRuleDao.loadAllPreRepairRules();
	}

	public void storePreRepairRule(PreRepairRule preRepairRule) {
		this.preRepairRuleDao.storePreRepairRule(preRepairRule);
	}

	public void deletePreRepairRule(PreRepairRule preRepairRule) {
		this.preRepairRuleDao.deletePreRepairRule(preRepairRule);
	}

	public void deleteAllPreRepairRules(Collection<PreRepairRule> preRepairRules) {
		this.preRepairRuleDao.deleteAllPreRepairRules(preRepairRules);
	}

}
