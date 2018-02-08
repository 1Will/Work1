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
package com.yongjun.tdms.service.year.repair;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcView;

/**
 * <p>Title: RepairPlanAndProcManager
 * <p>Description: 大项修计划和实施业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface RepairPlanAndProcManager {
	
	/**
     * 传入大项修计划ID集合，获取集合到数据库
     * 
     * @param repairPlanOrProcIds	大项修计划ID集合
     * @return List					大项修计划集合
     */
	List<RepairPlanAndProc> loadAllRepairPlanOrProcs(Long[] repairPlanOrProcIds);
	
	/**
	 * 根据传入的大项修计划集合，判断集合中的计划是否已制定实施，
	 * 如果已制定，则不让其删除，否则删除集合中的大项修计划单
	 * 
	 * @param repairPlanAndProcs	大项修计划集合
	 * @return
	 */
	@Transactional
	void deleteAllRepairPlanOrProcs(Collection<RepairPlanAndProc> repairPlanAndProcs) throws Exception;
	
	/**
     * 传入大项修计划或实施ID，获取大项修计划或实施
     * 
     * @param repairPlanOrProcId	大项修计划或实施ID
     * @return RepairPlanAndProc	大项修计划或实施
     */
	RepairPlanAndProc loadRepairPlanOrProc(Long repairPlanOrProcId);
	
	/**
     * 传入大项修计划，保存大项修计划
     * 
     * @param repairPlanAndProc	大项修计划
     * 
     */
	@Transactional
	void storeRepairPlan(RepairPlanAndProc repairPlan);
	
	/**
	 * 传入大项修实施,保存大项修实施
	 * @param repairProc 大项修实施
	 */
	@Transactional
	void storeRepairProc(RepairPlanAndProc repairProc);
	
	@Transactional
	void cancelJob(RepairPlanAndProc repairPlanAndProc);
	
	/**
	 * 根据传入的大项修计划,合计该计划相关的计划详细的维修费用,更新计划总费用
	 * @param plan 大项修计划
	 */
	void resetYearRepairPlanOrProcFee(RepairPlanAndProc plan);

	public List<RepairPlanAndProcView> loadAllRepairPlanAndProcView(String[] array) throws HibernateException;
	
	
}
