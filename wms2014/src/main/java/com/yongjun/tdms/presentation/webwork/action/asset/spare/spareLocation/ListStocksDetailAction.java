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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.spareLocation;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;


/**
 * <p>Title: ListSpareDetailAction
 * <p>Description: 备件明细台帐页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class ListStocksDetailAction extends ValueListAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final SpareLocationManager spareLocationManager;
	private final SpareManager spareManager;
	private final WarehouseManager warehouseManager;
	
	
	private SpareLocation spareLocation;
	
	public ListStocksDetailAction(SpareLocationManager spareLocationManager,SpareManager spareManager,WarehouseManager warehouseManager){
		this.spareLocationManager = spareLocationManager;
		this.spareManager = spareManager;
		this.warehouseManager=warehouseManager;
	}
	
	@Override
	public void prepare() throws Exception {		
		if (this.hasId("spare.id")) {
			if(null!=this.spareLocationManager.loadByKey("spare", this.getId("spare.id"))){							
				this.spareLocation=this.spareLocationManager.loadByKey("spare", this.getId("spare.id")).get(0);	
			}		
		}		
		else{
			this.spareLocation=new 	SpareLocation();
		}
		setFirst(true);		
	}

	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	/**
	 * 获得用户的所有仓库
	 * 功过用户获得用户的所属仓库和权限仓库
	 * 	
	 */
	//	获得所有仓库的集合
	public List<Warehouse> getAllWarehouse() {
		List<Warehouse> ws = this.warehouseManager.loadAllValidWarehouse();
		Warehouse w = new Warehouse();
		w.setId(-1L);
		w.setName(this.getText("select.option.all"));
		ws.add(0, w);
		return ws;
	}
	
	
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
	
		if(null!=this.getId("spare.id")){			
			map.put("spareid", this.getId("spare.id"));	
		}else{
			map.put("spareid", null);
		}
		if(null!=request.getParameter("warehouse.id")){			
			map.put("warehouse", request.getParameter("warehouse.id"));	
		}else{
			map.put("warehouse", null);
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "locationDetal";
	}



	public SpareLocation getSpareLocation() {
		return spareLocation;
	}

	public void setSpareLocation(SpareLocation spareLocation) {
		this.spareLocation = spareLocation;
	}

	

}
