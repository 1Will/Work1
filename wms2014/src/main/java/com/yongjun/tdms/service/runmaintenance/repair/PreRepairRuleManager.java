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
package com.yongjun.tdms.service.runmaintenance.repair;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;

/**
 * <p>Title: PreRepairRuleManager
 * <p>Description: 预防性维修标准业务访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PreRepairRuleManager.java 9888 2007-12-28 03:17:44Z qsun $
 */
@Transactional(readOnly=true)
public interface PreRepairRuleManager {
	/**
	 * 根据传入预防性维修标准ID,获取预防性维修标准
	 * @param preRepairRuleId 预防性维修标准ID
	 * @return PreRepairRule 预防性维修标准
	 */
	PreRepairRule loadPreRepairRule(Long preRepairRuleId);
	
	/**
	 * 根据传入预防性维修标准ID集合,获取集合预防性维修标准
	 * @param preRepairRuleIds 预防性维修标准ID集合
	 * @return List 预防性维修标准集合
	 */
	List<PreRepairRule> loadAllPreRepairRules(Long [] preRepairRuleIds);
	
	/**
	 * 获取集合预防性维修标准
	 * @return List 预防性维修标准集合
	 */
	List<PreRepairRule> loadAllPreRepairRules();
	
	/**
	 * 保存预防性维修标准
	 * @param preRepairRule 预防性维修标准实体
	 */
	@Transactional
	void storePreRepairRule(PreRepairRule preRepairRule);
	
	/**
	 * 删除预防性维修标准
	 * @param preRepairRule 预防性维修标准实体
	 */
	@Transactional
	void deletePreRepairRule(PreRepairRule preRepairRule);
	
	/**
	 * 根据传入预防性维修标准集合，删除集合预防性维修标准
	 * @param preRepairRules 预防性维修标准集合
	 */
	@Transactional
	void deleteAllPreRepairRules(Collection<PreRepairRule> preRepairRules);
}
