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
package com.yongjun.tdms.presentation.webwork.action.base.codevalue;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
/**
 * @author smzhang
 * @version $Id: ListToolingTypeAction 8881 2007-12-02 03:05:28Z smzhang $
 */
public class ListToolingTypeAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
	private List<ToolingType> toolingTypes;
	private final ToolingTypeManager toolingTypeManager;
	public ListToolingTypeAction(ToolingTypeManager toolingTypeManager){
			this.toolingTypeManager = toolingTypeManager;
	}
	/**
	 * 根据页面传来的id几个，获取对象集合
	 */
	public void prepare() throws Exception {
		if(this.hasIds("toolingTypeIds")){
			toolingTypes = toolingTypeManager.loadAllToolingTypes(this.getIds("toolingTypeIds"));
		}
	}

	public String execute() throws Exception {
		if(this.isDelete()){
			return this.delete();
		}
		if(this.isDisabled()){
			return disabled();
		}
		if(this.isEnable()){
			return enabled();
		}
		return SUCCESS;
	}
	/**
	 * 删除选中的toolingType对象
	 * @return
	 */
	public String delete(){
		try{
		this.toolingTypeManager.deleteAllToolingTypes(toolingTypes);
		this.addActionMessage(this.getText("delete.toolingTypes.success"));
		} catch (Exception e) {
			this.addActionMessage(this.getText("delete.failure"));
			return ERROR;
		}
		return SUCCESS;
	}
	public String disabled(){
		this.toolingTypeManager.disabledAllToolingTypes(toolingTypes);
		this.addActionMessage(this.getText("disabled.toolingTypes.success"));
		return SUCCESS;
	}
	public String enabled(){
		this.toolingTypeManager.enabledAllToolingTypes(toolingTypes);
		this.addActionMessage(this.getText("enabled.toolingTypes.success"));
		return SUCCESS;
	}
	protected String getAdapterName() {
		return "toolingType";
	}
	public List<ToolingType> getToolingTypes() {
		return toolingTypes;
	}
	public void setToolingTypes(List<ToolingType> toolingTypes) {
		this.toolingTypes = toolingTypes;
	}

}
