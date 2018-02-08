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
package com.yongjun.tdms.presentation.webwork.action.base.lubricationOil;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.lubricationOil.LubricationOilManager;

public class EditLubricationOilAction extends PrepareAction {
	private static final long serialVersionUID = 3667819688590438749L;
	private final LubricationOilManager lubricationOilManager;
	private final CodeValueManager codeValueManager;
	private LubricationOil lubricationOil;
	
	public EditLubricationOilAction(LubricationOilManager lubricationOilManager,
			CodeValueManager codeValueManager) {
		this.lubricationOilManager = lubricationOilManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (null == this.lubricationOil) {
			if(this.hasId("lubricationOil.id")) {
				this.lubricationOil = this.lubricationOilManager.loadLubricationOil(this.getId("lubricationOil.id"));
			} else {
				this.lubricationOil = new LubricationOil();
			}
		}
	}

	public String save() {
		
		if (this.isDisabled()) {
			this.lubricationOil.setDisabled(true);
		}
		
		if (this.isEnabled()) {
			this.lubricationOil.setDisabled(false);
		}
		boolean isNew = this.lubricationOil.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("category.id"))) {
			this.lubricationOil.setCategory(this.codeValueManager.loadCodeValue(this.getId("category.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("measureUnit.id"))) {
			this.lubricationOil.setMeasureUnit(this.codeValueManager.loadCodeValue(this.getId("measureUnit.id")));
		}
		this.lubricationOilManager.storeLubricationOil(this.lubricationOil);
		
		if (isNew) {
			this.addActionMessage(this.getText("lubricationOil.add.success", Arrays
					.asList(new Object[] { lubricationOil.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("lubricationOil.edit.success", Arrays
					.asList(new Object[] { lubricationOil.getName() })));
			return SUCCESS;
		}
		
	}
	
//	private boolean isDisabled() {
//		return this.hasKey("disabled");
//	}
	
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
	public LubricationOil getLubricationOil() {
		return lubricationOil;
	}

	public void setLubricationOil(LubricationOil lubricationOil) {
		this.lubricationOil = lubricationOil;
	}
	
	public List getCategorys() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.LUBRICATION_OIL_TYPE);
	}
	
	public List getMeasureUnits() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.CAPACITY_MEASURE);
	}

}
