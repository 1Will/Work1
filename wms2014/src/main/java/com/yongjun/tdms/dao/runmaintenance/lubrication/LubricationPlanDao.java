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
package com.yongjun.tdms.dao.runmaintenance.lubrication;

import java.util.List;
import java.util.Map;

import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;

/**
 * <p>Title: LubricationPlanDao
 * <p>Description: 润滑计划数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: LubricationPlanDao.java 10005 2007-12-29 12:37:46Z mwei $
 */
public interface LubricationPlanDao {
	/**
	 * 根据传入的润滑计划对象,存储润滑计划对象
	 * 
	 * @param alteration 润滑计划对象
	 * @return 
	 */	
	void storeLubricationPlan(LubricationPlan lubricationPlan);
	
	/**
	 * 根据传入的润滑计划的Id,获取润滑计划对象
	 * 
	 * @param id 润滑计划Id号
	 * @return  润滑计划对象
	 */
	LubricationPlan loadLubricationPlan(Long id);
	
	/**
	 * 根据传入的润滑计划的Id,获取润滑计划对象
	 * 
	 * @param id 润滑计划Id号
	 * @return  润滑计划对象
	 */
	List<LubricationPlan> loadAllMatchOptionLubricationPlans(Map searchOption);
	
	/**
	 * 根据传入的润滑计划,获取润滑计划对象
	 * 
	 * @param id 润滑计划Id号
	 * @return  润滑计划对象
	 */
	List<LubricationPlan> loadAllLubricationPlansByDevice(LubricationPlan lubricationPlan);
	
	/**
	 * 根据传入的润滑计划对象的集合,删除润滑计划对象集合
	 * 
	 * @param list 润滑计划对象集合
	 * @return  
	 */
	void deleteAllLubricationPlan(List<LubricationPlan> list);
	
	/**
	 * 根据传入的润滑计划对象Id的数组,获取润滑计划对象集合
	 * 
	 * @param lubricationRuleIds 润滑计划对象Id的数组
	 * @return  润滑计划对象集合
	 */	
	List<LubricationPlan> loadAllLubricationPlan(Long[] lubricationPlanIds);
	
	/**
	 * 获取全部的润滑计划对象集合
	 * 
	 * @param 
	 * @return  润滑计划对象集合
	 */
	List<LubricationPlan> loadAllLubricationPlan();
}
