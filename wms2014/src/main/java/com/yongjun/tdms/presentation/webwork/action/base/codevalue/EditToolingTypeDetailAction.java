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

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.exception.ErroSaveException;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeDetailManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
/**
 * @author smzhang
 * @version $Id: EditToolingTypeDetailAction 8881 2007-12-02 03:05:28Z smzhang $
 */
public class EditToolingTypeDetailAction extends PrepareAction{
	
	private static final long serialVersionUID = 1L;
	private ToolingType toolingType;
	private ToolingTypeDetail toolingTypeDetail;
	private final ToolingTypeManager toolingTypeManager;
	private final ToolingTypeDetailManager toolingTypeDetailManager;
	EditToolingTypeDetailAction (ToolingTypeManager toolingTypeManager,
			ToolingTypeDetailManager toolingTypeDetailManager){
		this.toolingTypeManager = toolingTypeManager;
		this.toolingTypeDetailManager = toolingTypeDetailManager;
	}
	/**
	 * 根据页面传来的id获取对象
	 */
	public void prepare() throws Exception {
		if(this.toolingType == null){
			if(this.hasId("toolingType.id")){
				toolingType = toolingTypeManager.loadToolingType(getId("toolingType.id"));
			}
		}
		if(this.toolingTypeDetail == null){
			if(this.hasId("toolingTypeDetail.id")){
				toolingTypeDetail = toolingTypeDetailManager.loadToolingTypeDetail(getId("toolingTypeDetail.id"));
			}else{
				toolingTypeDetail = new ToolingTypeDetail();
			}
		}
	}
	/**
	 * save方法，保存toolingTypeDetail对象
	 * @return
	 */
	public String save(){
		boolean isNew = this.toolingTypeDetail .isNew();
		toolingTypeDetail.setToolingType(toolingType);
		try{
		try{
		toolingTypeDetailManager.storeToolingTypeDetail(toolingTypeDetail);
		} catch (Exception e) {
			throw new ErroSaveException();
		}
		}catch(ErroSaveException e){
			this.addActionMessage(this.getText("toolingType.Error"));
			return NEW;
		}
		if (isNew) {
			this.addActionMessage(this.getText("toolingTypeDetail.add.success", Arrays
					.asList(new Object[] { toolingTypeDetail.getCode() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingTypeDetail.edit.success", Arrays
					.asList(new Object[] { toolingTypeDetail.getCode() })));
			System.out.println("SUCCESS");
			return SUCCESS;
		}
		
	}

	public String execute() throws Exception {
		return SUCCESS;
	}
	public ToolingType getToolingType() {
		return toolingType;
	}
	public void setToolingType(ToolingType toolingType) {
		this.toolingType = toolingType;
	}
	public ToolingTypeDetail getToolingTypeDetail() {
		return toolingTypeDetail;
	}
	public void setToolingTypeDetail(ToolingTypeDetail toolingTypeDetail) {
		this.toolingTypeDetail = toolingTypeDetail;
	}

}
