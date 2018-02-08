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
//import com.yongjun.tdms.model.asset.device.DeviceDepreciationDetail;
//import com.yongjun.tdms.service.asset.device.DeviceCardManager;
//import com.yongjun.tdms.service.asset.device.DeviceDepreciationDetailManager;

/**
 * <p>Title: ListDeviceDepreciationDetail
 * <p>Description: 设备折旧明细访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListDeviceDepreciationDetail.java 11417 2009-09-03 16:23:51Z wliu $
 */
public class ListDeviceDepreciationDetailAction
extends ValueListAction{

	private static final long serialVersionUID = -8391488865832623007L;
	
//	private final DeviceDepreciationDetailManager dddManager;	//设备折旧明细业务逻辑接口
//	private final DeviceCardManager deviceCardManager;	//设备台账业务逻辑接口
//	DeviceDepreciationDetail deviceDepreciationDetail;	//设备折旧明细实体类
//	List<DeviceDepreciationDetail> ddds;	//设备折旧明细实体类集合
	private Long deviceId;	//设备标识Id
	
	/**
	 * 设备折旧明细访问控制类构造函数
	 * @param dddManager 注入设备折旧明细业务逻辑
	 * @param deviceCardManager 注入设备台账业务逻辑
	 */
	public ListDeviceDepreciationDetailAction() {
		
	}

	/**
	 * 默认调用方法
	 * 若页面请求中有参数(存放设备标识),将设备标识ID赋值
	 */
	@Override
	public void prepare() throws Exception {
		if(null != request.getParameter("deviceCard.id")){
			deviceId = Long.parseLong(request.getParameter("deviceCard.id"));
		}
		this.setFirst(true);
	
	}
	
	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("device.id",this.getId("deviceCard.id"));
		return map;
	}
	
	/**
	 * 执行调用的方法
	 */
	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}

	/**
	 * 调用valueList配置文件关联的hql语句
	 */
	@Override
	protected String getAdapterName() {
		
		return "DeviceDepreciationDetailHQL";
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	
}
