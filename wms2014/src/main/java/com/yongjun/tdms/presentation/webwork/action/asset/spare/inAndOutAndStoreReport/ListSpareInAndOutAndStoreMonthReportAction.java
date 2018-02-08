/*
 * YONGJUN-TEACHNOLOGY
 */
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inAndOutAndStoreReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreMonthReportType;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author yli-JohnSon
 * @Date 2009-4-21
 */
public class ListSpareInAndOutAndStoreMonthReportAction extends ValueListAction {

	/*
	 * YONGJUN-TEACHNOLOGY
	 */
	private static final long serialVersionUID = 1L;
	private final WarehouseManager warehouseManager;
	
	public ListSpareInAndOutAndStoreMonthReportAction(WarehouseManager warehouseManager){
		this.warehouseManager = warehouseManager;
	}
	
	public void prepare() throws Exception {

	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		if (request.getParameter("onlyCheck")!=null) {
			return "onlyCheckSpareInAndOutAndStoreAndMonthReports";
		}
		return "spareInAndOutAndStoreAndMonthReports";
	}
	// 获得类别
	public List<LabelValue> getToolDevFlag() {
		LabelValue[] arrays = this
				.wrapEnum(SpareInAndOutAndStoreMonthReportType.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
//	获得所有库区的集合
	public List<Regional> getAllRegional() {
		
		List<Regional> rs = new ArrayList<Regional>();
		Regional r = new Regional();
		r.setId(-1L);
		r.setName(this.getText("select.option.all"));
		rs.add(0, r);
		return rs;
	}
	//获得所有仓库的集合
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
	//获得权限仓库的集合
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
	
	//将库位号作为默认排序项
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
//		if(request.getParameter("locationCode")!=null && request.getParameter("locationCode")!=""){
//			map.put("locationCode",request.getParameter("locationCode"));
//		}else{
//			map.put("locationCode",null);
//		}
		return map;
	}
}
