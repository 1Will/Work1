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
package com.yongjun.tdms.service.runmaintenance.lubrication;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;

/**
 * @author qs
 * @version $Id: LubricationRuleManager.java 9745 2007-12-26 05:20:17Z mwei $
 */
@Transactional(readOnly = true)
public interface LubricationRuleManager {
	@Transactional
	/**
	 * 存储润滑标准实体
	 */
	void storeLubricationRule(LubricationRule lubricationRule);
	
	@Transactional
	/**
	 * 存储一组润滑标准实体，并修改值
	 */
	void storeLubricationRule(String lubricationInfo);

	/**
	 * 根据Id数组的集合加载所有的润滑标准实体
	 */
	List<LubricationRule> loadAllLubricationRule(Long[] lubricationRuleIds);

	/**
	 * 加载所有的润滑标准实体
	 */
	LubricationRule loadLubricationRule(Long id);

	@Transactional
	/**
	 * 删除所有的工装变动实体集合
	 */
	void deleteAllLubricationRule(List<LubricationRule> list);

}
