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
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.accept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.AcceptResult;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
/**
 * 
 * <p>Title: EditAcceptToolingMakeDetail
 * <p>Description: 工装制造详细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:$
 */
public class EditAcceptToolingMakeDetail extends PrepareAction{
	private static final long serialVersionUID = 1L;
    private final AcceptBillManager acceptBillManager;
    private final AcceptBillDetailManager acceptBillDetailManager;
    private final ToolingTypeManager toolingTypeManager;
    private AcceptBill acceptBill;               //验收单对象
    private AcceptBillDetail toolingMakeDetail;   //验收单明细对象
    private final CodeValueManager codeValueManager;
    private final DepartmentManager departmentManager;
    private final UserManager  userManager;
    public EditAcceptToolingMakeDetail(AcceptBillManager acceptBillManager,AcceptBillDetailManager acceptBillDetailManager,
    		ToolingTypeManager toolingTypeManager,
    		CodeValueManager codeValueManager,DepartmentManager departmentManager,
    		UserManager  userManager){
    	this.acceptBillManager=acceptBillManager;
    	this.acceptBillDetailManager=acceptBillDetailManager;
    	this.toolingTypeManager=toolingTypeManager;
    	this.codeValueManager=codeValueManager;
    	this.departmentManager=departmentManager;
    	this.userManager=userManager;
    }
	public void prepare() throws Exception {
		if(this.hasId("acceptBill.id")){     //如果请求中包含"acceptBill.id",则根据"acceptBill.id"获取验收单对象
			this.acceptBill=this.acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
		}
		if (this.hasId("toolingMakeDetail.id")||null == toolingMakeDetail) {                //如果请求中包含"acceptBillDetail.id",则根据"acceptBillDetail.id"获取验收单明细对象
			if (this.hasId("toolingMakeDetail.id")) {
				this.toolingMakeDetail = this.acceptBillDetailManager.loadAcceptBillDetail(this.getId("toolingMakeDetail.id"));
			  }
		   else {
				  this.toolingMakeDetail = new AcceptBillDetail();
				}
	     }
	}
	
	public String save() {
		boolean isNew = this.toolingMakeDetail.isNew();
		if (this.hasId("category.id")) {      //设置工装类别
			ToolingType toolingType = this.toolingTypeManager.loadToolingType(this.getId("category.id"));
			toolingMakeDetail.setToolingCategory(toolingType);    //设置工装类别id
			toolingMakeDetail.setCategoryName(toolingType.getValue());    //设置工装类别名称
		}
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {//获得部门 
			Department det=this.departmentManager.loadDepartment(this.getId("department.id"));
			toolingMakeDetail.setDepartment(det);
			toolingMakeDetail.setDepartmentName(det.getName());
		}
		if (this.hasId("calUnit.id")) {      //设置工装计量单位
			toolingMakeDetail.setCalUnit(codeValueManager.loadCodeValue(this.getId("calUnit.id")));
		}
		//获得验收结果的值并保存
		if(!StringUtils.isEmpty(request.getParameter("result"))){
			if(request.getParameter("result").equals(AcceptResult.QUALIFIED.toString())){
				toolingMakeDetail.setResult(AcceptResult.QUALIFIED);
			}
			if(request.getParameter("result").equals(AcceptResult.DISQUALIFICATION.toString())){
				toolingMakeDetail.setResult(AcceptResult.DISQUALIFICATION);
			}
		}
		acceptBill.setSubmit(true);
		toolingMakeDetail.setAcceptBill(acceptBill);
		//如果新建工装制作明细 并且图号不为空
		if(toolingMakeDetail.getGraphNo()!=null){
			toolingMakeDetail.setJudageSave(true);
		}
		this.toolingMakeDetail.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);
		acceptBillDetailManager.storeAcceptBillDetail(toolingMakeDetail);
	 
		if (isNew) {
	            addActionMessage(getText("toolingMakeDetail.add.success",
	                    Arrays.asList(new Object[]{toolingMakeDetail.getGraphNo()})));
	            return NEW;
	        } else {
	                addActionMessage(getText("toolingMakeDetail.edit.success",
	                        Arrays.asList(new Object[]{toolingMakeDetail.getGraphNo()})));
	            return SUCCESS;
	        }
	}
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
	public List getCategorys() {
		return this.toolingTypeManager.loadAllToolingTypes();
	}
	/**
	 * 获取工装计量单位集合
	 * @return List 工装计量单位
	 */
	public List getCalUnits() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
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
	public List<LabelValue> getProcResults() {  //获得验收结果的枚举类型的值
		LabelValue[] arrays = this.wrapEnum(AcceptResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public AcceptBillDetail getToolingMakeDetail() {
		return toolingMakeDetail;
	}
	public void setToolingMakeDetail(AcceptBillDetail toolingMakeDetail) {
		this.toolingMakeDetail = toolingMakeDetail;
	}


}
