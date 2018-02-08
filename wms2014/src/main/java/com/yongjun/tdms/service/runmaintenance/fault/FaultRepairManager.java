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
package com.yongjun.tdms.service.runmaintenance.fault;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;

/**
 * <p>Title: FaultRepairManager
 * <p>Description: 故障维修业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface FaultRepairManager {
	/**
	 * 根据传入的故障维修的ID,获取故障维修实体
	 * @param faultRepairId 故障维修的ID 
	 * @return FaultRepair 故障维修实体
	 */
	FaultRepair loadFaultRepair(Long faultRepairId);
	
	/**
	 * 根据传入的故障维修ID集合,获取故障维修实体集合
	 * @param faultRepairIds 故障维修ID集合
	 * @return List 故障维修实体集合
	 */
	List<FaultRepair> loadAllFaultRepairs(Long [] faultRepairIds);
	
	/**
	 * 获取故障维修实体集合
	 * @return List 故障维修实体集合
	 */
	List<FaultRepair> loadAllFaultRepairs();
	
	/**
	 *  保存传入的故障维修对象
	 * @param faultRepair 故障维修对象
	 */
	@Transactional
	void storeFaultRepair(FaultRepair faultRepair);
	
	/**
	 * 删除传入的故障维修对象
	 * @param faultRepair 故障维修对象
	 */
	@Transactional
	void deleteFaultRepair(FaultRepair faultRepair);
	
	/**
	 * 删除传入的故障维修集合
	 * @param faultRepairs 故障维修集合
	 */
	@Transactional
	void deleteAllFaultRepairs(Collection<FaultRepair> faultRepairs);
}
