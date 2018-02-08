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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.spareWareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.spareWareHouse.SpareWareHouse;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;

/**
 * <p>Title: ListSpareWareHouseAction
 * <p>Description: 备件库总台帐页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class ListOldSpareWareHouseAction extends ValueListAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2238478010575962485L;
	
	private final SpareWareHouseManager spareWareHouseManager;
	private final SpareTypeManager spareTypeManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	
	private String allSpareWareHouseIds;
	private String allSpareWareHouseMinStocks;
	
	private SpareWareHouse spareWareHouse;
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
    newwarehouseManager;
	private boolean onlyLessMinStock=false;
	private boolean onlyEmptyMinStock=false;
	
	public ListOldSpareWareHouseAction(SpareWareHouseManager spareWareHouseManager, SpareTypeManager spareTypeManager,
			SpareDetailTypeManager spareDetailTypeManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
		    newwarehouseManager) {
		this.spareWareHouseManager = spareWareHouseManager;
		this.spareTypeManager = spareTypeManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.newwarehouseManager = newwarehouseManager;
	}
	
	@Override
	public void prepare() throws Exception {
		
		
	}
	
	@Override
	public String execute() throws Exception {
		//System.out.println("=============" + "execute");
		if (this.isSave()) {
			//System.out.println("=============" + "save");
			return saveMinStocks();
		}
		//System.out.println("========" + onlyLessMinStock);
		return SUCCESS;
	}
	 public String listOfLessMinStocks()
     throws Exception {
		 super.list();
		 this.setOnlyLessMinStock(true);
		 return SUCCESS;
	 }

	public String saveMinStocks(){
		if(!StringUtils.isEmpty(request.getParameter("allSpareWareHouseIds"))){
			this.allSpareWareHouseIds = request.getParameter("allSpareWareHouseIds");
		}
		//System.out.println("========= allSpareWareHouseIds " + allSpareWareHouseIds);
	
		if(!StringUtils.isEmpty(request.getParameter("allSpareWareHouseMinStocks"))){
			this.allSpareWareHouseMinStocks = request.getParameter("allSpareWareHouseMinStocks");
		}
		//System.out.println("========= allSpareWareHouseMinStocks " + allSpareWareHouseMinStocks);
		this.spareWareHouseManager.storeSpareWareHouse(allSpareWareHouseIds, allSpareWareHouseMinStocks);
		return SUCCESS;
	}
	/**
	 * 获取备件种类
	 */
	@SuppressWarnings("unchecked")
	public List getSpareType() {
		List allSpareTypeList = new ArrayList();
		List spareTypeList = new ArrayList();
		allSpareTypeList =spareTypeManager.loadAllSpareType();
		for (int i=0;i<allSpareTypeList.size();i++){       //区分是工装[TOOLING]或设备[DEVICE]
			spareTypeList.add(allSpareTypeList.get(i));
		}
		SpareType type = new SpareType();
		type.setId(Long.valueOf(-1L));
		type.setName(this.getText("select.option.all"));
		allSpareTypeList.add(0,type);
		return allSpareTypeList;
	}
	/**
	 * 获取备件明细种类
	 * @return
	 */
	public List getSpareDetailType (){
		return spareDetailTypeManager.createSelectSpareDetailType(this.getText("select.option.all"));
	}
	/**
	 * @function 获得所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		Warehouse w = new Warehouse();
		w.setId(-1l);
		w.setName("所有");
		wareList.add(w);
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
	@Override
	protected String getAdapterName() {
		return "spareOldWareHouse";
	}
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		
		if(("-1").equals(request.getParameter("warehouse.id"))){
			List<Warehouse> list = this.getAllWarehouse();
			list.remove(0);
			map.put("roleWarehouseIds",list);
			map.remove("warehouse.id");
		}
		
		if(request.getParameter("warehouse.id")==null){
			List<Long>  warehouseIds = new ArrayList<Long>();
			warehouseIds.add(-1L);
			//获取登陆用户所属权限仓库ID值集合
			if(null != getAllWarehouse()){
				for(Warehouse warehouse:getAllWarehouse()){
					warehouseIds.add(warehouse.getId());
				}
			}
			map.put("roleWarehouseIds",warehouseIds);
			map.remove("warehouse.id");
			
		}
		boolean aa=this.isOnlyLessMinStock();
		if (this.isOnlyLessMinStock()){
			
			map.put("onlyLessMinStock",true);
		}
		String only1=request.getParameter("category.code");
		String only2=request.getParameter("warehouse.id");
		String only3=request.getParameter("spareNo");
		String only4=request.getParameter("spareName");
		String onlyMix=request.getParameter("modelSpecs");
		String onlyMix1=request.getParameter("onlyLessMinStock");
		
		
		return map;
	}
	public SpareWareHouse getSpareWareHouse() {
		return spareWareHouse;
	}

	public void setSpareWareHouse(SpareWareHouse spareWareHouse) {
		this.spareWareHouse = spareWareHouse;
	}
	/**
	 * 判断页面是否保存安全库存
	 * @return true | false
	 */
	private boolean isSave() {
		if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		   }
		}
		return false;
	}

	public boolean isOnlyLessMinStock() {
		return onlyLessMinStock;
	}

	public void setOnlyLessMinStock(boolean onlyLessMinStock) {
		this.onlyLessMinStock = onlyLessMinStock;
	}

	public boolean isOnlyEmptyMinStock() {
		return onlyEmptyMinStock;
	}

	public void setOnlyEmptyMinStock(boolean onlyEmptyMinStock) {
		this.onlyEmptyMinStock = onlyEmptyMinStock;
	}

}
