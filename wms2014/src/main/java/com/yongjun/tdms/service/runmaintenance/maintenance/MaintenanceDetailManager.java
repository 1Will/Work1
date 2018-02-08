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
package com.yongjun.tdms.service.runmaintenance.maintenance;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;

/**
 * <p>Title: MaintenanceDetailManager
 * <p>Description: 设备保养明细业务访问定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface MaintenanceDetailManager {
	List<MaintenanceDetail> loadAllMaintenanceDetails(Long[] maintenanceDetailIds);
	
	MaintenanceDetail loadMaintenanceDetail(Long maintenanceDetailId);
	
	@Transactional
	void storeMaintenanceDetail(MaintenanceDetail maintenanceDetail);
	
	@Transactional
	void storeMaintenanceDetail(Maintenance maintenance, String addToolingIds);
	
	/**
	 * 根据传入的保养计划明细的明细ID字符串、部位、方法、要求、计划完成日期字符、负责人字符串
	 * 备注字符串，更新计划明细中相应的内容
	 * @param allMaintenanceDetailId 明细ID字符串
	 * @param allPart 部位字符串
	 * @param allMethod 方法字符串
	 * @param allAppeal 要求字符串
	 * @param allDate 计划完成日期字符串/实施完成日期
	 * @param allDutyPeople 负责人字符串
	 * @param allComment 备注字符串
	 */
	@Transactional
	void storeMaintenanceDetail(String allMaintenanceDetailId,
//			String allPart,
//			String allMethod,
//			String allAppeal,
			String allDate,
			String allDutyPeople,
			String allResultType,
			String allComment);
	
	/**
	 * 根据传入的保养计划明细的明细ID字符串、计划完成日期字符、负责人字符串
	 * @param allMaintenanceDetailId 明细ID字符串
	 * @param allDate 	实施完成日期
	 * @param allResult 保养结果
	 */
	@Transactional
	void storeMaintenanceDetail(String allMaintenanceDetailId,
			String allDate,
			String allResult);
	
	@Transactional
	void deleteMaintenanceDetails(MaintenanceDetail maintenanceDetail);
	
	@Transactional
	void deleteAllMaintenanceDetails(Collection<MaintenanceDetail> maintenanceDetails)throws Exception;
	public MaintenanceDetail loadMaintenanceDetailBydeviceID(final Long id);
	public MaintenanceDetail loadMaintenanceDetailBydeviceIDAndMonth(final Long id,final String month) ;
	
}
