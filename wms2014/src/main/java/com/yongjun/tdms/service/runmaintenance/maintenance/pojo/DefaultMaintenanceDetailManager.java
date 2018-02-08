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
package com.yongjun.tdms.service.runmaintenance.maintenance.pojo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDao;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDetailDao;
import com.yongjun.tdms.model.runmaintenance.maintenance.Maintenance;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceResultType;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDetailManager;


/**
 * <p>Title: DefaultMaintenanceDetailManager
 * <p>Description: 设备保养明细业务访问定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class DefaultMaintenanceDetailManager extends BaseManager implements MaintenanceDetailManager{
	private final MaintenanceDetailDao maintenanceDetailDao;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	//private final MaintenanceDao maintenanceDao;
	
	public DefaultMaintenanceDetailManager(MaintenanceDetailDao maintenanceDetailDao,
			DepartmentManager departmentManager,
			UserManager userManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager
			//MaintenanceDao maintenanceDao
			) {
		this.maintenanceDetailDao = maintenanceDetailDao;
		this.departmentManager = departmentManager;
		this.userManager = userManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		//this.maintenanceDao = maintenanceDao;
	}
	
	public List<MaintenanceDetail> loadAllMaintenanceDetails(Long[] maintenanceDetailIds) {
		return this.maintenanceDetailDao.loadAllMaintenanceDetails(maintenanceDetailIds);
	}

	public MaintenanceDetail loadMaintenanceDetail(Long maintenanceDetailId) {
		return this.maintenanceDetailDao.loadMaintenanceDetail(maintenanceDetailId);
	}

	public void storeMaintenanceDetail(MaintenanceDetail maintenanceDetail) {
		this.maintenanceDetailDao.storeMaintenanceDetail(maintenanceDetail);
	}

	public void storeMaintenanceDetail(Maintenance maintenancePlan, String addToolingIds) {
		String [] deviceId = null;
		if (null != addToolingIds) {
			deviceId = addToolingIds.split(",");
		}
		for (int i=0;i<deviceId.length;i++) {
			MaintenanceDetail planDetail = new MaintenanceDetail();
			planDetail.setDevice(this.deviceCardManager.loadDevice(Long.valueOf(deviceId[i])));
			planDetail.setPlan(maintenancePlan);
			for (Maintenance maintenanceProc : maintenancePlan.getMaintenanceProc()) {
				planDetail.setProc(maintenanceProc);
			}
			planDetail.setResult(MaintenanceResultType.UNFINISHED);
			this.maintenanceDetailDao.storeMaintenanceDetail(planDetail);
		}
	}

	public void storeMaintenanceDetail(String allMaintenanceDetailId, 
			String allDate, 
			String allDutyPeople,
			String allResultType,
			String allComment) {
		String [] maintenanceDetailId = null;
		String [] date = null;
		String [] dutyPeople = null;
		String [] resultType = null;
		String [] comment = null;
		
		if (null != allMaintenanceDetailId) {
			maintenanceDetailId = allMaintenanceDetailId.split(",");
		}
		if (null != allDate) {
			date = allDate.split(",");
		}
		if (null != allDutyPeople) {
			dutyPeople = allDutyPeople.split(",");
		}
		if (null != allResultType) {
			resultType = allResultType.split(",");
		}
		if (null != allComment) {
			comment = allComment.split(",");
		}
		updateMaintenancePlanDetailContent (maintenanceDetailId,
				date,dutyPeople,resultType,comment);
	}
	
	private void updateMaintenancePlanDetailContent(String [] maintenanceDetailId,
			String [] date,String [] dutyPeople,String [] resultType,String [] comment){
		int detailNum = 0;
		while (detailNum < maintenanceDetailId.length) {
			MaintenanceDetail planDetail = this.maintenanceDetailDao.loadMaintenanceDetail(Long.valueOf(maintenanceDetailId[detailNum]));
		
			if (null != date) {
				for (int i=0;i<date.length;i+=2) {
					if (date[i].equals(maintenanceDetailId[detailNum])) {
						planDetail.setPlanDate(DateUtil.toDate(date[i+1],"yyyy-MM-dd"));
						break;
					} else {
						planDetail.setPlanDate(null);
					}
				}
			} else {
				planDetail.setPlanDate(null);
			}
			
			if (null != dutyPeople) {                   //更新负责人
				for (int i=0; i<dutyPeople.length; i=i+2) {
					if (dutyPeople[i].equals(maintenanceDetailId[detailNum])) {
						planDetail.setDutyPeople(this.userManager.loadUser(Long.valueOf(dutyPeople[i+1])));
						break;
					} else {
						planDetail.setDutyPeople(null);
					}
				}
			} else {
				planDetail.setDutyPeople(null);
			}
			
			if (null != resultType) {                   
				for (int i=0; i<resultType.length; i=i+2) {
					if (resultType[i].equals(maintenanceDetailId[detailNum])) {
						planDetail.setResultType(this.codeValueManager.loadCodeValue(Long.valueOf(resultType[i+1])));
						break;
					} else {
						planDetail.setResultType(null);
					}
				}
			} else {
				planDetail.setResultType(null);
			}
			
			if (null != comment) {                   //更新备注
				for (int i=0; i<comment.length; i=i+2) {
					if (comment[i].equals(maintenanceDetailId[detailNum])) {
						planDetail.setComment(comment[i+1]);
						break;
					} else {
						planDetail.setComment(null);
					}
				}
			} else {
				planDetail.setComment(null);
			}
//			planDetail.setActualDate(planDetail.getPlanDate());
			this.maintenanceDetailDao.storeMaintenanceDetail(planDetail);
			detailNum++;
		}
	}
	

	public void storeMaintenanceDetail(String allMaintenanceDetailId, String allDate, String allResult) {
		String [] maintenanceDetailId = null;
		String [] date = null;
		String [] result = null;
		
		if (null != allMaintenanceDetailId) {
			maintenanceDetailId = allMaintenanceDetailId.split(",");
		}
		if (null != allDate) {
			date = allDate.split(",");
		}
		if (null != allResult) {
			result = allResult.split(",");
		}
		updateMaintenanceProcDetailContent (maintenanceDetailId,date,result);
	}
	
	private void updateMaintenanceProcDetailContent(
			String [] maintenanceDetailId,String [] date,String [] result) {
		int detailNum = 0;
		
		while (detailNum<maintenanceDetailId.length) {
			MaintenanceDetail procDetail = this.maintenanceDetailDao.loadMaintenanceDetail(Long.valueOf(maintenanceDetailId[detailNum]));
			
			if (null != date) {
				for (int i=0;i<date.length;i+=2) {
					if (date[i].equals(maintenanceDetailId[detailNum])) {
						procDetail.setActualDate(DateUtil.toDate(date[i+1],"yyyy-MM-dd"));
						break;
					} else {
						procDetail.setActualDate(null);
					}
				}
			} else {
				procDetail.setActualDate(null);
			}
			
			if (null != result) {
				for (int i=0; i<result.length; i=i+2) {
					if (result[i].equals(maintenanceDetailId[detailNum])) {
						if (result[i+1].equals(MaintenanceResultType.UNFINISHED.toString())) {
							procDetail.setResult(MaintenanceResultType.UNFINISHED);
						} else {
							procDetail.setResult(MaintenanceResultType.FINISHED);
						}
						break;
					} else {
						procDetail.setResult(null);
					}
				}
			} else {
				procDetail.setResult(null);
			}
			this.maintenanceDetailDao.storeMaintenanceDetail(procDetail);
			detailNum++;
		}
	}

	public void deleteMaintenanceDetails(MaintenanceDetail maintenanceDetail) {
		
	}

	public void deleteAllMaintenanceDetails(Collection<MaintenanceDetail> maintenanceDetails) throws Exception{
	    updateMaintenanceDetailByMaintenanceDetailProc(maintenanceDetails);
		this.maintenanceDetailDao.deleteAllMaintenanceDetails(maintenanceDetails);
	}
	public void updateMaintenanceDetailByMaintenanceDetailProc(Collection<MaintenanceDetail> maintenanceDetails)throws Exception{

		for (MaintenanceDetail planDetail : maintenanceDetails) {
			if (planDetail.getResult().equals(MaintenanceResultType.FINISHED)){
				throw new Exception();
			}
			
		}
	}
	public MaintenanceDetail loadMaintenanceDetailBydeviceID(Long id) {
		return this.maintenanceDetailDao.loadMaintenanceDetailBydeviceID(id);
		 
	}

	public MaintenanceDetail loadMaintenanceDetailBydeviceIDAndMonth(Long id, String month) {
		
		return maintenanceDetailDao.loadMaintenanceDetailBydeviceIDAndMonth(id,month);
	}
}
