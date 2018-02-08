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
package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairRuleDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;

/**
 * <p>Title: HibernatePreRepairRule
 * <p>Description: 预防性维修标准数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: HibernatePreRepairRule.java 9692 2007-12-25 01:49:03Z zbzhang $
 * @see com.yongjun.tdms.dao.runmaintenance.repair.PreRepairRuleDao
 */
public class HibernatePreRepairRule extends BaseHibernateDao implements PreRepairRuleDao{

	public PreRepairRule loadPreRepairRule(Long preRepairRuleId) {
		return this.load(PreRepairRule.class,preRepairRuleId);
	}

	public List<PreRepairRule> loadAllPreRepairRules(Long[] preRepairRuleIds) {
		return this.loadAll(PreRepairRule.class, preRepairRuleIds);
	}

	public List<PreRepairRule> loadAllPreRepairRules() {
		return this.loadAll(PreRepairRule.class);
	}

	public void storePreRepairRule(PreRepairRule preRepairRule) {
		this.store(preRepairRule);
	}

	public void deletePreRepairRule(PreRepairRule preRepairRule) {
		this.delete(preRepairRule);
	}

	public void deleteAllPreRepairRules(Collection<PreRepairRule> preRepairRules) {
		this.deleteAll(preRepairRules);
	}

}
