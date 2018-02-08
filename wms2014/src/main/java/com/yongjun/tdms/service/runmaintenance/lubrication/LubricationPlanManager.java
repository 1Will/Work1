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
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanView;

/**
 * <p>Title: LubricationPlanManager
 * <p>Description: 润滑计划业务接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: LubricationPlanManager.java 11075 2008-02-25 07:25:55Z zbzhang $
 */
@Transactional(readOnly=true)
public interface LubricationPlanManager {
	@Transactional
	/**
	 * 存储润滑计划实体
	 */
	void storeLubricationPlan(LubricationPlan lubricationPlan);
	
	@Transactional
	/**
	 * 存储润滑计划实体
	 */
	void storeLubricationPlan(String lubricationRuleInfo);
	
	@Transactional
	/**
	 * 存储润滑计划实体
	 */
	void storeLubricationPuleModify(String lubricationInfo)throws Exception;

	/**
	 * 根据Id数组的集合加载所有的润滑计划实体
	 */
	List<LubricationPlan> loadAllLubricationPlan(Long[] lubricationPlanIds);
	
	/**
	 * 根据Id数组的集合加载所有的润滑计划实体
	 */
	List<LubricationPlan> loadAllMatchOptionLubricationPlan(Map map);

	/**
	 * 加载所有的润滑计划实体
	 */
	LubricationPlan loadLubricationPlan(Long id);

	/**
	 * 删除所有的润滑计划实体集合
	 */
	@Transactional
	void deleteAllLubricationPlan(List<LubricationPlan> list) throws Exception;

	/**
	 * 每月生成下月润滑计划
	 */
	@Transactional
	void createPlanByScheduler();
	public List<LubricationPlanView> loadAllLubricationPlanView(String[] array) throws HibernateException;
}
