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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.fault;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
//import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * <p>Title: ListFaultAnalysisByDeviceAction
 * <p>Description: 设备故障统计访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListFaultAnalysisByDeviceAction.java 11417 2009-09-11 9:01:51Z wliu $
 */
public class ListFaultAnalysisByDeviceAction 
extends ValueListAction{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认调用方法
	 */
	@Override
	public void prepare() throws Exception {

		this.setFirst(true);
	}
	
	/**
	 * 调用valueList配置文件关联的hql语句
	 */
	@Override
	protected String getAdapterName() {
		
		return "FaultAnalysisByDeviceHQL";
	}

}
