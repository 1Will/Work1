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

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;

/**
 * <p>Title: ListFaultAnalysisByDeviceTypeAction
 * <p>Description: 设备类型故障统计访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListFaultAnalysisByDeviceTypeAction.java 11417 2009-09-11 11:01:51Z wliu $
 */
public class ListFaultAnalysisByDeviceTypeAction 
extends ValueListAction{

	private static final long serialVersionUID = 1L;
	//设备类别业务逻辑接口
	private final DeviceTypeManager deviceTypeManager;
	
	/**
	 * 设备类型故障统计访问控制类构造函数
	 * 注入设备类型业务逻辑接口
	 * @param deviceTypeManager 
	 */
	public ListFaultAnalysisByDeviceTypeAction(DeviceTypeManager deviceTypeManager) {
		
		this.deviceTypeManager = deviceTypeManager;
	}
	
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
		
		return "FaultAnalysisByDeviceTypeHQL";
	}

	/**
	 * 获得所有设备类型信息，并添加首行选择所有
	 * @return
	 */
	public List<DeviceType> getAllDeviceTypes(){
		
		List<DeviceType> list = deviceTypeManager.loadAllDeviceTypes();
		DeviceType d = new DeviceType();
		d.setName(this.getText("select.option.all"));
		d.setId(-1L);
		list.add(0, d);
		return list;
	}
	
}
