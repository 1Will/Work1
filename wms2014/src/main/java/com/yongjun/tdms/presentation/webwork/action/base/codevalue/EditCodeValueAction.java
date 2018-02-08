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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author qs
 * @version $Id: EditCodeValueAction.java 11325 2008-03-15 06:48:17Z wzou $
 */
public class EditCodeValueAction extends PrepareAction {
	private static final long serialVersionUID = -4592806141801642844L;
	private CodeValue type;
	private List<CodeValue> codeValueDetails;		//用于获取删除的CodeValue
	private List<CodeValue> Details;				//用于获取CodeValue明细的List集合，以便在页面排序显示
	private List<CodeValue> types;
	private final CodeValueManager codeValueManager;
	
	public EditCodeValueAction(CodeValueManager codeValueManager) {
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() throws Exception {
		if (this.codeValueDetails == null && this.hasIds("codeValueDetailIds")) {
			this.codeValueDetails = this.codeValueManager.loadAllValues(this.
					getIds("codeValueDetailIds"));
		}
		if (null == type) {
			if (hasId("type.id")) {
				type = codeValueManager.loadCodeValue(getId("type.id")); 
				Details=codeValueManager.LoadAllValuesByCode(type.getCode());
//				if (type.getValues()!= null){					
//					type.setValues(range(type));
//				}
			} else {
			type = new CodeValue();
			}
		}
	}
	
	/**
	 * 删除选择的基础编码
	 */
	public String delete() {
		try {
			this.codeValueManager.deleteAllCodeValue(this.codeValueDetails);
		} catch (Exception e) {
			this.addActionMessage(this.getText("codeValueDetails.delete.error"));
			return ERROR;
		}

		this.addActionMessage(this.getText("codeValueDetails.delete.success"));
		return SUCCESS;
	}
	/**
	 * 对CodeValue对象中关联的Set类型的CodeValue对象根据code值进行排序。
	 * 
	 */
	public Set range(CodeValue codeValue) {
		Set detailType = new HashSet();
		Set newDetailType = new HashSet();
		CodeValue every;
		detailType = codeValue.getValues();
		int length = detailType.size();
		for (int i=0;i<length;i++) {
			every=codeValueManager.loadCodeTypeByCode(
					codeValue.getCode()+Integer.toString((i+1))
					);
			newDetailType.add(every);
		}
		return newDetailType;
		
	}
	
	public String save() {
		/*
		 * 对编码名称唯一性验证，收集数据，暂时用不到。
		//获取编码名称的值
		String codeValue = null;
		if(!StringUtils.isEmpty(request.getParameter("type.value"))){
			codeValue = request.getParameter("type.value");
		}
		*/
		/**
		 * 页面执行，如果选择了删除就调用 <b>delete</b>函数处理。因为在xwork.xml中指定save()方法，故删除也放在save()方法中。
		 */
		if (this.isDelete()) {
			delete();
			return SUCCESS;
		}
		boolean isNew = this.type.isNew();
		//判断是否是新建，如果是，查询数据库获取最大code值+1，转换为字符串。
		if (isNew) {
			types = codeValueManager.loadAllValues();
			int code=0,max=0;
			String s_code;
			for(CodeValue type : types) {
				if (type.getMasterCode()==null){
					code = Integer.valueOf(type.getCode().substring(0,3));
					if (code>max) {
						max = code;
					}
				}
			}
			if(Integer.toString((max+1)).length()<3){
				s_code = "0"+Integer.toString((max+1));
			}else {
				s_code = Integer.toString((max+1));
			}
			this.type.setCode(s_code);
		}
		/*
		 * 对编码姓名唯一性验证，功能已实现，暂时用不到，预留
		if(isNew){
			if(checkCodeValue(codeValue)){
				this.codeValueManager.storeCodeValue(this.type);
			}else{
				this.addActionMessage(this.getText("codeValue.value.exists",Arrays
						.asList(new Object[] {type.getValue()})));
				return ERROR;
			}
		}else{
			this.codeValueManager.storeCodeValue(this.type);
		}
		*/
		
		this.codeValueManager.storeCodeValue(this.type);
		if (isNew) {
			this.addActionMessage(this.getText("codeValue.add.success", Arrays
					.asList(new Object[] { type.getValue() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("codeValue.edit.success", Arrays
					.asList(new Object[] { type.getValue() })));
			return SUCCESS;
		}
		
	}
	/**
	 * 判断编码名称是否存在
	 * @param codeValue
	 * @return true | false
	 */
	private boolean checkCodeValue(String codeValue){
		if(this.codeValueManager.getCodeValueByValue(codeValue) > 0 ){
			return false;
		}
		return true;
	}
	
	
	public CodeValue getType() {
		return type;
	}
	
	public void setType(CodeValue type) {
		this.type = type;
	}
	
	public Set<CodeValue> getValues() {
		if (null != type) {
			return type.getValues(); 
		}
		return null;
	}

	public List<CodeValue> getDetails() {
		return Details;
	}

	public void setDetails(List<CodeValue> details) {
		Details = details;
	}
}
