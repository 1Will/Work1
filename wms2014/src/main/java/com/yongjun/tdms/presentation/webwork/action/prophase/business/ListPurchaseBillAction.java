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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;



import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.model.prophase.business.PurchaseTypeStatus;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * @author qs
 * @version $Id: 申购单查询Action.java 11328 2008-03-15 09:39:30Z mwei $
 */
@SuppressWarnings("serial")
public class ListPurchaseBillAction extends ValueListAction {

	
	private final DepartmentManager departmentManager;
	private final SupplierManager supplierManager;
	private final PurchaseBillManager purchaseBillManager;
	private List<PurchaseBill> purchases;
	private Supplier supplier;//采购单所关联的供应商
	private String toolingDevFlag;//工装和设备的标识
	
    public ListPurchaseBillAction(DepartmentManager departmentManager,PurchaseBillManager purchaseBillManager,
			 SupplierManager supplierManager){
		 this.departmentManager=departmentManager;
		 this.purchaseBillManager=purchaseBillManager;
		 this.supplierManager=supplierManager;
	 }
	public void prepare(){
		//first=true;
		if(null==this.supplier) {//获得供应商的对象
			  if(this.hasId("supplier.id")){
				  this.supplier=this.supplierManager.loadSupplier(this.getId("supplier.id"));
			  }
		}
		if (this.purchases == null && this.hasIds("purchaseBillIds")) {
	        this.purchases = this.purchaseBillManager.loadPurchaseBills(this.getIds("purchaseBillIds"));
	      }
		
		if(this.hasId("toolingDevFlag")){//判断从页面传来的toolingDevFlag是否有值
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
//			 System.out.println("11111"+toolingDevFlag);
		}
	}

	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		if(this.isDelete()){
			this.delete();
		}
		return SUCCESS;
	}
	
	public String delete(){
		this.purchaseBillManager.deleteAllPurchaseBills(purchases);
		this.addActionMessage(this.getText("purchasebill.delete.success"));
		return SUCCESS;
	}
	
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
	
	public String disabled() {
		try {
			this.purchaseBillManager.disabledAllPurchases(purchases);
		} catch (Exception e) {
			if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
				this.addActionMessage(this.getText("purchases.disabled.failer"));
				return ERROR;
			}else{
				this.addActionMessage(this.getText("purchasescontract.disabled.failer"));
				return ERROR;
			}
			
		}
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			this.addActionMessage(this.getText("purchases.disabled.success"));
			return SUCCESS;
		}else{
			this.addActionMessage(this.getText("purchasescontract.disabled.success"));
			return SUCCESS;
		}
		
	}

	public String enabled() {
		this.purchaseBillManager.enabledAllPurchasers(purchases);
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			this.addActionMessage(this.getText("purchases.enabled.success"));
			return SUCCESS;
		}else{
			this.addActionMessage(this.getText("purchasescontract.enabled.success"));
			return SUCCESS;
		}
		
	}
	
	//获得采购单状态
	public List<LabelValue> getPurchaseStatus() {
		LabelValue[] arrays = this.wrapEnum(PurchaseBillStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	//获得采购单类型
	public List<LabelValue> getPurTypeStatus() {
		LabelValue[] arrays = this.wrapEnum(PurchaseTypeStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		if(toolingDevFlag.equals("DEVICE")){
			tmp.remove(arrays[1]);
		}else{
			tmp.remove(arrays[2]);
		}
		return tmp;
	}
	
	//获得验收单所有部门
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	@Override
	protected String getAdapterName() {
        if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			return "devicePurchaseBills";
		} else {
			return "TolingPurchaseBills";
		}
	
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public List<PurchaseBill> getPurchases() {
		return purchases;
	}
	public void setPurchases(List<PurchaseBill> purchases) {
		this.purchases = purchases;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		//是否可以查看其他部门
//		if(!this.getLoginUser().isViewAll()){
//			 map.put("department.id",this.getLoginUser().getDepartment().getId());
//		}
		if(!this.getLoginUser().isViewAll()){
			if (!this.hasId("department.id")){
				map.put("department.id",this.getLoginUser().getDepartment().getId());
			}
		}
	  return map;
	}

}
