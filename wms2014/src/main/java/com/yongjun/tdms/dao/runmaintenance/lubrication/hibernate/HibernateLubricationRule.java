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
package com.yongjun.tdms.dao.runmaintenance.lubrication.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.lubrication.LubricationRuleDao;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;

/**
 * <p>Title: HibernateLubricationRule</p>
 * <p>Description: 润滑标准数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * 
 * @author wzou@yj-technology.com
 * @version $Id: HibernateLubricationRule.java 9952 2007-12-29 07:00:56Z qsun $
 */
public class HibernateLubricationRule extends BaseHibernateDao implements
		LubricationRuleDao {
	public void storeLubricationRule(LubricationRule lubricationRule) {
		this.store(lubricationRule);
	}

	public LubricationRule loadLubricationRule(Long id) {
		return this.load(LubricationRule.class, id);
	}

	public void deleteAllLubricationRule(List<LubricationRule> list) {
		this.deleteAll(list);
	}

	public List<LubricationRule> loadAllLubricationRule(
			Long[] lubricationRuleIds) {
		return this.loadAll(LubricationRule.class, lubricationRuleIds);
	}

	public List<LubricationRule> loadAllLubricationRule() {
		return this.loadAll(LubricationRule.class);
	}

}
