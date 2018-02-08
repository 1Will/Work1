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
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.model.prophase.business.PurchaseTypeStatus;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;

/**
 * @author qs
 * @version $Id: EditPurchaseBillAction.java 11328 2008-03-15 09:39:30Z mwei $
 */
@SuppressWarnings("serial")
public class EditPurchaseBillAction extends PrepareAction {
	
	private final PurchaseBillManager purchaseBillManager;
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	private final BudgetDetailManager budgetDetailManager;
	private final EventNewManager eventNewManager;
	
	private String newAddPurchaseSupplierName;                        //新添加到采购单中的供应商姓名
	private String newAddPurchaseSupplierTelphone;                    //新添加到采购单中的供应商联系方式
	private PurchaseBill purchaseBill;                                //采购单的对象
	private CodeValue feeSource;                                      // 费用来源
	private BudgetDetail budgetDetail;              //预算明细
	private Supplier supplier;                                        //采购单所关联的供应商
	private String toolingDevFlag;                                     //标识[工装][设备]
	private String report;//是从报表管理 进入标识
	
	
	public EditPurchaseBillAction(PurchaseBillManager purchaseBillManager, SupplierManager supplierManager,
			CodeValueManager codeValueManager,DepartmentManager departmentManager
			,UserManager userManager,BudgetDetailManager budgetDetailManager,EventNewManager eventNewManager) {
		this.purchaseBillManager = purchaseBillManager;
		this.supplierManager = supplierManager;
		this.codeValueManager=codeValueManager;
		this.departmentManager=departmentManager;
		this.userManager=userManager;
		this.budgetDetailManager = budgetDetailManager;
		this.eventNewManager = eventNewManager;
		
	}
	public void prepare() throws Exception {
		if (null == this.purchaseBill) {
			if (this.hasId("purchaseBill.id")) { //保存时获得采购单对象
				this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
				
				if (!this.hasId("supplier.id")) {
					this.supplier = this.purchaseBill.getSupplier();
				}
				if (request.getParameter("budgetDetail.id")==null) {
					this.budgetDetail = this.purchaseBill.getBudgetDetail();
				}
			} else {
				this.purchaseBill = new PurchaseBill();
				purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
				purchaseBill.setBuyer(userManager.getUser());
			}
		}
		if (this.hasId("toolingDevFlag")) {
			this.toolingDevFlag = request.getParameter("toolingDevFlag");
			
		}
		report = this.request.getParameter("report");
	}
	public String save() {
		if (this.isSubmitRecord()){
			
			return submitRecord();
		}
		boolean isNew = purchaseBill.isNew();
		//获得采购单的类型
		if(!StringUtils.isEmpty(request.getParameter("typeStatus"))){    
			if(request.getParameter("typeStatus").equals("DEVICE")){	//获得设备类型
				purchaseBill.setTypeStatus(PurchaseTypeStatus.DEVICE);
			}
			if(request.getParameter("typeStatus").equals("SPARE")){		//获得备件类型
				purchaseBill.setTypeStatus(PurchaseTypeStatus.SPARE);
			}
			if(request.getParameter("typeStatus").equals("TOOLING")){	//获得工装类型
				purchaseBill.setTypeStatus(PurchaseTypeStatus.TOOLING);
			}
		}
	
		if(!StringUtils.isEmpty(request.getParameter("status"))){		 //获得采购单的状态
			if (request.getParameter("status").equals("NEWSTATUS")) {
				purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
			}
			if (request.getParameter("status").equals("INSPECTING")) {
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTING);
			}
			if (request.getParameter("status").equals("INSPECTED")) {
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTED);
			}
		}
