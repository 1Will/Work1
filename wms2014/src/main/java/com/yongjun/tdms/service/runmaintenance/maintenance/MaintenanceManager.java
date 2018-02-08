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

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetailView;

/**
 * <p>Title: MaintenanceManager
 * <p>Description: 设备保养业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface MaintenanceManager {
	List<Maintenance>  loadAllMaintenances(Long[] maintenanceIds);
	
	@Transactional
	void deleteAllMaintenances(Collection<Maintenance> maintenances);
	
	@Transactional
	void storeMaintenance(Maintenance maintenance);
	
	public Maintenance loadMaintenance(Long maintenanceId);
	
	@Transactional
	void disabledAllMaintenances(Collection<Maintenance> maintenances)throws Exception;
	
	@Transactional
	void enabledAllMaintenances(Collection<Maintenance> maintenances);
	
	/**
	 * 生成下月设备保养单
	 */
	@Transactional
	void createMaintenancePlanByScheduler();
	
	public List<MaintenanceDetailView> loadAllMaintenanceDetailView(String[] array) throws HibernateException;
	
	Maintenance getMaintenanceByPlanNoAndProc(String planNo);
}
