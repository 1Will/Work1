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
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.asset.device.DeviceFullUsingManager;

/**
 * <p>Title: ListDeviceFullUsingAction
 * <p>Description: 设备完好利用情况页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListDeviceFullUsingAction.java 9149 2007-12-09 06:29:38Z qsun $
 */
public class ListDeviceFullUsingAction extends ValueListAction{
	private static final long serialVersionUID = -6069111887511072334L;
	private final DeviceFullUsingManager deviceFullUsingManager;
	
	public ListDeviceFullUsingAction (DeviceFullUsingManager deviceFullUsingManager){
		this.deviceFullUsingManager = deviceFullUsingManager;
	}
	
	@Override
	protected String getAdapterName() {
		return "deviceFullUsing";
	}
	
//	public List<LabelValue> getMonths() {
//		List<LabelValue> tmp = new ArrayList<LabelValue>();
//		int year = DateUtil.getYear(Calendar.getInstance().getTime());
//		int month = DateUtil.getMonth(Calendar.getInstance().getTime())+1;
//		String m = null;
//		String date = null;
//		for (int i = 1;i< month ;i++){
//			if(i<10) {
//				m = "0"+String.valueOf(i);
//			}else {
//				m = String.valueOf(i);
//			}
//			date = String.valueOf(year)+"-"+ m;
//			LabelValue array = new LabelValue(date,date);
//			tmp.add(array);
//		}
//		return tmp;
//	}
	public List<LabelValue> getMonths() {
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		List monthList = new ArrayList<LabelValue>();
		monthList = deviceFullUsingManager.getMonths();
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
