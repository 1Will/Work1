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
package com.yongjun.tdms.presentation.webwork.action.report;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.ManagementLevel;
import com.yongjun.tdms.model.report.DeviceUseStatus;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceUseStatusManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: ListDeviceUseStatus
 * <p>Description: 主要生成设备使用状况页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListAccidentBillAction.java 9149 2007-12-09 06:29:38Z qsun $
 */
public class ListDeviceUseStatusAction extends ValueListAction{
	private static final long serialVersionUID = 1649917677285044530L;
	private final DeviceUseStatusManager deviceUseStatusManager;
	
	public ListDeviceUseStatusAction(DeviceUseStatusManager deviceUseStatusManager) {
		this.deviceUseStatusManager = deviceUseStatusManager;
	}

	@Override
	protected String getAdapterName() {
			return "deviceUseStatus";
	}
	
	public List<LabelValue> getMonths() {
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		List monthList = new ArrayList<LabelValue>();
		monthList = deviceUseStatusManager.getMonths();
		int length = monthList.size();
		if (length != 0) {
			String date = null;
			for (int i = 0;i< length ;i++){
				date = (String)monthList.get(i);
				LabelValue array = new LabelValue(date,date); 
				tmp.add(array);
			}
		}	
		return tmp;
	}
	
}