//		if(!StringUtils.isEmpty(request.getParameter("status"))){		 //获得申购单的状态
//			if (request.getParameter("status").equals("NEWSTATUS")) {
//				purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
//			}
//		}
		if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {     //获取采购人
			String supplierId = request.getParameter("supplier.id").replace(",", "");
			purchaseBill.setSupplier(this.supplierManager.loadSupplier(Long.parseLong(supplierId)));
		}
		if (!StringUtils.isEmpty(request.getParameter("buyer.id"))) {     //获取采购人
			//System.out.println("11111111:"+request.getParameter("buyer.id"));
			purchaseBill.setBuyer(this.userManager.loadUser(this.getId("buyer.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {     //获取责任单位
			purchaseBill.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("sourceType"))) {   //获取费用来源
			purchaseBill.setFeeSource(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("sourceType").trim()));
		}else{
			purchaseBill.setFeeSource(null);
		}
		
		//移除该预算编号关联的采购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
			//如果已付金额存在，则从预算中已发生费用减去已付金额
			if (null != purchaseBill.getAlreadyPayOut()) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(purchaseBill.getBudgetNo(),purchaseBill.getAlreadyPayOut());
			}
		}
		
		//设置关联的预算明细
		if (!StringUtils.isEmpty(request.getParameter("budgetDetail.id"))) {        //如果为预算内，则置关联的预算明细核预算编号
			BudgetDetail budgetDetail = this.budgetDetailManager.loadBudgetDetail(this.getId("budgetDetail.id"));
			purchaseBill.setBudgetDetail(budgetDetail);                    //设置关联的预算明细
			purchaseBill.setBudgetNo(budgetDetail.getBudgetNo());          //设置预算编号
		} else {                                                                   //如果为预算内，则置空预算明细和预算编号
			purchaseBill.setBudgetDetail(null);
			purchaseBill.setBudgetNo(null);
		}
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			purchaseBill.setToolingDevFlag(SysModel.DEVICE);
		}else{
			purchaseBill.setToolingDevFlag(SysModel.TOOLING);
		}
		purchaseBillManager.storePurchaseBill(purchaseBill);
		 if (isNew) {
			 if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
				 addActionMessage(getText("purchaseBill.add.success", Arrays
							.asList(new Object[] { purchaseBill.getBillNo() })));
					return NEW;
			 }else{
				 addActionMessage(getText("purchaseBillCon.add.success", Arrays
							.asList(new Object[] { purchaseBill.getBillNo() })));
					return NEW;
			 }
			
		} else {
			 if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
				 addActionMessage(getText("purchaseBill.edit.success", Arrays
							.asList(new Object[] { purchaseBill.getBillNo() })));
					return SUCCESS;
			 }else{
				 addActionMessage(getText("purchaseBillCon.edit.success", Arrays
							.asList(new Object[] { purchaseBill.getBillNo() })));
					return SUCCESS;
			 }
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
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
	/**
	 * 类型选择
	 * @return
	 */
	public List<LabelValue> getPurchaseTypes() {  
		LabelValue[] arrs = this.wrapEnum(PurchaseTypeStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrs.length; i++) {
			tmp.add(arrs[i]);
		}
		if(toolingDevFlag.equals("DEVICE")){
			tmp.remove(arrs[1]);
		}else{
			tmp.remove(arrs[2]);
		}
		return tmp;
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
	 * 1.EventType ：1050表示入库提交,1051表示出库的提交
	 * 2.Time
	 * 3.Status: 目前为0
	 * 4.OrgId: 目前为1
	 * 5.UserId
	 * 6.DocType:目前和EventType内容一致
	 * 7.DocId ：提交的多个报告ID,是字符串型
	 * 8.MoreInfo:一些相关信息
	 */
	public String submitRecord() {
		if(!StringUtils.isEmpty(request.getParameter("status"))){		 //获得采购单的状态
			if (request.getParameter("status").equals("NEWSTATUS")) {
				purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
			}
			if (request.getParameter("status").equals("INSPECTING")) {
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTING);
			}
			if (request.getParameter("status").equals("INSPECTED")) {
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTED);
			}
		}
		this.eventNewManager.storeEventNew_Purchase(1053,
				Calendar.getInstance().getTime(),
				0,1,this.userManager.getUser().getId(),1000,(purchaseBill.getId()).intValue(),"");
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
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	/**
	 * 获取所有费用来源的类型集合
	 * @return List 费用来源的类型集合
	 */
	
	public List getFeeSourceTypes() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.FEE_SOURCE_TYPE);
		
	}
	
	/**
	 * 获取费用来源
	 * @return
	 */
	public String getSourceType() {
		if(null != purchaseBill && null!=purchaseBill.getFeeSource()){
			return this.purchaseBill.getFeeSource().getRealCode();
		}
		return  null;
		
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public String getNewAddPurchaseSupplierName() {
		return newAddPurchaseSupplierName;
	}
	public void setNewAddPurchaseSupplierName(String newAddPurchaseSupplierName) {
		this.newAddPurchaseSupplierName = newAddPurchaseSupplierName;
	}
	public String getNewAddPurchaseSupplierTelphone() {
		return newAddPurchaseSupplierTelphone;
	}
	public void setNewAddPurchaseSupplierTelphone(
			String newAddPurchaseSupplierTelphone) {
		this.newAddPurchaseSupplierTelphone = newAddPurchaseSupplierTelphone;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public BudgetDetail getBudgetDetail() {
		return budgetDetail;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
}
