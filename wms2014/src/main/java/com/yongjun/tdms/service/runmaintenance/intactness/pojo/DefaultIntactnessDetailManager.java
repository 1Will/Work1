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
package com.yongjun.tdms.service.runmaintenance.intactness.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.intactness.IntactnessDetailDao;
import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;
import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessDetail;
import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessResultType;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.intactness.IntactnessDetailManager;
/**
 * <p>Title: DefaultIntactnessDetailManager
 * <p>Description: 设备鉴定业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.runmaintenance.intactness.IntactnessDetailManager
 */
public class DefaultIntactnessDetailManager implements IntactnessDetailManager {
	private final IntactnessDetailDao intactnessDetailDao;
	private final DeviceCardManager deviceCardManager;
	
	public DefaultIntactnessDetailManager(IntactnessDetailDao intactnessDetailDao,
			DeviceCardManager deviceCardManager) {
		this.intactnessDetailDao = intactnessDetailDao;
		this.deviceCardManager = deviceCardManager;
	}
	
	public IntactnessDetail loadIntactnessDetail(Long intactnessDetailId) {
		return this.intactnessDetailDao.loadIntactnessDetail(intactnessDetailId);
	}

	public List<IntactnessDetail> loadAllIntactnessDetails(
			Long[] intactnessDetailIds) {
		return this.intactnessDetailDao.loadAllIntactnessDetails(intactnessDetailIds);
	}

	public List<IntactnessDetail> loadAllIntactnessDetail() {
		return this.intactnessDetailDao.loadAllIntactnessDetail();
	}

	public void storeIntactnessDetail(IntactnessDetail intactnessDetail) {
		this.intactnessDetailDao.storeIntactnessDetail(intactnessDetail);
	}

	public void deleteIntactnessDetail(IntactnessDetail intactnessDetail) {
		this.intactnessDetailDao.deleteIntactnessDetail(intactnessDetail);
	}

	public void deleteAllIntactnessDetail(
			Collection<IntactnessDetail> intactnessDetail) {
		this.intactnessDetailDao.deleteAllIntactnessDetail(intactnessDetail);
	}

	public void storeIntactnessDetail(Intactness intactness, String newDeviceIds) {
		String [] deviceId = null;
		
		if (null != newDeviceIds) {
			deviceId = newDeviceIds.split(",");
		}
		addNewIntactnessDetail(intactness, deviceId);
	}
	
	/**
	 * 添加新的设备鉴定明细
	 * @param intactness 设备鉴定实体
	 * @param deviceId 设备ID
	 */
	private void addNewIntactnessDetail(Intactness intactness, String [] deviceId) {
		for (int i=0; i<deviceId.length; i++) {
			IntactnessDetail detail = new IntactnessDetail();
			detail.setDevice(this.deviceCardManager.loadDevice(Long.valueOf(deviceId[i])));
			detail.setIntactness(intactness);
			this.intactnessDetailDao.storeIntactnessDetail(detail);
		}
	}

	public void storeIntactnessDetail(String allIntactnessDetailId, String allIntactnessDetailResult, String allComment) {
		String [] intactnessDetailId = null;
		String [] intactnessDetailResult = null;
		String [] comment = null;
		
		if (null != allIntactnessDetailId) {
			intactnessDetailId = allIntactnessDetailId.split(",");
		}
		if (null != allIntactnessDetailResult) {
			intactnessDetailResult = allIntactnessDetailResult.split(",");
		}
		if (null != allComment) {
			comment = allComment.split(",");
		}
		updateIntactnessDetail(intactnessDetailId, intactnessDetailResult, comment);
	}
	
	private void updateIntactnessDetail(String [] intactnessDetailId,
			String [] intactnessDetailResult,
			String [] comment) {
		int count = 0;
		
		while (count < intactnessDetailId.length) {
			IntactnessDetail detail = this.intactnessDetailDao.loadIntactnessDetail(Long.valueOf(intactnessDetailId[count]));
			
			if (null != intactnessDetailResult) {                   //设置明细的鉴定详细
				for (int i=0; i<intactnessDetailResult.length; i=i+2) {
					if (intactnessDetailResult[i].equals(intactnessDetailId[count])) {
						//TODO  置设备的状态
						if (intactnessDetailResult[i+1].equals(IntactnessResultType.INTACT.toString())) {
							detail.setResult(IntactnessResultType.INTACT);
						} else {
							detail.setResult(IntactnessResultType.UNINTACT);
						}
						break;
					} else {
						detail.setResult(null);
					}
				}
			} else {
				detail.setResult(null);
			}
			
			if (null != comment) {                         //设置明细的详细描述
				for (int i=0; i<comment.length; i=i+2) {
					if(comment[i].equals(intactnessDetailId[count])) {
						detail.setComment(comment[i+1]);
						break;
					} else {
						detail.setComment(null);
					}
				}
			} else {
				detail.setComment(null);
			}
			
			this.intactnessDetailDao.storeIntactnessDetail(detail);
			count++;
		}
	}

}
