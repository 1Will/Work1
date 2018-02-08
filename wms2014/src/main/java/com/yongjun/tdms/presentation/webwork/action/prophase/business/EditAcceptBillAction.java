package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;

import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
@SuppressWarnings("serial")
public class EditAcceptBillAction extends PrepareAction{
	
	private static final long serialVersionUID = 1L;
	private final AcceptBillManager acceptBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final SupplierManager supplierManager;
	private final EventNewManager eventNewManager;
	
	private  PurchaseBill purchaseBill;     //采购单对象
	private Supplier supplier;
    private AcceptBill acceptBill;          //获得验收单对象
    private PurchaseBillDetail purchaseBillDtl;//获得采购单明细的对象
    private String toolingDevFlag;             //标识[工装][设备]
    public EditAcceptBillAction( AcceptBillManager acceptBillManager,PurchaseBillDetailManager purchaseBillDetailManager
    		,DepartmentManager departmentManager,UserManager userManager,SupplierManager supplierManager,
    		 EventNewManager eventNewManager){
    	  this.acceptBillManager=acceptBillManager;
    	  this.purchaseBillDetailManager=purchaseBillDetailManager;
    	  this.departmentManager=departmentManager;
    	  this.userManager=userManager;
    	  this.supplierManager=supplierManager;
    	  this.eventNewManager = eventNewManager;
    	
    }
  
	public void prepare() throws Exception {
		 if(this.acceptBill==null){
			 if(this.hasId("acceptBill.id")){     //通过页面传来的验收单的id获得验收单对象
				 this.acceptBill=acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
				 
				 if(!this.hasId("supplier.id")){
					 this.supplier=acceptBill.getSupplier();
				 }
			 } else {
				 acceptBill=new AcceptBill();
				 acceptBill.setAcceptPeople(userManager.getUser());
			 }
		 }
		 
//		if (this.hasId("supplier.id")) {//通过页面传来的供应商的id获得供应商对象
//			this.supplier = this.supplierManager.loadSupplier(getId("supplier.id"));
//		} 
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
		}
  }
	
	public String save() {
		if (this.isSubmitRecord()){
			
			return submitRecord();
		}
		boolean isNew = acceptBill.isNew();
		 
//        if (!StringUtils.isEmpty(request.getParameter("acceptPeople.id"))) {     //获取验收人
//    	  acceptBill.setAcceptPeople(this.userManager.loadUser(this.getId("acceptPeople.id")));
//		}
        if (!StringUtils.isEmpty(request.getParameter("acceptBill.people.id"))) {     //获取验收人
      	  acceptBill.setAcceptPeople(this.userManager.loadUser(this.getId("acceptBill.people.id")));
  		}
        if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {     //获取供应商
      	  acceptBill.setSupplier(this.supplierManager.loadSupplier(this.getId("supplier.id")));
  		}
        
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {     //获取责任单位
			acceptBill.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (this.hasId("purchaseBillDtl.id")) {//通过页面传来的采购单明细的id获得采购明细单对象
			this.purchaseBillDtl = this.purchaseBillDetailManager.loadPurchaseBillDetail(getId("purchaseBillDtl.id"));
	        //设置关联的采购单明细
			acceptBill.setPurchaseBillDtl(this.purchaseBillDtl);
			//设置关联的采购单
			acceptBill.setPurchaseBill(this.purchaseBillDtl.getPurchaseBill());
		} 
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			acceptBill.setToolingDevFlag(SysModel.DEVICE);
		}else{
			acceptBill.setToolingDevFlag(SysModel.TOOLING);
		}
		//设置关联的供应商
		acceptBill.setSupplier(supplier);

		acceptBillManager.storeAcceptBill(acceptBill);
		
		 if (isNew) {
			addActionMessage(getText("acet.add.success", Arrays
					.asList(new Object[] { acceptBill.getAcceptBillNo() })));
			return NEW;
		} else {
			addActionMessage(getText("acet.edit.success", Arrays
					.asList(new Object[] { acceptBill.getAcceptBillNo() })));
			return SUCCESS;
		}
	}
	
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
	private boolean isSubmitRecord() {
		if(this.hasKey("submitRecord")){
			if (!StringUtils.isEmpty(request.getParameter("submitRecord"))) {
				return true;
			}
		}
		return false;
	}
	/*
	 * 提交记录，storeEventNew的参数
	 * 1.EventType ：1055表示验收提醒
	 * 2.Time
	 * 3.Status: 目前为0
	 * 4.OrgId: 目前为1
	 * 5.UserId
	 * 6.DocType:目前和EventType内容一致
	 * 7.DocId ：提交的多个报告ID,是字符串型
	 * 8.MoreInfo:一些相关信息
	 */
	public String submitRecord() {
		this.eventNewManager.storeEventNew_Accept(1055,
				Calendar.getInstance().getTime(),
				0,1,this.userManager.getUser().getId(),1000,(acceptBill.getId()).intValue(),"");
		return SUCCESS;
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
    
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public PurchaseBillDetail getPurchaseBillDtl() {
		return purchaseBillDtl;
	}
	public void setPurchaseBillDtl(PurchaseBillDetail purchaseBillDtl) {
		this.purchaseBillDtl = purchaseBillDtl;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
}
