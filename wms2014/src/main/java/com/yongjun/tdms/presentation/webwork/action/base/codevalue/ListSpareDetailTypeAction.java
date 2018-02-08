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
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;

/**
 * <p>Title:ListSpareDetailTypeAction
 * <p>Description:备件分类明细页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class ListSpareDetailTypeAction extends ValueListAction {

	private static final long serialVersionUID = 1324830619698498198L;
	private SpareType spareType;
	private List<SpareDetailType> spareDetailTypes;
	private final SpareTypeManager spareTypeManager;
	private final SpareDetailTypeManager spareTypeDetailManager;
	public ListSpareDetailTypeAction(SpareTypeManager spareTypeManager,SpareDetailTypeManager spareTypeDetailManager){
		this.spareTypeManager = spareTypeManager;
		this.spareTypeDetailManager = spareTypeDetailManager;
	}
	@Override
	public void prepare() throws Exception {
		if(this.hasId("spareType.id")){
			this.spareType = spareTypeManager.loadSpareType(this.getId("spareType.id"));
		}
		if(this.hasIds("spareTypeDetailIds")){
			this.spareDetailTypes = spareTypeDetailManager.loadAllSpareDetailTypes(this.getIds("spareTypeDetailIds"));
		}
		this.setFirst(false);
	}
	@Override
	public String execute() throws Exception {
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}
	private String delete() {
		try {
			spareTypeDetailManager.deleteAllSpareDetailType(spareDetailTypes);
			this.addActionMessage(this.getText("spareDetailType.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			this.addActionMessage(this.getText("spareDetailType.delete.filaure"));
			e.printStackTrace();
			return ERROR;
		}
		
	}
	@Override
	protected String getAdapterName() {
		return "spareDetailTypes";
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("spareType.id",this.getId("spareType.id"));
		return map;
	}
	public SpareType getSpareType() {
    	return spareType;
    }
	public void setSpareType(SpareType spareType) {
    	this.spareType = spareType;
    }
	
}
