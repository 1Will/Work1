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

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanView;


/**
 * <p>Title: PreRepairPlanManager
 * <p>Description: 预防性维修计划业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: PreRepairPlanManager.java 11160 2008-02-29 10:15:24Z zbzhang $
 */
@Transactional(readOnly=true)
public interface PreRepairPlanManager {
	
	/**
     * 传入预防性维修计划ID集合，获取集合到数据库
     * 
     * @param preRepairPlanIds	预防性维修计划ID集合
     * @return List				预防性维修计划集合
     */
	List<PreRepairPlan> loadAllPreRepairPlans(Long[] preRepairPlanIds);
	
	/**
	 * 根据传入的预防性维修计划集合，判断集合中的计划是否已制定实施，
	 * 如果已制定，则不让其删除，否则删除集合中的预防性维修计划单
	 * 
	 * @param preRepairPlans	预防性维修计划集合
	 * @return
	 */
	@Transactional
	void deleteAllPreRepairPlan(Collection<PreRepairPlan> preRepairPlans) throws Exception;
	
	/**
	 * 根据传入的预防性维修计划对象，保存该预防性维修计划单,并通过该计划单创建实施单
	 * 
	 * @param preRepairPlan	预防性维修计划对象
	 * @return
	 */
	@Transactional
	void storePreRepairPlan(PreRepairPlan preRepairPlan);
	
	/**
	 * 根据传入的预防性维修实施对象，保存该预防性维修实施单
	 * 
	 * @param preRepairProc	预防性维修实施对象
	 * @return
	 */
	@Transactional
	void storePreRepairProc(PreRepairPlan preRepairProc);
	
	
	/**
	 * 根据传入的预防性维修计划对象ID，导出该预防性维修计划对象
	 * 
	 * @param preRepairPlanId	预防性维修计划ID
	 * @return
	 */
	@Transactional
	PreRepairPlan loadPreRepairPlan(Long preRepairPlanId);
	
	/**
	 * 根据页面传入的预防性计划ID,复制该计划，并保存为预防性维修实施
	 * @param preRepairPlanId 预防性计划ID
	 */
	@Transactional
    PreRepairPlan storePreRepairProc(Long preRepairPlanId);
	
	/**
	 * 根据传入的预防性维修实施集合，删除集合中的预防性维修实施
	 * @param preRepairProc
	 */
	@Transactional
	void deleteAllPreRepairProc(Long[] preRepairProcIds);
	
	/**
	 * 根据传入的预防性维修计划，删除预防性维修计划单
	 * 
	 * @param preRepairPlan	预防性维修计划
	 * @return
	 */
	@Transactional
	void deletePreRepairPlan(PreRepairPlan preRepairPlan);
	
	/**
	 * 重置预防性维修实施的实际开始时间
	 * @param preRepairProc 预防性维修实施实体
	 */
	@Transactional
	void resetPreRepairProcEstimateExecDate(PreRepairPlan preRepairProc);
	
	/**
	 * 重置预防性维修计划和其相关联的实施的总费用
	 * @param plan 预防性维修计划
	 */
	@Transactional
	void resetPreRepairPlanOrProcFee(PreRepairPlan plan);
	@Transactional
	List<PreRepairPlan> Query(String[] queryInfo)throws HibernateException;
	
	@Transactional
	void cancelJob(PreRepairPlan preRepairProc);

	public List<PreRepairPlanView> loadAllPreRepairPlanView(String[] array) throws HibernateException;
}
