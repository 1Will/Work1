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
package com.yongjun.tdms.presentation.webwork.action.asset.device.accountReport;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceType;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
 
/**
 * <p>Title: ListDeviceOriginalNetStatisticsAction
 * <p>Description: 设备原值净值统计访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListDeviceOriginalNetStatisticsAction.java 2009-09-22 14:10:51Z wliu $
 */
public class ListDeviceOriginalNetStatisticsAction
extends ValueListAction{

	private static final long serialVersionUID = 1L;
	
	//设备类型业务逻辑接口
	private final DeviceTypeManager deviceTypeManager;
	private Long deviceTypeId;	//设备类型标识
	
	/**
	 * 设备原值净值统计访问控制类构造函数
	 * 注入设备类型业务逻辑接口
	 * @param deviceTypeManager
	 */
	public ListDeviceOriginalNetStatisticsAction(DeviceTypeManager deviceTypeManager) {

		this.deviceTypeManager = deviceTypeManager;
	}
	
	/**
	 * 默认调用方法
	 * 若页面请求中有参数(存放设备类型标识),将设备类型标识ID赋值
	 */
	@Override
	public void prepare() throws Exception {
		if(null != request.getParameter("deviceType.id")){
			deviceTypeId = Long.parseLong(request.getParameter("deviceType.id"));
		}
		this.setFirst(true);
	
	}
	
	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(request.getParameter("deviceType.id")!=null){
			map.put("deviceTypeId",this.getId("deviceType.id"));
		}
		return map;
	}
	
	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected String getAdapterName() {
		
		return "DeviceOriginalNetStatisticsHQL";
	}

	
	/**
	 * 获取所有设备类型信息
	 * @return List 设备类型集合
	 */
	public List getAllDeviceType(){
		
		List<DeviceType> deviceTypes = deviceTypeManager.loadAllDeviceTypes();
		DeviceType dt = new DeviceType();
		dt.setId(Long.valueOf(-1L));
		dt.setName(this.getText("select.option.all"));
		deviceTypes.add(0,dt);
		return deviceTypes;
	}
	
	/**
	 * @return the deviceTypeId
	 */
	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	/**
	 * @param deviceTypeId the deviceTypeId to set
	 */
	public void setDeviceTypeId(Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

}
