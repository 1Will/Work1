package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class ListPurchaseDtlBillSelectorAction extends ValueListAction{

	
	private static final long serialVersionUID = 1L;
	private final PurchaseBillManager  purchaseBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private final DepartmentManager departmentManager;
    private List<PurchaseBillDetail> purchaseBillDtls; //获取采购单明细集合
    private PurchaseBillDetail purchaseBillDtl;        //采购单明细对象
    private PurchaseBill purchaseBill;
    private String toolingDevFlag;//工装和设备的标识
    public ListPurchaseDtlBillSelectorAction(PurchaseBillManager  purchaseBillManager,PurchaseBillDetailManager purchaseBillDetailManager,
    		DepartmentManager departmentManager){
    	this.purchaseBillManager=purchaseBillManager;
    	this.purchaseBillDetailManager=purchaseBillDetailManager;
    	this.departmentManager=departmentManager;
    }
    
    public void prepare() {
		if (null == this.purchaseBillDtl) {
			if (this.hasId("purchaseBillDtl.id")) {
				this.purchaseBillDtl = this.purchaseBillDetailManager.loadPurchaseBillDetail(getId("purchaseBillDtl.id"));
			
			} 
		}
		if (this.purchaseBillDtls == null && this.hasIds("purchaseBillDtlIds")) {
            this.purchaseBillDtls = this.purchaseBillDetailManager.loadPurchaseBillDetails(this.getIds("purchaseBillDtlIds"));
        }
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
		}
		
	}
    /**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
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
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	@Override
	protected String getAdapterName() {
		 if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			   return "choosePurchases";
			} else {
				return "toolingPurchaseBills";
	}
		
}
	
	public PurchaseBillDetail getPurchaseBillDtl() {
		return purchaseBillDtl;
	}
	public void setPurchaseBillDtl(PurchaseBillDetail purchaseBillDtl) {
		this.purchaseBillDtl = purchaseBillDtl;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

}
