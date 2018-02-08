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
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;

/**
 * <p>Title:ListSpareTypeAction
 * <p>Description:备件分类页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class ListSpareTypeAction extends ValueListAction {

	private static final long serialVersionUID = -4602220727976223345L;
	private List<SpareType> spareTypes;
	private final SpareTypeManager spareTypeManager;
	
	public ListSpareTypeAction(SpareTypeManager spareTypeManager){
		this.spareTypeManager = spareTypeManager;
	}
	/**
	 * 根据页面传来的id集合，获取对象集合
	 */
	public void prepare() throws Exception {
		if(this.hasIds("spareTypeIds")){
			spareTypes = spareTypeManager.loadAllSpareType(this.getIds("spareTypeIds"));
		}
	}
	@Override
	public String execute() throws Exception {
		if(isDelete()){
			this.delete();
		}
		if(this.isDisabled()){
			this.disable();
		}
		if(this.isEnable()){
			this.enable();
		}
		return SUCCESS;
	}

	private String delete() {
		try {
			this.spareTypeManager.deleteAllSpareType(spareTypes);
			this.addActionMessage(this.getText("spareType.delete.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			this.addActionMessage(this.getText("spareType.delete.falier"));
			e.printStackTrace();
			return ERROR;
		}
	}
	private String disable() {
		System.out.println("jhhfdg");
		try {
			this.spareTypeManager.disabledAllSpareType(spareTypes);
			this.addActionMessage(this.getText("spareType.disable.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			this.addActionMessage(this.getText("spareType.disable.falier"));
			e.printStackTrace();
			return ERROR;
		}
	}
	private String enable() {
		try {
			this.spareTypeManager.endabledAllSpareType(spareTypes);
			this.addActionMessage(this.getText("spareType.enable.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			this.addActionMessage(this.getText("spareType.enable.falier"));
			e.printStackTrace();
			return ERROR;
		}
	}
	@Override
	protected String getAdapterName() {
		return "spareTypes";
	}
	
	//获得类别
	public List<LabelValue> getSpareSort(){
		LabelValue[] arrays = this.wrapEnum(SysModel.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

}
