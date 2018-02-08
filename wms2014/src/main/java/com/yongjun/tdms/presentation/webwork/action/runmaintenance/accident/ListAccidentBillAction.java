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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.accident;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.accident.AccidentBill;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.accident.AccidentBillManager;

/**
 * <p>Title: ListAccidentBillAction
 * <p>Description: 事故单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListAccidentBillAction.java 9149 2007-12-09 06:29:38Z qsun $
 */
public class ListAccidentBillAction extends ValueListAction {
	private static final long serialVersionUID = 5475881407533631619L;
	private final AccidentBillManager accidentBillManager;
	private final DepartmentManager departmentManager;
	private List<AccidentBill> toolingAccidentBills;
	private String toolingDevFlag;
	
	public ListAccidentBillAction(AccidentBillManager accidentBillManager, 
			                  DepartmentManager departmentManager) {
		this.accidentBillManager = accidentBillManager;
		this.departmentManager = departmentManager;
	}

	/**
	 * 获取页面参数 <b>accidentBillIds</b> ,如果得到，根据ID获取事故单
	 * 同时根据传递的"toolingDevFlag"来判断是工装还是设备的报废单
	 */
	public void prepare() {
		if (this.hasId("accidentBillIds")) {
			this.toolingAccidentBills = this.accidentBillManager.loadAllAccidentBills(this.getIds("accidentBillIds"));
		}
		if(this.hasId("toolingDevFlag")){
			if(request.getParameter("toolingDevFlag").equals("TOOLING")){
				this.toolingDevFlag="TOOLING";
			}
			else {
				this.toolingDevFlag="DEVICE";
			}
		}
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b> 函数处理
	 * 
	 * @return	String SUCCESS
	 */
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除选择的事故单
	 * 
	 * @return	String SUCCESS
	 */
	public String delete() {
		this.accidentBillManager.deleteAllAccidentBils(toolingAccidentBills);
		this.addActionMessage(this.getText("accidentBill.delete.success"));
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 * 同时根据"toolingDevFlag"判断是工装还是设备的查询，返回不同的ID
	 */
	@Override
	protected String getAdapterName() {
		if(this.toolingDevFlag.equals("TOOLING")){
			return "toolingAccidentBills";
		}else{		
			return "deviceAccidentBills";
		}
	}

	/**
	 * 获得事故单集合
	 * 
	 * @return List 事故单集合
	 */
	public List<AccidentBill> getToolingAccidentBills() {
		return toolingAccidentBills;
	}

	/**
	 * 设置事故单集合
	 * 
	 * @param toolingFaultBill 事故单集合
	 * @return
	 */
	public void setToolingAccidentBills(List<AccidentBill> toolingAccidentBills) {
		this.toolingAccidentBills = toolingAccidentBills;
	}

	/**
	 * 获得选择的部门列表
	 * 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		return this.departmentManager.createSelectDepartments(this.getText("select.option.all"));
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
