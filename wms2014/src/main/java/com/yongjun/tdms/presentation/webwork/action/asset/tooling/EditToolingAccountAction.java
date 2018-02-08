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
package com.yongjun.tdms.presentation.webwork.action.asset.tooling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.ManagementLevel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeDetailManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.product.ProductManager;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * <p>Title: EditToolingAccountAction
 * <p>Description: 工装台长页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: EditToolingAccountAction.java 11286 2008-03-12 06:20:51Z wzou $
 */
public class EditToolingAccountAction extends PrepareAction{
	private static final long serialVersionUID = 1845156305621809532L;
	private final DeviceCardManager deviceCardManager;
	private final ToolingTypeManager toolingTypeManager;
	private final ToolingTypeDetailManager toolingTypeDetailManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final ProductManager productManager;
	private final SupplierManager supplierManager;
	private final UserManager userManager;
	private final ProductionLineManager productionLineManager;
	private final AcceptBillManager acceptBillManager;
	private final ApplicationDocManager applicationDocManager;
	private ApplicationDoc  applicationDoc;
	private DeviceCard tooling;
	private AcceptBill acceptBill;
	
	public EditToolingAccountAction(DeviceCardManager deviceCardManager,
			ToolingTypeManager toolingTypeManager,
			ToolingTypeDetailManager toolingTypeDetailManager,
			DepartmentManager departmentManager,
			CodeValueManager codeValueManager,
			ProductManager productManager,
			SupplierManager supplierManager,
			UserManager userManager,
			ProductionLineManager productionLineManager,
			AcceptBillManager acceptBillManager,
		ApplicationDocManager applicationDocManager) {
		this.deviceCardManager = deviceCardManager;
		this.toolingTypeManager = toolingTypeManager;
		this.toolingTypeDetailManager = toolingTypeDetailManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.productManager = productManager;
		this.supplierManager = supplierManager;
		this.userManager = userManager;
		this.productionLineManager = productionLineManager;
		this.acceptBillManager=acceptBillManager;
		this.applicationDocManager=applicationDocManager;
		
	}
	
	public void prepare() throws Exception {
		if (this.hasId("acceptBill.id")) {
			this.tooling = this.deviceCardManager.loadDeviceByAcceptBill(this.getId("acceptBill.id"));
		}
		if (null == this.tooling) {
			if (this.hasId("tooling.id")) {
				this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
			} else {
				this.tooling = new DeviceCard();
			}
		}
		if(null==this.acceptBill){
			if(this.hasId("acceptBill.id")){
				this.acceptBill=this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
				tooling.setSpecification(acceptBill.getSpecification());
				tooling.setModel(acceptBill.getModel());
			    tooling.setDepartment(acceptBill.getDepartment());
			    tooling.setNumber(Long.valueOf(acceptBill.getAmounts()));
			    tooling.setSupplier(acceptBill.getSupplier());
			    tooling.setName(acceptBill.getMerchandiseName());
			}
		}
	}
	/**
	 * 保存工装
	 * @return SUCCESS 或 NEW
	 */
	public String save() {
		boolean isNew = this.tooling.isNew();
		
		if (this.isEnabled()) {
			tooling.setEnabled(true);
		}
		if (!StringUtils.isEmpty(request.getParameter("product.id"))) {
				tooling.setProduct(this.productManager.loadProduct(this.getId("product.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("supplier.id"))) {
			tooling.setSupplier(this.supplierManager.loadSupplier(this.getId("supplier.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("desiger.id"))) {
			tooling.setToolingDesigner(this.userManager.loadUser(this.getId("desiger.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("checker.id"))) {
			tooling.setChecker(this.userManager.loadUser(this.getId("checker.id")));
		}
		
		if (!StringUtils.isEmpty(request.getParameter("manager.id"))) {
			tooling.setManager(this.userManager.loadUser(this.getId("manager.id")));
		}
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			tooling.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		
		if (!StringUtils.isEmpty(request.getParameter("calUnit.id"))) {
			tooling.setCalUnit(this.codeValueManager.loadCodeValue(this
					.getId("calUnit.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("productionLine.id"))) {
			tooling.setProductionLine(productionLineManager.loadProductionLine(this.getId("productionLine.id")));
		} else {
			tooling.setProductionLine(null);
		}
		
		if (!StringUtils.isEmpty(request.getParameter("toolingType.id"))) {
			tooling.setToolingType(this.toolingTypeManager.loadToolingType(this.getId("toolingType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("toolingTypeDetail.id"))) {
			tooling.setToolingTypeDetail(this.toolingTypeDetailManager.loadToolingTypeDetail(this.getId("toolingTypeDetail.id")));
		}
		tooling.setAcceptBill(acceptBill);
		this.deviceCardManager.storeTooling(tooling);
		if(acceptBill!=null){
			for(ApplicationDoc doc:acceptBill.getChangeDoc()){
			doc.setDevice(tooling);
			this.applicationDocManager.storeApplicationDoc(doc);
			}
		}

		
		if (isNew) {
			this.addActionMessage(this.getText("tooling.add.success", Arrays
					.asList(new Object[] { tooling.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("tooling.edit.success", Arrays
					.asList(new Object[] { tooling.getName() })));
			return SUCCESS;
		}
	}
	
	/**
	 * 判断是否点击有效按钮
	 * @return
	 */
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
	public DeviceCard getTooling() {
		return tooling;
	}
	
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	
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
		return departmentManager.createSelectDepartments(StringUtils.EMPTY);
	}
	
	public List getToolingTypes() {
		return this.toolingTypeManager.loadAllToolingTypes();
	}
	
	public List getToolingTypeDetails() {
		return this.toolingTypeDetailManager.loadAllToolingTypeDetails();
	}
	
	/**
	 * 获取管理级别的所有值
	 * @return
	 */
	public List<LabelValue> getManagementLevels() {
		LabelValue [] arrays = this.wrapEnum(ManagementLevel.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**
	 * 获取计量单位的所有值
	 * @return
	 */
	public List<CodeValue> getCalcUnits() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}
	
	/**
	 * 获取产品型号的所有值
	 * @return List 产品型号的集合
	 */
	public List<Product> getProductModels() {
		return this.productManager.loadAllProducts();
	}
	/**
	 * 获取部门的所有值
	 * @return
	 */
	public List getAllDepartments() {
		return departmentManager.createSelectDepartments(StringUtils.EMPTY);
	}
	public List getProductionLines() {
		return productionLineManager.createSelectProductionLines(StringUtils.EMPTY);
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public AcceptBill getAcceptBill() {
		return acceptBill;
	}

	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}

	public ApplicationDoc getApplicationDoc() {
		return applicationDoc;
	}

	public void setApplicationDoc(ApplicationDoc applicationDoc) {
		this.applicationDoc = applicationDoc;
	}
}
