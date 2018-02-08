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
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
/**
 * @author smzhang
 * @version $Id: EditToolingTypeAction 8881 2007-12-02 03:05:28Z smzhang $
 */
public class EditToolingTypeAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
	private ToolingType toolingType;
	private final ToolingTypeManager toolingTypeManager;
	public EditToolingTypeAction(ToolingTypeManager toolingTypeManager){
			this.toolingTypeManager = toolingTypeManager;
	}
	/**
	 * 根据页面传来的id获取对象
	 */
	public void prepare() throws Exception {
		if(toolingType == null){
			if(this.hasId("toolingType.id")){
				toolingType = this.toolingTypeManager.loadToolingType(this.getId("toolingType.id"));
			}else{
				toolingType = new ToolingType();
			}
		}
	}
	/**
	 * save方法，保存toolingType对象
	 * @return
	 */
	public String save() {
		boolean isNew = this.toolingType.isNew();
		try{
		try {
			this.toolingTypeManager.storeToolingType(toolingType);
		} catch (Exception e) {
			throw new ErroSaveException();
		}
		}catch(ErroSaveException e){
			this.addActionMessage(this.getText("toolingType.Error"));
			return NEW;
		}
		if (isNew) {
			this.addActionMessage(this.getText("toolingType.add.success",
					Arrays.asList(new Object[] { toolingType.getCode() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingType.edit.success",
					Arrays.asList(new Object[] { toolingType.getCode() })));
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

}
