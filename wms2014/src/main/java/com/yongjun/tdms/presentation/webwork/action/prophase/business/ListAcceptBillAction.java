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
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
/**
 * @author sq
 * @version $Id: ListAcceptBillAction.java 11137 2008-03-15 15:41:36Z xschen $
 */
public class ListAcceptBillAction extends ValueListAction{
  
	private static final long serialVersionUID = 1L;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final SupplierManager supplierManager;
	private final AcceptBillManager acceptBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private Supplier supplier;           //验收单所关联的供应商
    private List<AcceptBill> acceptBills;    //验收单集合
    private  PurchaseBillDetail purchaseBillDtl;  //验收单所关联的采购单明细的对象
    private String toolingDevFlag;           //标识[工装][设备] 
    
    public ListAcceptBillAction(SupplierManager supplierManager,DepartmentManager departmentManager,
    		 AcceptBillManager acceptBillManager,PurchaseBillDetailManager purchaseBillDetailManager,UserManager userManager){
    	this.supplierManager=supplierManager;
    	this.departmentManager=departmentManager;
    	this.acceptBillManager=acceptBillManager;
    	this.purchaseBillDetailManager=purchaseBillDetailManager;
    	this.userManager=userManager;
    }
public void prepare(){
		
		if(null==this.supplier) {//获得供应商的对象
			  if(this.hasId("supplier.id")){
				  this.supplier=this.supplierManager.loadSupplier(this.getId("supplier.id"));
			  }
		}
		if(null==this.purchaseBillDtl) {//获采购单明细对象
			  if(this.hasId("purchaseBillDtl.id")){
				  this.purchaseBillDtl=this.purchaseBillDetailManager.loadPurchaseBillDetail(this.getId("purchaseBillDtl.id"));
			  }
		}
		if (this.acceptBills == null && this.hasIds("acceptBillIds")) {
	        this.acceptBills = this.acceptBillManager.loadAcceptBills(this.getIds("acceptBillIds"));
	      }
		if(this.hasId("toolingDevFlag")){//判断从页面传来的toolingDevFlag是否有值
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
		}
	}
    //验收单失效的方法
   public String disabled() {
	     this.acceptBillManager.disabledAllAcceptBills(acceptBills);
	     this.addActionMessage(this.getText("acet.dis.success"));
	     return SUCCESS;
    }
   //验收单有效的方法
   public String enabled() {
	    this.acceptBillManager.enabledAllAcceptBills(acceptBills);
	    this.addActionMessage(this.getText("acet.enab.success"));
	  return SUCCESS;
   }
   
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		return SUCCESS;
	}
	
	private boolean isEnabled() {
		return this.hasKey("enabled");
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
		
		 if ((SysModel.DEVICE.toString().equals(toolingDevFlag))) {
				return "deviceAccepts";
			} else {
				return "toolingAccepts";
			}
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public PurchaseBillDetail getPurchaseBillDtl() {
		return purchaseBillDtl;
	}
	public void setPurchaseBillDtl(PurchaseBillDetail purchaseBillDtl) {
		this.purchaseBillDtl = purchaseBillDtl;
	}

}
