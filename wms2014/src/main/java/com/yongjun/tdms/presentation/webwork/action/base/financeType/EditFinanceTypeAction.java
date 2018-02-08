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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.financeType.FinanceType;
import com.yongjun.tdms.service.base.financeType.FinanceTypeManager;

/**
 * <p>Title: EditFinanceTypeAction
 * <p>Description: 财务分类页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */
public class EditFinanceTypeAction extends PrepareAction {
	private static final long serialVersionUID = -6877391661670910445L;
	private final FinanceTypeManager financeTypeManager;
	private FinanceType financeType;

	public EditFinanceTypeAction(FinanceTypeManager financeTypeManager) {
		this.financeTypeManager = financeTypeManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.financeType) {
			if (this.hasId("financeType.id")) {
				this.financeType = this.financeTypeManager.loadFinanceType(this.getId("financeType.id"));
			} else {
				this.financeType = new FinanceType();
			}
		}
		
	}

	public String save() {
		boolean isNew = this.financeType.isNew();
		this.financeTypeManager.storeFinanceType(financeType);
		
		if (isNew) {
			this.addActionMessage(this.getText("financeType.add.success", Arrays
					.asList(new Object[] { financeType.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("financeType.edit.success", Arrays
					.asList(new Object[] { financeType.getName() })));
			return SUCCESS;
		}
	}
	public FinanceType getFinanceType() {
		return financeType;
	}

	public void setFinanceType(FinanceType financeType) {
		this.financeType = financeType;
	}

	public List<String> getAllCode() {
		List<String> allCode = new ArrayList();
		for(FinanceType financeType : financeTypeManager.loadAllFinanceTypes()){
			allCode.add(financeType.getCode());
		}
		return allCode;
	}

	
}
