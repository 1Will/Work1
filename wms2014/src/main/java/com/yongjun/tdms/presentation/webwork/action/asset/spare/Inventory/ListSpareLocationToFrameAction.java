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
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryDetailManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;

/**
 * <p>Title:ListSpareLocationToFrameAction
 * <p>Description:选择备件库台账</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class ListSpareLocationToFrameAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private SpareInventory spareInventoryBill;
 								//页面信息的回传
	private final SpareInventoryDetailManager spareInventoryDetailManager;
	private final SpareInventoryManager spareInventoryManager;
	private final UserManager userManager;
	
	public ListSpareLocationToFrameAction(SpareInventoryDetailManager spareInventoryDetailManager,
			SpareInventoryManager spareInventoryManager,
			UserManager userManager){
		this.spareInventoryDetailManager=spareInventoryDetailManager;
		this.spareInventoryManager=spareInventoryManager;
		this.userManager = userManager;
	}
	@Override
	public void prepare(){
		if(this.hasId("spareInventoryBill.id")){
			this.spareInventoryBill = spareInventoryManager.loadSpareInventory(this.getId("spareInventoryBill.id"));
		}
	 
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	  
 
 
	/**
	 * 将spareInBill.id的值放入到spareInBillDtl的valuelist中去
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
       // map.put("spareInventoryBill.id",this.getId("spareInventoryBill.id"));
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "chooseSpareLocations";
	}
	
	public User getLoginUser(){
		return this.userManager.getUser();
	}
	public SpareInventory getSpareInventoryBill() {
		return spareInventoryBill;
	}
	public void setSpareInventoryBill(SpareInventory spareInventoryBill) {
		this.spareInventoryBill = spareInventoryBill;
	}
 

}
