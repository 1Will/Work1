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
package com.yongjun.tdms.dao.runmaintenance.maintenance;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;

/**
 * <p>Title: MaintenanceDao
 * <p>Description:  设备保养数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: MaintenanceDao.java 9514 2007-12-19 10:12:05Z mwei $
 */
public interface MaintenanceDao {
	List<Maintenance>  loadAllMaintenances(Long[] maintenanceIds);
	
	List<Maintenance>  loadAllMaintenances();
	
	@Transactional
	void deleteAllMaintenances(Collection<Maintenance> maintenances);
	
	@Transactional
	void storeMaintenance(Maintenance maintenance);
	
	public Maintenance loadMaintenance(Long maintenanceId);
	
	@Transactional
	void deleteMaintenance(Maintenance maintenance);
	Maintenance getMaintenanceByPlanNoAndProc(String planNo);
	

}
