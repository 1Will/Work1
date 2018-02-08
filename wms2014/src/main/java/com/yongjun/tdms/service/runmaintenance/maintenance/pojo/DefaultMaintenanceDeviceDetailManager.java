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
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.maintenance.MaintenanceDeviceDetailDao;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDeviceDetail;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDeviceDetailManager;

/**
 * <p>Title: DefaultMaintenanceDeviceDetailManager
 * <p>Description: 设备保养详细业务访问定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:$
 */
public class DefaultMaintenanceDeviceDetailManager extends BaseManager implements MaintenanceDeviceDetailManager{
	private final MaintenanceDeviceDetailDao maintenanceDeviceDetailDao;
	
	public DefaultMaintenanceDeviceDetailManager(MaintenanceDeviceDetailDao maintenanceDeviceDetailDao) {
		this.maintenanceDeviceDetailDao = maintenanceDeviceDetailDao;
	}

	public List<MaintenanceDeviceDetail> loadAllMaintenanceDeviceDetails(Long[] maintenanceDeviceDetails) {
		return this.maintenanceDeviceDetailDao.loadAllMaintenanceDeviceDetails(maintenanceDeviceDetails);
	}
	
	public MaintenanceDeviceDetail loadMaintenanceDeviceDetail(Long maintenanceDeviceDetailId) {
		return this.maintenanceDeviceDetailDao.loadMaintenanceDeviceDetail(maintenanceDeviceDetailId);
	}

	public void storeMaintenanceDeviceDetail(MaintenanceDeviceDetail maintenanceDeviceDetail) {
		this.maintenanceDeviceDetailDao.storeMaintenanceDeviceDetail(maintenanceDeviceDetail);
	}

	public void storeMaintenanceDeviceDetail(String allmaintenanceDeviceDetailId, String allPart, String allMethod, String allAppeal) {
		String [] maintenanceDeviceDetailId = null;
		String [] part = null;
		String [] method = null;
		String [] appeal = null;
		if (null != allmaintenanceDeviceDetailId) {
			maintenanceDeviceDetailId = allmaintenanceDeviceDetailId.split(",");
		}
		if (null != allPart) {
			part = allPart.split(",");
		}
		if (null != allMethod) {
			method = allMethod.split(",");
		}
		if (null != allAppeal) {
			appeal = allAppeal.split(",");
		}
		updateMaintenanceDeviceDetail(maintenanceDeviceDetailId,
				part,
				method,
				appeal
				);
	}
		private void updateMaintenanceDeviceDetail(String [] maintenanceDeviceDetailId,
				String [] part,
				String [] method,
				String [] appeal){
			int detailNum = 0;
			
			while (detailNum < maintenanceDeviceDetailId.length) {
				MaintenanceDeviceDetail detail = this.maintenanceDeviceDetailDao.loadMaintenanceDeviceDetail(Long.valueOf(maintenanceDeviceDetailId[detailNum]));
				if (null != part) {                   //更新部位
					for (int i=0; i<part.length; i=i+2) {
						if (part[i].equals(maintenanceDeviceDetailId[detailNum])) {
							detail.setPart(part[i+1]);
							break;
						} else {
							detail.setPart(null);
						}
					}
				} else {
					detail.setPart(null);
				}
				
				if (null != method) {                   //更新方法
					for (int i=0; i<method.length; i=i+2) {
						if (method[i].equals(maintenanceDeviceDetailId[detailNum])) {
							detail.setMethod(method[i+1]);
							break;
						} else {
							detail.setMethod(null);
						}
					}
				} else {
					detail.setMethod(null);
				}
				if (null != appeal) {                   //更新要求
					for (int i=0; i<appeal.length; i=i+2) {
						if (appeal[i].equals(maintenanceDeviceDetailId[detailNum])) {
							detail.setAppeal(appeal[i+1]);
							break;
						} else {
							detail.setAppeal(null);
						}
					}
				} else {
					detail.setAppeal(null);
				}
				this.maintenanceDeviceDetailDao.storeMaintenanceDeviceDetail(detail);
				detailNum++;
			}
			
	}
	

	public void deleteMaintenanceDeviceDetails(Collection<MaintenanceDeviceDetail> maintenanceDeviceDetails) throws Exception {
		this.maintenanceDeviceDetailDao.deleteMaintenanceDeviceDetails(maintenanceDeviceDetails);
	}
	
}
