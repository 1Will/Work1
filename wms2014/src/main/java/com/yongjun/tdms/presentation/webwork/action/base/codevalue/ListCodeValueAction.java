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
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author qs
 * @version $Id: ListCodeValueAction.java 11325 2008-03-15 06:48:17Z wzou $
 */
public class ListCodeValueAction extends ValueListAction {
	private static final long serialVersionUID = -5309787188031323179L;
	
	private final CodeValueManager codeValueManager;
	private List<CodeValue> codeValueDetails;
	
	public ListCodeValueAction(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}
	
	/**
	 * 根据页面传来的基础编码的ID集合,获取基础编码的集合对象
	 */
	public void prepare() {
		if (this.hasIds("codeValueIds")) {
			this.codeValueDetails = this.codeValueManager.loadAllValues(this.getIds("codeValueIds"));
		}
	}
	
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		return SUCCESS;
	}
	
	public String disabled() {
		this.codeValueManager.disabledAllCodeValues(codeValueDetails);
		this.addActionMessage(this.getText("disabled.codeValueDetails.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.codeValueManager.enabledAllChecks(codeValueDetails);
		this.addActionMessage(this.getText("enabled.codeValueDetails.success"));
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {
		return "codes";
	}

}
