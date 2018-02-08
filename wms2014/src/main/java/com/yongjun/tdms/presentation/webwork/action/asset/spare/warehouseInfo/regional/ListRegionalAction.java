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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.regional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.WarehouseManager;

/**
 * <p>
 * Title:ListRegionalAction
 * <p>
 * Description:ListRegionalAction
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
public class ListRegionalAction
extends ValueListAction {
	private static final long serialVersionUID = 0L;

	private final RegionalManager regionalManager;

	private final WarehouseManager warehouseManager;
	private final CodeValueManager codeValueManager;
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager 
	newwarehouseManager;

	private List<Regional> regionals;

	//	登陆用户所属权限仓库ID值集合
	private List<Long> warehouseIds = new ArrayList<Long>();
	// 登陆用户
	private User loginUser;
	
	public ListRegionalAction(RegionalManager regionalManager,
	WarehouseManager warehouseManager,
	CodeValueManager codeValueManager,com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager 
	newwarehouseManager) {
		this.regionalManager = regionalManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager =codeValueManager;
		this.newwarehouseManager = newwarehouseManager;
	}

	@Override
	protected String getAdapterName() {
		return "RegionalHQL";
	}

	@Override
	public void prepare() throws Exception {
		if (this.hasIds("regionalIds")) {
			this.regionals = this.regionalManager.loadAllRegionals(this
			.getIds("regionalIds"));
		}
		else {
			this.regionals = new ArrayList<Regional>();
		}
		
		//	加默认权限仓库，解决权限仓库空值sql出错问题
//		warehouseIds.add(-1L);
		//	获取登陆用户所属权限仓库ID值集合
//		User user = this.userManager.loadUser(this.userManager.getUser().getId());
		List<Warehouse> wlist=this.getAllWarehouse();
		if(null != wlist){
			for(Warehouse warehouse:wlist){
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

	private String enabled() {
		try {
			this.regionalManager.enabledAllRegional(regionals);
			this.addActionMessage(this.getText("regional.enabled.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("regional.enabled.error"));
			return ERROR;
		}
	}

	private String disabled() {
		try {
			this.regionalManager.disabledAllRegional(regionals);
			this.addActionMessage(this.getText("regional.disabled.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("regional.disabled.error"));
			return ERROR;
		}
	}

	private String delete() {
		try {
			this.regionalManager.deleteAllRegional(regionals);
			this.addActionMessage(this.getText("regional.delete.success"));
			return SUCCESS;
		}
		catch (Exception e) {
			this.addActionMessage(this.getText("regional.delete.error"));
			return ERROR;
		}
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}
	
	/**
	 * 仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
//		List<Warehouse> wareList = new ArrayList<Warehouse>();
////		wareList = this.warehouseManager.loadAllWarehouse();
//		Warehouse w = new Warehouse();
//		w.setId(-1l);
//		w.setName(this.getText("allOptions"));
//		wareList.add(0,w);
//		return wareList;
		User user = this.userManager.loadUser(this.userManager.getUser().getId());
		List<Warehouse> whrlist = null;
		try {
			Warehouse warehouse = new Warehouse();
			warehouse.setId(-1L);
			warehouse.setName(this.getText("select.option.all"));
			whrlist = newwarehouseManager.loadByUserAndNew(user.getId());
			whrlist.add(0, warehouse);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return whrlist;
	}
	
	

	/**
	 * 获得用户的所有仓库
	 * 功过用户获得用户的所属仓库和权限仓库
	 * 
	 * @return
	 * @author hjia
	 * @since 1.0
	 */
	public List<Warehouse> getAllWarehouseName(){
		//通过用户获得该用户的所属仓库。
		List<Warehouse> wareList = new ArrayList<Warehouse>();
//		Warehouse ware = this.userManager.getUser().getWarehouse();
//		if(null != ware){
//			Warehouse w = this.warehouseManager.loadWarehouse(ware.getId());
//			wareList.add(w);
//		}
		//获得用户的所有权限仓库。
		List<Warehouse> list = new ArrayList<Warehouse>();
		Long userId=this.userManager.getUser().getId();
		User user=userManager.loadUser(userId);
		list.addAll(user.getWarehouses());
		if(list.size()>0){
			wareList.addAll(list);
		}
		
		Warehouse warehouse = new Warehouse();
		warehouse.setId(-1L);
		warehouse.setName(this.getText("select.option.all"));
		wareList.add(0, warehouse);
		
		return wareList;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != warehouseIds) {
			map.put("warehouseIds", warehouseIds);
		}
		return map;
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
