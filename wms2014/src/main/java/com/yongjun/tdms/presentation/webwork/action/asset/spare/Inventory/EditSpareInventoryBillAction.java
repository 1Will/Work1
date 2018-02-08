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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.pluto.service.security.WarehouseManager;
/**
 * <p>Title:EditSpareInventoryBillAction
 * <p>Description:盘点单页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class EditSpareInventoryBillAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
	private SpareInventory spareInventoryBill;
	private User user;
    private final SpareInventoryManager spareInventoryManager;           
    private final UserManager userManager;
    private final EventNewManager eventNewManager;
    private final WarehouseManager warehouseManager;
    
    private final CodeValueManager codeValueManager;
    private CodeValue storageGrade;
    private Warehouse warehouse;
    
    
    public EditSpareInventoryBillAction(SpareInventoryManager spareInventoryManager,
    		UserManager userManager,
    		EventNewManager eventNewManager,
    		WarehouseManager warehouseManager,
    		CodeValueManager codeValueManager){
    	this.spareInventoryManager=spareInventoryManager;
    	this.userManager=userManager;
    	this.eventNewManager = eventNewManager;
    	this.warehouseManager = warehouseManager;
    	this.codeValueManager = codeValueManager;
    }
	public void prepare() throws Exception {
		
		if(null==spareInventoryBill){
			if(this.hasId("spareInventoryBill.id")){
				spareInventoryBill=spareInventoryManager.loadSpareInventory(this.getId("spareInventoryBill.id"));
			}else{
				spareInventoryBill = new SpareInventory();
				//盘点人默认为当前登录人
				spareInventoryBill.setInventoryPeople(userManager.getUser());
			}
		}
	
		
	}
	
	public String save(){
		String sId = this.request.getParameter("storageGrade.id");
		String wId = this.request.getParameter("warehouse.id");
		if(!StringUtils.isEmpty(sId)){
			 storageGrade = this.codeValueManager.loadCodeValue(Long.parseLong(sId));
			 spareInventoryBill.setStorageGrade(storageGrade);
		}
		if(StringUtils.isEmpty(wId)){
			warehouse = this.warehouseManager.loadWarehouse(Long.parseLong(wId));
			spareInventoryBill.setWarehouse(warehouse);
		}
		  
		if (this.isSubmitRecord()){
			return submitRecord();
		}
		boolean isNew = this.spareInventoryBill.isNew();
		//获得对应的盘点人
		if(!StringUtils.isEmpty(request.getParameter("spareInventory.inventoryPeople.id"))){
			this.user = userManager.loadUser(this.getId("spareInventory.inventoryPeople.id"));
			spareInventoryBill.setInventoryPeople(user);
		}
		//获得选择的仓库
		if(this.hasId("warehouse.id")){
			this.spareInventoryBill.setWarehouse(this.warehouseManager.loadWarehouse(this.getId("warehouse.id")));
		}
		this.spareInventoryManager.storeSpareInventory(spareInventoryBill);
		
		String logContent = "";
		
		if(isNew){
			logContent = "被添加";
		}else{
			logContent = "被修改";
		}
		logContent = spareInventoryBill.getInventoryNo()+logContent;
		this.getLogger().logStore(spareInventoryBill, logContent);
		
		if (isNew) {
			this.addActionMessage(this.getText("spareInventory.add.success",
					Arrays.asList(new Object[] {spareInventoryBill.getInventoryNo()})));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareInventory.edit.success",
					Arrays.asList(new Object[] {spareInventoryBill.getInventoryNo()})));
			return SUCCESS;
		}
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	private boolean isSubmitRecord() {
		if(this.hasKey("submitRecord")){
			if (!StringUtils.isEmpty(request.getParameter("submitRecord"))) {
				return true;
			}
		}
		return false;
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1050表示入库提交,1051表示出库的提交,1052表示盘点的提交
	 * 2.Time
	 * 3.Status: 目前为0
	 * 4.OrgId: 目前为1
	 * 5.UserId
	 * 6.DocType:目前和EventType内容一致
	 * 7.DocId ：提交的多个报告ID,是字符串型
	 * 8.MoreInfo:一些相关信息
	 */
	public String submitRecord() {
		this.eventNewManager.storeEventNew_InvenTory(1052,
				Calendar.getInstance().getTime(),
				0,1,this.userManager.getUser().getId(),1000,(spareInventoryBill.getId()).intValue(),"");
		return SUCCESS;
	}
	public Warehouse getUserWarehouse(){
		Warehouse w = this.userManager.loadUser(this.userManager.getUser().getId()).getWarehouse();
		return w;
	}
	
	/**
	 * 获得仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouses(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		Warehouse warehouse = new Warehouse();
		warehouse.setId(-1L);
		warehouse.setName("");
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
	 * @function 获得所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
		List<Warehouse> wareList = new ArrayList<Warehouse>();
 
		List<Warehouse> list = new ArrayList<Warehouse>();
		Long userId=this.userManager.getUser().getId();
		User user=userManager.loadUser(userId);
		list.addAll(user.getWarehouses());
		for(Warehouse warehouse:list){
			if(!(warehouse.getDisabled())){
				wareList.add(warehouse);
			}
		}

		return wareList;
	}
	
	public User getLoginUser(){
		return this.userManager.getUser();
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SpareInventory getSpareInventoryBill() {
		return spareInventoryBill;
	}
	public void setSpareInventoryBill(SpareInventory spareInventoryBill) {
		this.spareInventoryBill = spareInventoryBill;
	}
 
 

}
