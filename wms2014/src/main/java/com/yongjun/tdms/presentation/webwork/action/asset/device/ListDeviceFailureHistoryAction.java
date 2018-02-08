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
package com.yongjun.tdms.presentation.webwork.action.asset.device;

//import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
//import com.yongjun.tdms.model.asset.device.DeviceCard;
//import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * <p>Title: ListDeviceFailureHistoryAction
 * <p>Description: 设备故障历史访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListDeviceFailureHistoryAction.java 9886 2009-09-09 11:26:41Z wliu $
 */
public class ListDeviceFailureHistoryAction
extends ValueListAction{

	private static final long serialVersionUID = 1L;
//	//设备故障历史业务逻辑接口
//	private final DeviceFailureHistoryManager deviceFailureHistoryManager;
//	//设备业务逻辑接口
//	private final DeviceCardManager deviceCardManager;
//	//设备故障历史类集合
//	private List<DeviceFailureHistory> dfhList;
	//设备标识ID
	private Long deviceId;
	
	/**
	 * 设备故障历史访问控制类无参构造函数
	 * 注入设备故障历史业务逻辑接口
	 * @param deviceFailureHistoryManager
	 */
	public ListDeviceFailureHistoryAction() {

	}

	/**
	 * 默认调用方法
	 * 若页面请求中有参数(存放设备标识和设备故障历史集合),将设备实体类和设备故障历史集合赋值
	 */
	@Override
	public void prepare() throws Exception {
		
		if(null != request.getParameter("device.id")){
			this.deviceId = Long.parseLong(request.getParameter("device.id"));
		}
		this.setFirst(false);
	}
	
	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("deviceId",this.getId("device.id"));
		return map;
	}
	
	/**
	 * 调用valueList配置文件关联的hql语句
	 */
	@Override
	protected String getAdapterName() {
		
		return "DeviceFailureHistoryHQL";
	}

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

}
