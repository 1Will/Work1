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
package com.yongjun.tdms.service.base.warnning;
import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.base.warnning.WarnningRule;

/**
 * <p>Title: WarnningRuleManager
 * <p>Description: 提醒规则业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface WarnningRuleManager {
	/**
	 * 根据传入的提醒规则ID,获取提醒规则
	 * @param budgetId 提醒规则ID
	 * @return Budget 提醒规则实体
	 */
	WarnningRule loadWarnningRule(Long warnningRuleId);
	
	/**
	 * 根据传入的提醒规则ID集合,获取集合提醒规则
	 * @param WarnningRuleIds 提醒规则ID集合
	 * @return List 集合提醒规则
	 */
	List<WarnningRule> loadAllWarnningRules(Long [] warnningRuleIds);
	
	/**
	 * 获取集合提醒规则
	 * @return List 集合提醒规则
	 */
	List<WarnningRule> loadAllWarnningRules();
	
	/**
	 * 保存提醒规则
	 * @param WarnningRule 提醒规则
	 */
	@Transactional
	void storeWarnningRule(WarnningRule warnningRule);
	
	/**
	 * 删除提醒规则
	 * @param WarnningRule 提醒规则
	 */
	@Transactional
	void deleteWarnningRule(WarnningRule warnningRule);
	
	/**
	 * 根据传入的提醒规则集合,删除集合提醒规则
	 * @param WarnningRules 提醒规则集合
	 */
	@Transactional
	void deleteAllWarnningRules(Collection<WarnningRule> warnningRules);
}
