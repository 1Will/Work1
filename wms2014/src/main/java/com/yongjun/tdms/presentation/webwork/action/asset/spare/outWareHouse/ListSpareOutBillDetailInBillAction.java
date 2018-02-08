package com.yongjun.tdms.presentation.webwork.action.asset.spare.outWareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
 
 
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.location.LocationManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillDetailManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: ListSpareOutBillDetailInBill
 * <p>Description: 出库单明细选择</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListSpareOutBillDetailInBill 9149 2010-10-28 06:29:38Z smzhang $
 */
public class ListSpareOutBillDetailInBillAction extends ValueListAction {

	private final DepartmentManager departmentManager;
	private final SpareManager spareManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final SpareTypeManager spareTypeManager;
	
	private final WarehouseManager warehouseManager;
	private final CodeValueManager codeValueManager;
	private final LocationManager locationManager;
	private final SpareOutBillDetailManager  spareOutBillDtlmanager;
	
	public ListSpareOutBillDetailInBillAction(DepartmentManager departmentManager,
			SpareManager spareManager,
			SpareTypeManager spareTypeManager,
			SpareDetailTypeManager spareDetailTypeManager,
			WarehouseManager warehouseManager,
			CodeValueManager codeValueManager,
			LocationManager locationManager,
			SpareOutBillDetailManager  spareOutBillDtlmanager){
		
		this.departmentManager = departmentManager;
		this.spareManager =spareManager;
		this.spareTypeManager =spareTypeManager;
		this.spareDetailTypeManager =spareDetailTypeManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager =codeValueManager;
		this.locationManager =locationManager;
		this.spareOutBillDtlmanager = spareOutBillDtlmanager;
	}
	
	
	public void prepare(){
		
	}
	
	
	
	@Override
	protected String getAdapterName() {
		return "seechSpareOutbillDateil";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		//是否可以查看其他部门
//		if(!this.getLoginUser().isViewAll()){
//			 map.put("department.id",this.getLoginUser().getDepartment().getId());
//		}
		if (this.hasId("outWarehouseId")){
			map.put("outWarehouseId",this.getId("outWarehouseId"));
		}
		if (this.hasId("wareHouse.id")){
			map.put("wareHouse.id",this.getId("wareHouse.id"));
		}
	  return map;
	}
	
	/***
	 * 获得仓库
	 * @return
	 */
	public List<Warehouse> getWareHouses(){
		List<Warehouse> list = new ArrayList<Warehouse>();
		list = this.warehouseManager.loadAllValidWarehouse();
		Warehouse house = new Warehouse();
		house.setId(-1l);
		house.setName(this.getText("select.option.all"));
		list.add(0,house);
		return list;
	}
	/**
	 * 获得库位
	 * @return
	 */
	public List<Location> getLocations(){
		List<Location> list = new ArrayList<Location>();
		 
		Location location = new Location();
		location.setId(-1l);
		location.setCode(this.getText("select.option.all"));
		return list;
	}
	
	/**
	 * 获得部门
	 * @return
	 */
	public List<Department> getAllDepartment(){
		List<Department> list = new ArrayList<Department>();
		list = this.departmentManager.loadAllDepartments();
		Department dept = new Department();
		dept.setId(-1l);
		dept.setName(this.getText("select.option.all"));
		list.add(0,dept);
		
		return list;
	}
	
	/**
	 * 获取备件大分类
	 */
	@SuppressWarnings("unchecked")
	public List getSpareType() {
		 
		List spareTypeList = new ArrayList();
		spareTypeList =spareTypeManager.loadAllSpareType();
		SpareType type = new SpareType();
		type.setId(Long.valueOf(-1L));
		type.setName(this.getText("select.option.all"));
		spareTypeList.add(0,type);
		return spareTypeList;
		
	}
	
	/**
	 * 获取备件明细分类
	 * @return
	 */
	public List getSpareDetailType (){
		
		List<SpareDetailType> list = new ArrayList<SpareDetailType>();
		list = this.spareDetailTypeManager.loadAllSpareDetailTypes();
		SpareDetailType type = new SpareDetailType();
		type.setId(-1l);
		type.setName(this.getText("select.option.all"));
		list.add(0, type);
		
		return list;
	}

	

}
