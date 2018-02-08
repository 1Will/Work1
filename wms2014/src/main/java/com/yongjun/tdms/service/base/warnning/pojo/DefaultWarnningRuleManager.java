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
package com.yongjun.tdms.service.base.warnning.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.base.warnning.WarnningRuleDao;
import com.yongjun.pluto.model.base.warnning.WarnningRule;
import com.yongjun.tdms.service.base.warnning.WarnningRuleManager;

/**
 * <p>Title: DefaultWarnningRuleManager
 * <p>Description: 提醒规则业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.workspace.warnning.WarnningRuleManager
 * @version $Id:$
 */
public class DefaultWarnningRuleManager implements WarnningRuleManager {

	private final WarnningRuleDao warnningRuleDao;
	
	public DefaultWarnningRuleManager(WarnningRuleDao warnningRuleDao) {
		this.warnningRuleDao = warnningRuleDao;
	}
	
	public WarnningRule loadWarnningRule(Long warnningRuleId) {
		return this.warnningRuleDao.loadWarnningRule(warnningRuleId);
	}

	public List<WarnningRule> loadAllWarnningRules(Long[] warnningRuleIds) {
		return this.warnningRuleDao.loadAllWarnningRules(warnningRuleIds);
	}

	public List<WarnningRule> loadAllWarnningRules() {
		return this.warnningRuleDao.loadAllWarnningRules();
	}

	public void storeWarnningRule(WarnningRule warnningRule) {
		this.warnningRuleDao.storeWarnningRule(warnningRule);
	}

	public void deleteWarnningRule(WarnningRule warnningRule) {
		this.warnningRuleDao.deleteWarnningRule(warnningRule);
	}

	public void deleteAllWarnningRules(Collection<WarnningRule> warnningRules) {
		this.warnningRuleDao.deleteAllWarnningRules(warnningRules);
	}

}
