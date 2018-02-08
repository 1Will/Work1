/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * <p>
 * Title:ListLocationAction
 * <p>
 * Description:模态窗口库位查询
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version
 */
public class ListLocationAction extends ValueListAction {
	
	private final RegionalManager regionalManager;

	private final WarehouseManager warehouseManager;

	private final CodeValueManager codeValueManager;
	private final LocationManager locationManager;
	private List<Location> locations ;
	private String fromSpareInBillDetail="false";
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager;
	//	登陆用户所属权限仓库ID值集合
	private List<Long> warehouseIds = new ArrayList<Long>();
	//  登陆用户
	private User loginUser;

	public ListLocationAction(final RegionalManager regionalManager, 
			final WarehouseManager warehouseManager, 
			final CodeValueManager codeValueManager, 
			final LocationManager locationManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager) {
		this.regionalManager = regionalManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager = codeValueManager;
		this.locationManager = locationManager;
		this.newwarehouseManager=newwarehouseManager;
	}
	
	@Override
	public void prepare() throws Exception {
		if (!StringUtils.isEmpty(request.getParameter("fromSpareInBillDetail"))) {
			this.fromSpareInBillDetail = request.getParameter("fromSpareInBillDetail");
		}
		if (this.hasIds("locationIds")) {
			this.locations = this.locationManager.loadAllLocations(this.getIds("locationIds"));
		}
		else {
			this.locations = new ArrayList<Location>();
		}
		
		//	加默认权限仓库，解决权限仓库空值sql出错问题
		//warehouseIds.add(-1L);
		//	获取登陆用户所属权限仓库ID值集合
//		User user = this.userManager.loadUser(this.userManager.getUser().getId());
//		if(null != user.getWarehouses()){
//			for(Warehouse warehouse:user.getWarehouses()){
//				warehouseIds.add(warehouse.getId());
//			}
//		}
		User user = this.userManager.loadUser(this.userManager.getUser().getId());
		List<Warehouse> whrlist = newwarehouseManager.loadByUserAndNew(user.getId());
		if(null != whrlist){
			for(Warehouse warehouse:whrlist){
				warehouseIds.add(warehouse.getId());
			}
		}
	}
	@Override
	public String execute() throws Exception {
		if (this.isDelete()) {
			return delete();
		}
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
 	@Override
	protected String getAdapterName() {
		return "newLocation";
	}
	
	/**
	 * 为valuelist提供仓库查询条件
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		List<Warehouse> list = this.RoleWarehouses();
		map.put("warehouse",request.getParameter("warehouse"));
		
		if (null != warehouseIds) {
			map.put("warehouseIds", warehouseIds);
		}
		return map;
	}


	//有效记录
	private String enabled() {
		try {
			this.locationManager.enabledAllLocation(locations);
			this.addActionMessage(this.getText("location.enabled.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("location.enabled.error"));
			return ERROR;
		}
	}

	private String disabled() {
		try {
			this.locationManager.disabledAllLocation(locations);
			this.addActionMessage(this.getText("location.disabled.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("location.disabled.error"));
			return ERROR;
		}
	}

	private String delete() {
		try {
			this.locationManager.deleteAllLocation(locations);
			this.addActionMessage(this.getText("location.delete.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("location.delete.error"));
			return ERROR;
		}
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List<CodeValue> getAllStorageGrade(){
		List<CodeValue> list=this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
		CodeValue c=new CodeValue();
		c.setId(-1L);
		c.setValue(this.getText("select.option.all"));
		list.add(0,c);
		return list;
	}
	//获得所有库区的集合
	public List<Regional> getAllRegional() {
		
		List<Regional> rs = new ArrayList<Regional>();
		Regional r = new Regional();
		r.setId(-1L);
		r.setName(this.getText("select.option.all"));
		rs.add(0, r);
		return rs;
	}
	/* hjia 2010-05-18 */
	//获得所有仓库的集合
	public List<Warehouse> getAllWarehouseName() {
//		通过用户获得该用户的所属仓库。
		List<Warehouse> wareList = new ArrayList<Warehouse>();
//		Warehouse ware = this.userManager.getUser().getWarehouse();
//		if(null != ware){
//			Warehouse w = this.warehouseManager.loadWarehouse(ware.getId());
//			wareList.add(w);
//		}
		//获得用户的所有权限仓库。
//		Long userId=userManager.getUser().getId();
//		User user=userManager.loadUser(userId);
//		List<Warehouse> list = new ArrayList<Warehouse>();
//		list.addAll(user.getWarehouses());
//		if(list.size()>0){
//			wareList.addAll(list);
//		}
		wareList = this.warehouseManager.loadAllWarehouse();
		Warehouse warehouse = new Warehouse();
		warehouse.setId(-1L);
		warehouse.setName(this.getText("所有"));
		wareList.add(0, warehouse);
		
		return wareList;
	}
	//获得权限仓库的集合
	public List<Warehouse> RoleWarehouses(){
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		Warehouse ware = this.userManager.getUser().getWarehouse();
		if(null != ware){
			Warehouse w = this.warehouseManager.loadWarehouse(ware.getId());
			wareList.add(w);
		}
		List<Warehouse> list = new ArrayList<Warehouse>();
		list.addAll(this.userManager.getUser().getWarehouses());
		if(list.size()>0){
			wareList.addAll(list);
		}
		return wareList;
	}
	//获得所有库位类型的集合
	public List<CodeValue> getAllLocationType() {
		List<CodeValue> cvs = this.codeValueManager
		.LoadAllValuesByCode(CodeConstants.LOCAL_TYPE);
		CodeValue cv = new CodeValue();
		cv.setId(-1L);
		cv.setValue(this.getText("select.option.all"));
		if (cvs == null) {
			cvs = new ArrayList<CodeValue>();
		}
		cvs.add(0, cv);
		return cvs;
	}
	//获得载重类型的集合
	public List<CodeValue> getAllBearload() {
		List<CodeValue> cvs = this.codeValueManager
		.LoadAllValuesByCode(CodeConstants.BEARLOAD);
		CodeValue cv = new CodeValue();
		cv.setId(-1L);
		cv.setValue(this.getText("select.option.all"));
		if (cvs == null) {
			cvs = new ArrayList<CodeValue>();
		}
		cvs.add(0, cv);
		return cvs; 
	}
	
	
	public String getFromSpareInBillDetail() {
		return fromSpareInBillDetail;
	}
	public void setFromSpareInBillDetail(String fromSpareInBillDetail) {
		this.fromSpareInBillDetail = fromSpareInBillDetail;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

}
