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
package com.yongjun.tdms.presentation.webwork.action.asset.device.deviceOccursHistory;

import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

/**
 * <p>Title: ListPreRepairPlanAction
 * <p>Description: 设备预防性维修控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author wliu@yj-technology.com
 * @version $Id: ListPreRepairPlanAction.java 2009-09-29 9:48:27 wliu$
 */
public class ListPreRepairAction
extends ValueListAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 设备预防性维修控制类无参构造函数
	 */
	public ListPreRepairAction() {
		
	}
	
	/**
	 * 默认调用方法
	 */
	@Override
	public void prepare() throws Exception {

		this.setFirst(false);
	}

	/**
	 * 执行调用的方法
	 */
	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}

	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(request.getParameter("deviceId") != null){
			map.put("deviceId",this.getId("deviceId"));
		}
		return map;
	}

	/**
	 * 调用valueList配置文件关联的hql语句
	 */
	@Override
	protected String getAdapterName() {
		
		return "PreRepairHQL";
	}
	
}
