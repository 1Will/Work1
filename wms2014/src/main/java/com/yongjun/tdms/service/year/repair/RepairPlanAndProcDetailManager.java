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

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairPlanAndProcDetailManager
 * <p>Description: 大项修计划和实施明细业务访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface RepairPlanAndProcDetailManager {
	
	/**
	 * 根据传入的大项修计划或实施明细的ID,获取大项修计划或实施明细
	 * @param planOrProcDetailId 大项修计划或实施明细的ID
	 * @return RepairPlanAndProcDetail 大项修计划或实施明细
	 */
	RepairPlanAndProcDetail loadRepairPlanAndProcDetail(Long planOrProcDetailId);
	
	/**
	 *  根据传入的大项修计划或实施明细的ID集合,获取大项修计划或实施明细集合
	 * @param planOrProcDetailIds 大项修计划或实施明细的ID集合
	 * @return List 大项修计划或实施明细集合
	 */
	List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails(Long [] planOrProcDetailIds);
	
	/**
	 * 获取大项修计划或实施明细集合
	 * @return List 大项修计划或实施明细集合
	 */
	List<RepairPlanAndProcDetail> loadAllRepairPlanOrProcDetails();
	
	/**
	 * 保存大项修计划详细,并使大项修实施关联到该计划详细
	 * @param planDetail 大项修计划详细
	 */
	@Transactional
	void storeRepairPlanDetail(RepairPlanAndProcDetail planDetail);
	
	/**
	 * 保存大项修实施详细
	 * @param procDetail 大项修实施详细
	 */
	@Transactional
	void storeRepairProcDetail(RepairPlanAndProcDetail procDetail);
	
	/**
	 * 删除大项修计划或实施明细
	 * @param planAndProcDetail 大项修计划或实施明细
	 */
	@Transactional
	void deleteRepairPlanAndProcDetail(RepairPlanAndProcDetail planOrProcDetail);
	
	/**
	 * 根据传入的大项修计划或实施明细的集合，删除集合中的大项修计划或实施
	 * @param planAndProcDetails 大项修计划或实施明细的集合
	 */
	@Transactional
	void deleteAllRepairPlanAndProcDetails(Collection<RepairPlanAndProcDetail> planOrProcDetails);
	
	/**
	 * 根据传入的大项修计划明细ID,承修单位,技术等级,种类,计划修理时间,计划开工日期,计划完工日期,外协,来更新计划明细的值,并设置实施字段的默认值
	 * @param allallYearRepairPlanDetailId 计划明细ID
	 * @param allYearRepairPlanDetailDepartment 承修单位
	 * @param allYearRepairPlanDetailTechnicalLevel 技术等级
	 * @param allYearRepairPlanDetailCategory 种类
	 * @param allYearRepairPlanDetailPlanRepairDate 计划修理时间
	 * @param allYearRepairPlanDetailPlanBeginDate 计划开工日期
	 * @param allYearRepairPlanDetailPlanEndDate 计划完工日期
	 * @param allYearRepairPlanDetailExternalHelp 外协
	 */
	@Transactional
	void storeAllRepairPlanDetails(String allYearRepairPlanDetailId,
			String allYearRepairPlanDetailDepartment,String allYearRepairPlanDetailCategory, 
			String allYearRepairPlanDetailPlanRepairDate,String allYearRepairPlanDetailPlanBeginDate, 
			String allYearRepairPlanDetailPlanEndDate,String allYearRepairPlanDetailExternalHelp);
	
	/**
	 * 根据传入的大项修实施明细ID,实际开工日期,实际完工日期,执行人,执行结果，来更新计划明细的值
	 * @param allYearRepairPlanDetailId 实施明细ID
	 * @param allYearRepairPlanDetailPlanBeginDate 实际开工日期
	 * @param allYearRepairPlanDetailPlanEndDate 实际完工日期
	 * @param allProcExePeople 执行人
	 * @param allProcExecResult 执行结果
	 * @param allYearRepairPlanDetailPlanRepairDate  实际维修日期
	 */
	@Transactional
	void storeAllRepairProcDetails(String allYearRepairProcDetailId,
			String allYearRepairPlanDetailProcBeginDate, 
			String allYearRepairPlanDetailProcEndDate,
			String allProcExePeople, String allProcExecResult,
			String allYearRepairPlanDetailProcRepairDate);

	/**
	 * 根据传入的大项修计划明细集合,并判断集合中是否有已经实施的,如有实施,则一个都不让删除,否全部删除,并更新相关的计划的总费用
	 * @param yearRepairPlanDetails 大项修计划明细集合
	 * @param plan 大项修计划
	 * @throws Exception 如果有计划明细已经实施,则抛出异常
	 */
	@Transactional
    void deleteAllYearRepairPlanDetail(
			Collection<RepairPlanAndProcDetail> yearRepairPlanDetails, RepairPlanAndProc plan) throws Exception;
}
