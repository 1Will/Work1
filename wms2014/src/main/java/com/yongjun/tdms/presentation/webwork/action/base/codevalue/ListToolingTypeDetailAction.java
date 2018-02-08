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
import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeDetailManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
/**
 * @author smzhang
 * @version $Id: ListToolingTypeDetailAction 8881 2007-12-02 03:05:28Z smzhang $
 */
public class ListToolingTypeDetailAction extends ValueListAction {

	private static final long serialVersionUID = 1L;
	private ToolingType toolingType;
	private List<ToolingTypeDetail> toolingTypeDetails;
	private final ToolingTypeManager toolingTypeManager;
	private final ToolingTypeDetailManager toolingTypeDetailManager;
	ListToolingTypeDetailAction(ToolingTypeManager toolingTypeManager,
		ToolingTypeDetailManager toolingTypeDetailManager){
		this.toolingTypeManager = toolingTypeManager;
		this.toolingTypeDetailManager = toolingTypeDetailManager;
	}
	protected String getAdapterName() {
		return "toolingTypeDetails";
	}
	/**
	 * 根据页面传来的id几个，获取对象集合
	 */
	public void prepare() throws Exception {
		if(this.hasIds("toolingTypeDetailIds")){
			this.toolingTypeDetails = toolingTypeDetailManager.loadAllToolingTypeDetails(getIds("toolingTypeDetailIds"));
		}
		if(toolingType == null){
			if(this.hasId("toolingType.id")){
				toolingType = toolingTypeManager.loadToolingType(this.getId("toolingType.id"));
			}else{
				toolingType = new ToolingType();
			}
		}
		this.setFirst(false);
	}

	public String execute() throws Exception {
		if(this.isDelete()){
			this.delete();
		}
		return SUCCESS;
	}
	/**
	 * 删除选中的toolingType对象
	 * @return
	 */
	public String delete(){
		try{
		this.toolingTypeDetailManager.deleteAllToolingTypeDetails(toolingTypeDetails);
		this.addActionMessage(this.getText("delete.toolingTypeDetails.success"));
		} catch (Exception e) {
			this.addActionMessage(this.getText("delete.failure"));
			return ERROR;
		}
		return SUCCESS;
	}
	public ToolingType getToolingType() {
		return toolingType;
	}
	public void setToolingType(ToolingType toolingType) {
		this.toolingType = toolingType;
	}
	public List<ToolingTypeDetail> getToolingTypeDetails() {
		return toolingTypeDetails;
	}
	public void setToolingTypeDetails(List<ToolingTypeDetail> toolingTypeDetails) {
		this.toolingTypeDetails = toolingTypeDetails;
	}

}
