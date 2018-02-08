/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.spareLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * @author yli-JohnSon
 * @Date 2009-4-11
 */
public class ListOldSpareLocationAction extends ValueListAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2406081302051101961L;
	private final DepartmentManager departmentManager;
	private final SpareTypeManager spareTypeManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final WarehouseManager warehouseManager;
	private final SpareOutBillManager spareOutBillManager;
	private String inOutFlag;
	private String fromSpareInBillDetail="false";
	private boolean onlyHasStocks = true;
	private String roleWarehouseId;
	private SpareOutBill spareOutBill;
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
    newwarehouseManager;
	
	public ListOldSpareLocationAction(
			DepartmentManager departmentManager,
			SpareTypeManager spareTypeManager,
			SpareDetailTypeManager spareDetailTypeManager,
			WarehouseManager warehouseManager,
			SpareOutBillManager spareOutBillManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
		    newwarehouseManager){
		this.departmentManager = departmentManager;
		this.spareTypeManager = spareTypeManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.warehouseManager = warehouseManager;
		this.spareOutBillManager=spareOutBillManager;
		this.newwarehouseManager = newwarehouseManager;
	}
	
	
	@Override
	public void prepare() throws Exception {	
		if (null == this.inOutFlag) {
			if (!StringUtils.isEmpty(request.getParameter("inOutFlag"))) {
				this.inOutFlag = request.getParameter("inOutFlag");
			}
		}

		if (!StringUtils.isEmpty(request.getParameter("fromSpareInBillDetail"))) {
			this.fromSpareInBillDetail = request.getParameter("fromSpareInBillDetail");
			System.out.println("fromSpareInBillDetail="+fromSpareInBillDetail);
		}
		
		if(this.hasId("roleWarehouseId")){
			this.roleWarehouseId = request.getParameter("roleWarehouseId");
		}
		//setFirst(true);
	}
	
	@Override
	protected String getAdapterName() {
		return "oldSpareLocationSearcher";
	}
	
	/**
	 * 娑撶皭aluelist閹绘劒绶垫禒鎾崇氨閺屻儴顕楅弶鈥叉
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		
		if(null != this.inOutFlag && ""!=this.inOutFlag && ("-1").equals(request.getParameter("warehouse.id"))){
			List<Warehouse> list = this.getAllWarehouse();
			list.remove(0);
			map.put("warehouseIds",list);
			map.remove("warehouse.id");
		}
		if (this.isOnlyHasStocks()) {
			map.put("onlyHasStocks", true);
		}
		return map;
	}
	public List getDepartments() {
//		if (!this.userManager.getUser().isViewAll()) {           //婵″倹鐏夐悽銊﹀煕閸欘亝婀侀惇瀣拱闁劑妫惃鍕綀闂勶拷
//			List<Department> list = new ArrayList<Department>();
//			if (null == this.userManager.getUser().getDepartment()) {      //婵″倹鐏夐悽銊﹀煕娑撳秴鐫樻禍搴濇崲娴ｆ洟鍎撮梻锟�缂冾噣鍎撮梻鈫朌娑擄拷-2
//				Department department = new Department();
//				department.setId(Long.valueOf(-2L));
//				department.setName("");
//				list.add(department);
//			} else {
//				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
//			}
//			return list;
//		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
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
		spareTypeList.add(0,type);
		return spareTypeList;
	}
	
	/**
	 * 获取备件明细种类
	 * @return
	 */
	public List getSpareDetailType (){
		return spareDetailTypeManager.createSelectSpareDetailType(this.getText("select.option.all"));
	}
	
//	/**                                                                                                                                                                                                                                                                                
//	 * @function 閺嶈宓佽ぐ鎾冲閻劍鍩涢懢宄扮繁閹碉拷閺堝娼堥梽鎰波鎼达拷
//	 * @return                                                                                                                                  
//	 */
//	public List<Warehouse> getAllWarehouse(){
//		List<Warehouse> wareList = new ArrayList<Warehouse>();
//		Set<Warehouse> set=this.userManager.loadUser(this.getLoginUser().getId()).getWarehouses();
//		if(set.size() > 0){
//			wareList.addAll(set);
//		}
//		wareList = this.warehouseManager.loadAllWarehouse();
// 
//		Warehouse w = new Warehouse();
//		w.setId(-1L);
//		w.setName(this.getText("select.option.all"));
//		wareList.add(0, w);
//		
//		return wareList;
//	}
	/**
	 * @function 获得所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
//		List<Warehouse> wareList = new ArrayList<Warehouse>();
//		wareList.add(newwarehouseManager.loadWarehouse(Long.parseLong(roleWarehouseId)));
//		return wareList;
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
	/**
	 * @function 鐎规矮绠熸惔鎾冲隘闂嗗棗鎮庨敍灞间簰閸氬簼绮ㄦ惔鎾堕獓閼辨柧璐熼崗鎯扮ゴ閸婏拷
	 * @return
	 */
	public List<Regional> getAllRegional(){
		
		List<Regional> list = new ArrayList<Regional>();
		Regional reg = new Regional();
		reg.setId(-1L);
		reg.setName(this.getText("select.option.all"));
		list.add(0, reg);
		return list;
	}
    public User getLoginUser(){
    	return this.userManager.getUser();
    }
	public boolean isOnlyHasStocks() {
		return onlyHasStocks;
	}
	public void setOnlyHasStocks(boolean onlyHasStocks) {
		this.onlyHasStocks = onlyHasStocks;
	}
	public String getInOutFlag() {
		return inOutFlag;
	}
	public void setInOutFlag(String inOutFlag) {
		this.inOutFlag = inOutFlag;
	}

	public String getFromSpareInBillDetail() {
		return fromSpareInBillDetail;
	}
	public void setFromSpareInBillDetail(String fromSpareInBillDetail) {
		this.fromSpareInBillDetail = fromSpareInBillDetail;
	}
	

}
