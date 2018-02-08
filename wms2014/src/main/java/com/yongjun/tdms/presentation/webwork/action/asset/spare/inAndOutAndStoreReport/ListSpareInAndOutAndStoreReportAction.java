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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inAndOutAndStoreReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReport;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportType;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.asset.spare.inAndOutAndStoreReport.SpareInAndOutAndStoreReportManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>
 * Title: ListSpareInAndOutAndStoreReportAction
 * <p>
 * Description: 收发存报表访问控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author wzou@yj-technology.com
 * @version $Id: ListSpareInAndOutAndStoreReportAction.java 9887 2009-03-10
 *          03:11:36Z xschen $
 */
public class ListSpareInAndOutAndStoreReportAction extends ValueListAction {
	private static final long serialVersionUID = 1L;

	private SpareInAndOutAndStoreReport spareInAndOutAndStoreReport;
	

	private final SpareInAndOutAndStoreReportManager spareInAndOutAndStoreReportManager;// 收发存报表服务接口类
	private final DepartmentManager departmentManager;
	private final WarehouseManager warehouseManager;

	public ListSpareInAndOutAndStoreReportAction(
			SpareInAndOutAndStoreReportManager spareInAndOutAndStoreReportManager,
			DepartmentManager departmentManager,
			WarehouseManager warehouseManager) {
		this.spareInAndOutAndStoreReportManager = spareInAndOutAndStoreReportManager;
		this.departmentManager = departmentManager;
		this.warehouseManager = warehouseManager;
	}

	public void prepare() throws Exception {
		this.setFirst(true);
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		if (request.getParameter("onlyCheck")!=null) {
			return "spareInAndOutAndStoreReportOnly";
		}
		return "spareInAndOutAndStore";
	}

	public SpareInAndOutAndStoreReport getSpareInAndOutAndStoreReport() {
		return spareInAndOutAndStoreReport;
	}

	public void setSpareInAndOutAndStoreReport(
			SpareInAndOutAndStoreReport spareInAndOutAndStoreReport) {
		this.spareInAndOutAndStoreReport = spareInAndOutAndStoreReport;
	}

	// 获得类别
	public List<LabelValue> getToolDevFlag() {
		LabelValue[] arrays = this
				.wrapEnum(SpareInAndOutAndStoreReportType.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	// 获取列表
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
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
		if(request.getParameter("locationCode")!=null && request.getParameter("locationCode")!=""){
			//map.put("locationCode",this.getId("locationCode"));
			map.put("locationCode",request.getParameter("locationCode"));
		}else{
			map.put("locationCode",null);
		}
		return map;
	}
}
