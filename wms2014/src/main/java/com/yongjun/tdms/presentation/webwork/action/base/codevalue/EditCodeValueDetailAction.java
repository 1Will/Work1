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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author wzou
 * @version $Id: EditCodeValueAction.java 8000 2007-10-24 09:18:29Z qsun $
 */
public class EditCodeValueDetailAction extends PrepareAction {
	private static final long serialVersionUID = 2383086852839142040L;
	
	private final CodeValueManager codeValueManager;
	private CodeValue type;
	private CodeValue codeValueDetail;
	private List<CodeValue> types;
	
	public EditCodeValueDetailAction (CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() throws Exception {
		if (null == type) {
			if (hasId("type.id")) {
				type = codeValueManager.loadCodeValue(getId("type.id"));
			} else {
			type = new CodeValue();		
			}
		}
		if (null == codeValueDetail) {
			if (hasId("codeValueDetail.id")) {
				codeValueDetail = codeValueManager.loadCodeValue(getId("codeValueDetail.id")); 
			} else {
				codeValueDetail = new CodeValue();
			}
		}
	}
	
	public String save() {
		/*
		 * 判断编码名称唯一性，暂时用不到。
		//获得父类的id
		long id = 0;
		id= type.getId();
		String codeValue = null;
		//获得页面编码名称的值
		if(!StringUtils.isEmpty(request.getParameter("codeValueDetail.value"))){
			codeValue = request.getParameter("codeValueDetail.value");
		}
		*/
		boolean isNew = this.codeValueDetail.isNew();
		List<CodeValue> allTypes = new ArrayList() ;
		int code=0,max=0;
		String s_code;
		if (isNew) {
			types = codeValueManager.loadAllValues();
			for(CodeValue t : types) {
				if (t.getMasterCode()!=null){
					if (t.getMasterCode().getId()==type.getId()){
						allTypes.add(t);
					}
				}
			}
			for(CodeValue type : allTypes) {
				code = Integer.valueOf(type.getCode().substring(3,type.getCode().length()));
				if (code>max) {
					max = code;
				}
			}
			s_code = type.getCode()+Integer.toString((max+1));
			codeValueDetail.setMasterCode(type);
			this.codeValueDetail.setCode(s_code);
		}
		/*
		 * 对编码名称唯一性的验证，功能已实现，暂时用不到，预留
		if(isNew){
			if(checkCodeValue(codeValue,id)){
				this.codeValueManager.storeCodeValue(this.codeValueDetail);
			}else{
				this.addActionMessage(this.getText("codeValueDetail.value.exists",Arrays
						.asList(new Object[] {codeValueDetail.getValue()})));
				return ERROR;
			}
		}else{
			this.codeValueManager.storeCodeValue(this.codeValueDetail);
		}
		*/
		this.codeValueManager.storeCodeValue(this.codeValueDetail);
		
		if (isNew) {
			this.addActionMessage(this.getText("codeValueDetail.add.success", Arrays
					.asList(new Object[] { codeValueDetail.getValue() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("codeValueDetail.edit.success", Arrays
					.asList(new Object[] { codeValueDetail.getValue() })));
			return SUCCESS;
		}
	}
	
	
	/**
	 * 判断编码名称是否存在
	 * @param codeValue 
	 * @param id 
	 * @return true | false
	 */
	private boolean checkCodeValue(String codeValue,Long id){
		if(this.codeValueManager.getcodeValueDetailByValueCount(codeValue, id) > 0){
			return false;
		}
		return true;
	}
	
	
	public CodeValue getCodeValueDetail() {
		return codeValueDetail;
	}

	public void setCodeValueDetail(CodeValue codeValueDetail) {
		this.codeValueDetail = codeValueDetail;
	}

	public CodeValue getType() {
		return type;
	}

	public void setType(CodeValue type) {
		this.type = type;
	}

	public List<CodeValue> getTypes() {
		return types;
	}

	public void setTypes(List<CodeValue> types) {
		this.types = types;
	}

}
