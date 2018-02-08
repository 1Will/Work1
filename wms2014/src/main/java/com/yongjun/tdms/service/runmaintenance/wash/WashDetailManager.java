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
package com.yongjun.tdms.service.runmaintenance.wash;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;

/**
 * <p>Title: WashDetailManager
 * <p>Description: 清洗明细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface WashDetailManager {
	/**
	 *  根据传入的清洗明细的ID,获取清洗明细对象
	 * @param washDetailId 清洗明细的ID
	 * @return WashDetail 清洗明细对象
	 */
	WashDetail loadWashDetail(Long washDetailId);
	
	/**
	 * 根据传入的清洗明细的ID集合，获取清洗明细对象集合
	 * @param washDetailIds 清洗明细的ID集合
	 * @return List 清洗明细对象集合
	 */
	List<WashDetail> loadAllWashDetails(Long [] washDetailIds);
	
	/**
	 * 获取清洗明细对象集合
	 * @return List 清洗明细对象集合
	 */
	List<WashDetail> loadAllWashDetails();
	
	/**
	 * 保存传入的清洗明细对象
	 * @param detail 清洗明细对象
	 */
	@Transactional
	void storeWashDetail(WashDetail detail);
	
	/**
	 * 删除传入的清洗明细对象
	 * @param detail 清洗明细对象
	 */
	@Transactional
	void deleteWashDetail(WashDetail detail);
	
	/**
	 * 根据传入清洗明细集合，删除集合中的清洗明细对象
	 * @param details 清洗明细集合
	 */
	@Transactional
	void delteAllWashDetail(Collection<WashDetail> details);
	
	/**
	 * 根据传入的清洗计划ID、工装ID的字符串，新建清洗计划明细
	 * @param washPlan 清洗计划ID
	 * @param toolingIds 工装ID的字符串
	 */
	@Transactional
	void storeWashDetail(Wash washPlan, String toolingIds);
	
	/**
	 * 根据传入的清洗计划明细的明细ID字符串、计划清洗日期字符串、负责人字符串、监督人字符串、产品型号字符串、
	 * 备注字符串，更新计划明细中相应的内容
	 * @param allWashDetailId 明细ID字符串
	 * @param allPlanWashDate 计划清洗日期字符串
	 * @param allDutyPeople 负责人字符串
	 * @param allSupervisePeople 监督人字符串
	 * @param allProductModel 产品型号字符串
	 * @param allComment 备注字符串
	 */
	@Transactional
	void storeWashDetail(String allWashDetailId, String allPlanWashDate,
			String allDutyPeople, String allSupervisePeople,
			String allProductModel, String allComment);
	
	/**
	 * 根据传入的清洗实施明细的明细ID字符串、实际清洗日期字符串、清洗结果字符串，更新实施明细中相应的内容
	 * @param allWashDetailId 明细ID字符串
	 * @param allProcWashDate 实际清洗日期字符串
	 * @param allWashResult 清洗结果字符串
	 */
	@Transactional
	void storeWashDetail(String allWashDetailId, String allProcWashDate, String allWashResult);
	
	/**
	 * 根据传入清洗明细集合，删除集合中的清洗明细对象,如果有明细被实施,则抛出异常
	 * @param details 清洗明细集合
	 */
	@Transactional
	void delteAllWashDetailPlans(Collection<WashDetail> details) throws Exception;
			
	
	
}
