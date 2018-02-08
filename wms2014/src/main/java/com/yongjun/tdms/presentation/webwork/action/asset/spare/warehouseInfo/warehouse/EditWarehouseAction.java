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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.warehouseInfo.warehouse;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * <p>
 * Title:EditWarehouseAction
 * <p>
 * Description:EditWarehouseAction
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
public class EditWarehouseAction
extends PrepareAction {

	private static final long serialVersionUID = 1L;

	private final WarehouseManager warehouseManager;

	private final UserManager userManager;
	private final CodeValueManager codeValueManager;

	private Warehouse warehouse;

	public EditWarehouseAction(final WarehouseManager warehouseManager,
	final UserManager userManager,
	CodeValueManager codeValueManager) {
		this.warehouseManager = warehouseManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		//System.out.println(this.request.getParameter("storageGrade.id"));
		//System.out.println("warehouse.id" + this.request.getParameter("warehouse.id"));
		if (this.hasId("warehouse.id")) {
			this.warehouse = this.warehouseManager.loadWarehouse(this
			.getId("warehouse.id"));
		}
		else {
			this.warehouse = new Warehouse();
			this.warehouse.setUser(this.userManager.getUser());
		}
	}

	public String save() {
		//boolean isNew = this.warehouse.isNew();
		boolean isNew = (this.warehouse.getId()==null);
		//System.out.println("isNew"+isNew + "  " + (this.warehouse.getId()==null) +"id:" +this.warehouse.getId());
		if(this.hasId("wareh.user.id")){
			this.warehouse.setUser(this.userManager.loadUser(this.getId("wareh.user.id")));
		}
		String storageGradeId = this.request.getParameter("storageGrade.id");
		if(null != storageGradeId){
			this.warehouse.setStorageGrade(this.codeValueManager.loadCodeValue(Long.valueOf(storageGradeId)));
		}
		try{		
		if(isNew){
			if(warehouseManager.loadByKey("code", warehouse.getCode())!=null){
				this.addActionMessage(this.getText("warehouse.add.error",
						Arrays.asList(new Object[] { this.warehouse.getCode() })));
			}
			else{
				this.warehouse.setOldWarehouse("N");
				this.warehouseManager.storeWarehouse(this.warehouse);
				//创建新建库的同时生产对应的旧件库
				Warehouse oldWarehouse = new Warehouse();
				oldWarehouse.setAddress(warehouse.getAddress());
				oldWarehouse.setCode("JJ-" + warehouse.getCode());
				oldWarehouse.setComment(warehouse.getComment());
				oldWarehouse.setCreatedTime(warehouse.getCreatedTime());
				oldWarehouse.setCreator(warehouse.getCreator());
				oldWarehouse.setDisabled(warehouse.getDisabled());
				oldWarehouse.setFatherId(warehouse.getId());
				oldWarehouse.setFax(warehouse.getFax());
				oldWarehouse.setLastModifiedTime(warehouse.getLastModifiedTime());
				oldWarehouse.setLastOperator(warehouse.getLastOperator());
				oldWarehouse.setName("JJ-" + warehouse.getName());
				oldWarehouse.setOldWarehouse("Y");
				oldWarehouse.setOrganization(warehouse.getOrganization());
				oldWarehouse.setPostcode("JJ-" + warehouse.getPostcode());
				oldWarehouse.setStorageGrade(warehouse.getStorageGrade());
				oldWarehouse.setTel(warehouse.getTel());
				oldWarehouse.setUser(warehouse.getUser());
//				oldWarehouse.setUsers(warehouse.getUsers());
				oldWarehouse.setVersion(0l);
				this.warehouseManager.storeWarehouse(oldWarehouse);
				this.addActionMessage(this.getText("warehouse.add.success",
						Arrays.asList(new Object[] { this.warehouse.getCode() })));
			}
			return NEW;
		}else{
			this.warehouseManager.storeWarehouse(this.warehouse);
			this.addActionMessage(this.getText("warehouse.edit.success",
			Arrays.asList(new Object[] { this.warehouse.getCode() })));
			return SUCCESS;
		}
		}catch(Exception e){
			if(isNew){
				this.addActionMessage(this.getText("warehouse.add.error",
				Arrays.asList(new Object[] { this.warehouse.getCode() })));
			}else{
				this.addActionMessage(this.getText("warehouse.edit.error",
				Arrays.asList(new Object[] { this.warehouse.getCode() })));
			}
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

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
