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

import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDeviceDetail;

 /**
  * <p>Title: MaintenanceDeviceDetailManager
  * <p>Description: 设备保养详细业务访问定义类</p>
  * <p>Copyright: Copyright (c) 2007 yj-technology</p>
  * <p>Company: www.yj-technology.com</p>
  * @author wzou@yj-technology.com
  * @version $Id:$
  */
@Transactional(readOnly=true)
public interface MaintenanceDeviceDetailManager {
	List<MaintenanceDeviceDetail> loadAllMaintenanceDeviceDetails(Long[] maintenanceDeviceDetails);
	
	MaintenanceDeviceDetail loadMaintenanceDeviceDetail(Long maintenanceDeviceDetailId);
	
	@Transactional
	void storeMaintenanceDeviceDetail(MaintenanceDeviceDetail maintenanceDeviceDetail);
	
	/**
	 * 根据传入的设备保养详细的明细ID字符串、部位、方法、要求
	 * 备注字符串，更新计划明细中相应的内容
	 * @param allmaintenanceDeviceDetailId 明细ID字符串
	 * @param allPart 部位字符串
	 * @param allMethod 方法字符串
	 * @param allAppeal 要求字符串
	 */
	@Transactional
	void storeMaintenanceDeviceDetail(String  allmaintenanceDeviceDetailId,
			String allPart,
			String allMethod,
			String allAppeal);
	
	@Transactional
	void deleteMaintenanceDeviceDetails(Collection<MaintenanceDeviceDetail> maintenanceDeviceDetails)throws Exception;
}
