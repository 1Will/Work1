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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
 
/**
 * 
 * <p>Title:ListSpareInventoryBillAction
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListSpareInventoryBillAction.java 2008-11-13 15:18:44 yli$
 */
public class ListSpareInventoryBillAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private List<SpareInventory>  spareInventorys;
	//	登陆用户所属权限仓库ID值集合
	private List<Long> warehouseIds = new ArrayList<Long>();
    private final SpareInventoryManager spareInventoryManager;  
    private final WarehouseManager warehouseManager;
    private final CodeValueManager codeValueManager;
    private final UserManager userManager;
    private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager;
    
    public ListSpareInventoryBillAction(SpareInventoryManager spareInventoryManager,
    		WarehouseManager warehouseManager,
    		CodeValueManager codeValueManager,
    		 UserManager userManager,com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager){
    	this.spareInventoryManager=spareInventoryManager;
    	this.warehouseManager = warehouseManager;
    	this.codeValueManager = codeValueManager;
    	this.userManager = userManager;
    	this.newwarehouseManager=newwarehouseManager;
    }
	public void prepare() throws Exception {
		if (this.spareInventorys == null && this.hasIds("spareInventoryIds")) {
			this.spareInventorys = this.spareInventoryManager.loadAllSpareInventorys(this
					.getIds("spareInventoryIds"));
		}
		//	获取登陆用户所属权限仓库ID值集合
		User user = this.userManager.loadUser(this.userManager.getUser().getId());
		List<Warehouse> whrlist = newwarehouseManager.loadByUserAndNew(user.getId());
		if(null != whrlist){
			for(Warehouse warehouse:whrlist){
				warehouseIds.add(warehouse.getId());
			}
		}
	}
	
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return this.enabled();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 获得仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouses(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		Warehouse warehouse = new Warehouse();
		warehouse.setId(-1L);
		warehouse.setName("所有");
		list.add(warehouse);
		return list;
	}
	
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}
	
	
	/**
	 * 失效所有的盘点单
	 * @return
	 */
	public String disabled() {
		this.spareInventoryManager.disabledAllInventoryBill(spareInventorys);
		this.addActionMessage(this.getText("spareInventory.disabled.success"));
		return SUCCESS;
	}
    /**
     *  有效所有的盘点单
     * @return
     */
	public String enabled() {
		this.spareInventoryManager.enabledAllInventoryBill(spareInventorys);
		this.addActionMessage(this.getText("spareInventory.enabled.success"));
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		return "spareInventorys";
	}
	/**
	 * 为valuelist提供仓库查询条件
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != warehouseIds) {
			map.put("warehouseIds", warehouseIds);
		}
		return map;
	}
	public User getLoginUser(){
		return this.userManager.getUser();
	}
	public List<SpareInventory> getSpareInventorys() {
		return spareInventorys;
	}
	public void setSpareInventorys(List<SpareInventory> spareInventorys) {
		this.spareInventorys = spareInventorys;
	}
	
	
 

}
