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
package com.yongjun.tdms.dao.runmaintenance.repair;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;

/**
 * <p>Title: PreRepairPlanDao
 * <p>Description: 预防性维修计划数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: PreRepairPlanDao.java 10412 2008-01-16 01:27:50Z qsun $
 */
public interface PreRepairPlanDao {
	/**
     * 传入预防性维修计划ID集合，获取集合到数据库
     * 
     * @param preRepairPlanIds	预防性维修计划ID集合
     * @return List				预防性维修计划集合
     */
	List<PreRepairPlan> loadAllPreRepairPlans(Long[] preRepairPlanIds);
	
	/**
	 * 根据传入的预防性维修计划ID集合，删除集合中的预防性维修计划单
	 * 
	 * @param preRepairPlanIds	预防性维修计划ID集合
	 * @return
	 */
	void deleteAllPreRepairPlan(Collection<PreRepairPlan> preRepairPlans);
	
	/**
	 * 根据传入的预防性维修计划对象，保存该预防性维修计划单
	 * 
	 * @param preRepairPlan	预防性维修计划对象
	 * @return
	 */
	void storePreRepairPlan(PreRepairPlan preRepairPlan);
	
	/**
	 * 根据传入的预防性维修计划对象ID，导出该预防性维修计划对象
	 * 
	 * @param preRepairPlanId	预防性维修计划ID
	 * @return
	 */
	public PreRepairPlan loadPreRepairPlan(Long preRepairPlanId);
	
	List Query(String[] queryInfo) throws HibernateException;
	
	/**
	 * 根据传入的预防性维修计划，删除预防性维修计划单
	 * 
	 * @param preRepairPlan	预防性维修计划
	 * @return
	 */
	void deletePreRepairPlan(PreRepairPlan preRepairPlan);

}
