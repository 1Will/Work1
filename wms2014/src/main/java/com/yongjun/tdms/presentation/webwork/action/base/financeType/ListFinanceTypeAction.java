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
package com.yongjun.tdms.presentation.webwork.action.base.financeType;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.financeType.FinanceType;
import com.yongjun.tdms.service.base.financeType.FinanceTypeManager;

/**
 * <p>Title: ListFinanceTypeAction
 * <p>Description: 财务分类页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
public class ListFinanceTypeAction extends ValueListAction {
	private static final long serialVersionUID = -9122971354340110382L;
	private FinanceTypeManager financeTypeManager;
	private List<FinanceType> financeTypes;
	
	public ListFinanceTypeAction(FinanceTypeManager financeTypeManager) {
		this.financeTypeManager = financeTypeManager;
	}
	
	public void prepare() {
		if (null == financeTypes) {
			if (this.hasId("financeTypeIds")) {
				this.financeTypes = this.financeTypeManager.loadAllFinanceTypes(this.getIds("financeTypeIds"));
			}
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		if(this.isDisabled()){
			return disabled();
		}
		if(this.isEnable()){
			return enabled();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.financeTypeManager.deleteAllFinanceTypes(financeTypes);
		this.addActionMessage(this.getText("financeType.delete.success"));
		return SUCCESS;
	}
	public String disabled(){
		this.financeTypeManager.disabledAllFinanceTypes(financeTypes);
		this.addActionMessage(this.getText("financeType.disabled.success"));
		return SUCCESS;
	}
	public String enabled(){
		this.financeTypeManager.enabledAllFinanceTypes(financeTypes);
		this.addActionMessage(this.getText("financeType.enabled.success"));
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		return "financeTypes";
	}

}
