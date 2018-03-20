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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inWareHouse;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title:ListSpareInWareHouseHistoryAction
 * <p>Description:备件入库历史页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListSpareInWareHouseHistoryAction.java 2008-11-13 16:15:45 yli$
 */
public class ListOldSpareInWareHouseHistoryAction extends ValueListAction {
	private static final long serialVersionUID = -4575343920655074847L;
	
	private Spare spare;
	private Warehouse warehouse;
	private final SpareManager spareManager;
	private final WarehouseManager warehouseManager; 
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager;
	
	public ListOldSpareInWareHouseHistoryAction(SpareManager spareManager,WarehouseManager warehouseManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager  newwarehouseManager){
		this.spareManager = spareManager;
		this.warehouseManager = warehouseManager;
		this.newwarehouseManager =newwarehouseManager;
	}
	
	@Override
	public void prepare() throws Exception {
		if(spare == null){
			if(this.hasId("spare.id")){
				this.spare = spareManager.loasSpare(this.getId("spare.id"));
			}
		}
		if(warehouse == null){
			if(this.hasId("warehouse.id")){
				this.warehouse = warehouseManager.loadWarehouse(this.getId("warehouse.id"));
			}
		}
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "oldspareInWareHouseHistorys";
	}

	public Spare getSpare() {
		return spare;
	}
	
	public Warehouse getWarehouse(){
		return warehouse;
	}
	
	/**
	 * @function 获得所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		User user = this.userManager.getUser();
		List<Warehouse> allWa = null;
		try {
			allWa = newwarehouseManager.loadByUser(user.getId());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Warehouse  ws :allWa){
			if(ws.getOldWarehouse()!=null&&ws.getOldWarehouse().equals("Y")){
				wareList.add(ws);
			}
			
		}
		return wareList;
	}

}