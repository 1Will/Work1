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
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.base.codevalue.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;

/**
 * <p>Title:EditSpareDetailTypeAction
 * <p>Description:备件分类明细维护页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class EditSpareDetailTypeAction extends PrepareAction {

	private static final long serialVersionUID = 1L;
	private SpareType spareType;
	private SpareDetailType spareDetailType;
	private final SpareDetailTypeManager spareTypeDetailManager;
	private final SpareTypeManager spareTypeManager;
	public EditSpareDetailTypeAction(SpareDetailTypeManager spareTypeDetailManager,SpareTypeManager spareTypeManager){
		this.spareTypeDetailManager = spareTypeDetailManager;
		this.spareTypeManager = spareTypeManager;
	}
	public void prepare() throws Exception {
		if (this.hasId("spareType.id")) {
			spareType = spareTypeManager.loadSpareType(this.getId("spareType.id"));
		}
		if(null == spareDetailType){
			if(this.hasId("spareDetailType.id")){
				spareDetailType = spareTypeDetailManager.loadSpareDetailType(this.getId("spareDetailType.id"));
			}else{
				spareDetailType = new SpareDetailType();
			}
		}
	
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String save(){
		boolean isNew = spareDetailType.isNew();
		spareDetailType.setSpareType(spareType);	
		try{
			try{
				//将备件分类对象保存到备件分类明细中
			spareTypeDetailManager.storeSpareDetailType(spareDetailType);
			} catch (Exception e) {
				throw new ErroSaveException();
			}
		}catch(ErroSaveException e){
			this.addActionMessage(this.getText("referCode.already.exists",
					Arrays.asList(new Object[] { spareDetailType.getCode() })));
			return NEW;
		}
		if (isNew) {
			this.addActionMessage(this.getText("spareTypeDetail.add.success", Arrays
					.asList(new Object[] { spareDetailType.getCode() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareTypeDetail.edit.success", Arrays
					.asList(new Object[] { spareDetailType.getCode() })));
			return SUCCESS;
		}
	}
	public SpareDetailType getSpareDetailType() {
		return spareDetailType;
	}
	public void setSpareDetailType(SpareDetailType spareDetailType) {
		this.spareDetailType = spareDetailType;
	}
	public SpareType getSpareType() {
    	return spareType;
    }
	public void setSpareType(SpareType spareType) {
    	this.spareType = spareType;
    }
}
