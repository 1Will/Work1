package com.yongjun.tdms.presentation.webwork.action.asset.spare.inventoryAndCleanHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportType;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * @author chengding
 * @Date 04/22/2009
 */
public class ListSpareInventoryAndCleanHouseReportAction extends
		ValueListAction {

	private static final long serialVersionUID = 1L;

	private final DepartmentManager departmentManager;
	private final WarehouseManager warehouseManager;

	public ListSpareInventoryAndCleanHouseReportAction(
			DepartmentManager departmentManager,
			WarehouseManager warehouseManager) {
		this.departmentManager = departmentManager;
		this.warehouseManager = warehouseManager;
	}

	@Override
	protected String getAdapterName() {
		System.out.println(request.getParameter("onlyCheck"));
		return (request.getParameter("onlyCheck") != null) ? "onlyCheckInventoryAndCleanHouseReports"
				: "inventoryAndCleanHouseReports";
	}

	// 鑾峰緱绫诲埆鍒楄〃
	public List<LabelValue> getToolDevFlag() {
		LabelValue[] arrays = this
				.wrapEnum(SpareInAndOutAndStoreReportType.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> temp = new ArrayList<LabelValue>();
		temp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			temp.add(arrays[i]);
		}
		System.out.println("*********  " + temp.size());
		return temp;
	}

	// 鑾峰彇閮ㄩ棬鍒楄〃
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
//	鑾峰緱鎵�鏈夊簱鍖虹殑闆嗗悎
	public List<Regional> getAllRegional() {
		
		List<Regional> rs = new ArrayList<Regional>();
		Regional r = new Regional();
		r.setId(-1L);
		r.setName(this.getText("select.option.all"));
		rs.add(0, r);
		return rs;
	}
	//鑾峰緱鎵�鏈変粨搴撶殑闆嗗悎
	public List<Warehouse> getAllWarehouseName() {
		List<Warehouse> ws = this.warehouseManager.loadAllValidWarehouse();
		Warehouse w = new Warehouse();
		w.setId(-1L);
		w.setName(this.getText("select.option.all"));
		if (ws == null) {
			ws = new ArrayList<Warehouse>();
		}
		ws.add(0, w);
		return ws;
	}
	//鑾峰緱鏉冮檺浠撳簱鐨勯泦鍚�
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

	/**
	 * @function 鏍规嵁褰撳墠鐢ㄦ埛鑾峰緱鎵�鏈夋潈闄愪粨搴�
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
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
		Warehouse w = new Warehouse();
		w.setId(-1L);
		w.setName(this.getText("select.option.all"));
		wareList.add(0, w);
		return wareList;
	}
	
	//将库位号作为默认排序项
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(request.getParameter("locationCode")!=null && request.getParameter("locationCode")!=""){
			//map.put("locationCode",this.getId("locationCode"));
			map.put("locationCode",request.getParameter("locationCode"));
		}else{
			map.put("locationCode",null);
		}
		return map;
	}
	
}
