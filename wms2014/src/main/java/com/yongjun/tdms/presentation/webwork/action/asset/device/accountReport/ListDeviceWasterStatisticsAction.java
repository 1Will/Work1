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

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceType;
//import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
//import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
 
/**
 * <p>Title: ListDeviceWasterStatisticsAction
 * <p>Description: 设备报废统计访问控制类
 * <p>Copyright: Copyright (c) 2001 yj-technology
 * <p>Company: www.yj-technology.com
 * @author wliu@yj-technology.com
 * @version $Id: ListDeviceWasterStatisticsAction.java 2009-09-23 14:20:51Z wliu $
 */
public class ListDeviceWasterStatisticsAction
extends ValueListAction {

	private static final long serialVersionUID = 1L;

	// 设备管理业务逻辑接口
	//private final DeviceCardManager deviceCardManager;
	// 设备类型业务逻辑接口
	private final DeviceTypeManager deviceTypeManager;
	// 分公司业务逻辑接口
	private final FilialeManager filialeManager;
	// 部门业务逻辑接口
	private final DepartmentManager departmentManager;
	// 供应商业务逻辑接口
	//private final SupplierManager supplierManager;

	
	/**
	 * 设备报废统计访问控制类构造函数 
	 * 注入设备类型业务逻辑接口，分公司业务逻辑接口，部门业务逻辑接口
	 * @param deviceTypeManager
	 * @param filialeManager
	 * @param departmentManager
	 */
	public ListDeviceWasterStatisticsAction(
			DeviceTypeManager deviceTypeManager, 
			FilialeManager filialeManager,
			DepartmentManager departmentManager) {
		this.deviceTypeManager = deviceTypeManager;
		this.filialeManager = filialeManager;
		this.departmentManager = departmentManager;
	}

	/**
	 * 默认调用方法
	 */
	@Override
	public void prepare() throws Exception {
		
		this.setFirst(true);
	
	}
	
	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(request.getParameter("deviceType.id")!=null){
			map.put("deviceType.id",this.getId("deviceType.id"));
		}
		if(request.getParameter("filiale.id")!=null){
			map.put("filiale.id",this.getId("filiale.id"));
		}
		if(request.getParameter("department.id")!=null){
			map.put("department.id",this.getId("department.id"));
		}
		
		return map;
	}
	
	/**
	 * 将查询条件添加到valueList中，以便通过valueList的方式查询数据
	 */
	@Override
	protected String getAdapterName() {
		
		return "DeviceWasterStatisticsHQL";
	}

	/**
	 * 获取所有设备类型信息
	 * @return List 设备类型集合
	 */
	public List<DeviceType> getAllDeviceTypes(){
		
		List<DeviceType> deviceTypes = deviceTypeManager.loadAllDeviceTypes();
		DeviceType dt = new DeviceType();
		dt.setId(Long.valueOf(-1L));
		dt.setName(this.getText("select.option.all"));
		deviceTypes.add(0,dt);
		return deviceTypes;
	}
	
	/**
	 * 获取所有分公司信息
	 * @return List 分公司集合
	 */
	public List<Filiale> getAllFiliales(){
		
		List<Filiale> filiales = filialeManager.loadAllFiliale();
		Filiale filiale = new Filiale();
		filiale.setId(Long.valueOf(-1L));
		filiale.setName(this.getText("select.option.all"));
		filiales.add(0,filiale);
		return filiales;
	}
	
	/**
	 * 获取所有部门信息
	 * @return List 部门集合
	 */
	public List<Department> getAllDepartments(){
		
		List<Department> departments = departmentManager.loadAllDepartments();
		Department department = new Department();
		department.setId(Long.valueOf(-1L));
		department.setName(this.getText("select.option.all"));
		departments.add(0,department);
		return departments;
	}

}
