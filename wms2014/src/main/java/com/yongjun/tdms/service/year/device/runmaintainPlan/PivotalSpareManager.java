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
package com.yongjun.tdms.service.year.device.runmaintainPlan;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.device.runmaintainPlan.PivotalSpare;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;

/**
 * <p>Title: PivotalSpareManager
 * <p>Description: 运维计划的关键备件业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface PivotalSpareManager {
	/**
	 * 根据传入运维计划的关键备件ID,获取运维计划的关键备件对象
	 * @param pivotalSpareId 运维计划的关键备件ID
	 * @return PivotalSpare 运维计划的关键备件对象
	 */
	PivotalSpare loadPivotalSpare(Long pivotalSpareId);
	
	/**
	 * 根据传入运维计划的关键备件ID集合,获取集合运维计划的关键备件对象
	 * @param pivotalSpareIds 运维计划的关键备件ID集合
	 * @return List 集合运维计划的关键备件对象
	 */
	List<PivotalSpare> loadAllPivotalSpares(Long [] pivotalSpareIds);
	
	/**
	 * 获取集合运维计划的关键备件对象
	 * @return  List 集合运维计划的关键备件对象
	 */
	List<PivotalSpare> loadAllPivotalSpares();
	
	/**
	 * 保存传入的运维计划的关键备件对象
	 * @param pivotalSpare 运维计划的关键备件对象
	 */
	@Transactional
	void storePivotalSpare(PivotalSpare pivotalSpare);
	
	/**
	 * 删除传入的运维计划的关键备件对象
	 * @param pivotalSpare 运维计划的关键备件对象
	 */
	@Transactional
	void deletePivotalSpare(PivotalSpare pivotalSpare);
	
	/**
	 * 删除传入的集合中的运维计划的关键备件对象
	 * @param pivotalSpares 运维计划的关键备件对象集合
	 */
	@Transactional
	void deleteAllPivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail, Collection<PivotalSpare> pivotalSpares);
	
	/**
	 * 根据传入的运维计划详细、新添加的备件ID,创建与运维计划明细关联的关键备件
	 * @param runmaintainPlanDetail 运维计划详细
	 * @param newAddDeviceIds 备件ID
	 */
	@Transactional
	void storePivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail, String newAddSpareIds);
	
	/**
	 * 根据传入的关键备件ID字符串，使用数量字符串，备注字符串，来更新关键备件相应的值，并更新其关联的关键备件总费用
	 * @param allPivotalSpareIds 关键备件ID字符串
	 * @param allUsedNums 使用数量字符串
	 * @param allComments 备注字符串
	 */
	@Transactional
	void storePivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail, String allPivotalSpareIds, String allUsedNums, String allComments);
}
