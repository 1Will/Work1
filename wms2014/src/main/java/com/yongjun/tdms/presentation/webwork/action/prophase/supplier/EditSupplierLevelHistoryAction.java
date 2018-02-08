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
package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierLevelHistoryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * @author qs
 * @version $Id: $
 */
public class EditSupplierLevelHistoryAction extends PrepareAction {
	private static final long serialVersionUID = -1629626731501710513L;

	private final SupplierManager supplierManager;

	private final SupplierLevelHistoryManager supplierLevelHistoryManager;

	private final CodeValueManager codeValueManager;

	private Supplier supplier;

	private SupplierLevelHistory supplierLevelHistory;
	private CodeValue newLevel;

	public EditSupplierLevelHistoryAction(SupplierManager supplierManager,
			SupplierLevelHistoryManager supplierLevelHistoryManager,
			CodeValueManager codeValueManager) {
		this.supplierManager = supplierManager;
		this.supplierLevelHistoryManager = supplierLevelHistoryManager;
		this.codeValueManager = codeValueManager;
	}

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void prepare() throws Exception {
		if (null == this.supplier) {
			if (this.hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(this
						.getId("supplier.id"));
			} 
		}
		if (null == this.supplierLevelHistory) {
			if (this.hasId("supplierLevelHistory.id")) {
				this.supplierLevelHistory = this.supplierLevelHistoryManager.loadSupplierLevelHistory(this.getId("supplierLevelHistory.id"));
			} else {
				this.supplierLevelHistory = new SupplierLevelHistory();
			}
		}
		if (this.hasId("newLevel.id")) {
			this.newLevel = this.codeValueManager.loadCodeValue(this.getId("newLevel.id"));
		}
	}

	public String save() {
		boolean isNew = this.supplierLevelHistory.isNew();
		
		if(!StringUtils.isEmpty(request.getParameter("supplierLevelHistory.newLevel"))) {
			newLevel = this.codeValueManager.loadCodeValue(this.getId("supplierLevelHistory.newLevel"));
			if (null != supplier.getLevel()) {        //如果供应商等级不为空,则往历史记录里插入原级别
				supplierLevelHistory.setOrigLevel(supplier.getLevel().getValue());
			}
			supplier.setLevel(newLevel);                      //设置供应商的新的等级
		    supplierLevelHistory.setNewLevel(newLevel.getValue()); 
		 
		    
		}
		this.supplierLevelHistoryManager.storeSupplierLevelHistory(supplier,supplierLevelHistory);
		if (isNew) {
			this.addActionMessage(this.getText(
					"supplierLevelHistory.add.success", Arrays
							.asList(new Object[] { supplier.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText(
					"supplierLevelHistory.edit.success", Arrays
							.asList(new Object[] { supplier.getName() })));
			return SUCCESS;
		}

	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public SupplierLevelHistory getSupplierLevelHistory() {
		return supplierLevelHistory;
	}

	public void setSupplierLevelHistory(
			SupplierLevelHistory supplierLevelHistory) {
		this.supplierLevelHistory = supplierLevelHistory;
	}

	public List getSupplierLevel() {
		List<CodeValue> level = codeValueManager
				.LoadAllValuesByCode(CodeConstants.SUPPLIER_LEVEL);
		//level.remove(supplier.getLevel());
		return level;
	}

	public CodeValue getNewLevel() {
		return newLevel;
	}

	public void setNewLevel(CodeValue newLevel) {
		this.newLevel = newLevel;
	}

}
