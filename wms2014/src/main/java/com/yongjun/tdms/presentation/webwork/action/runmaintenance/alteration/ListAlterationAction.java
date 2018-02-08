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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.alteration;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;
import com.yongjun.tdms.model.runmaintenance.alteration.UnSealedStatus;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.service.runmaintenance.alteration.AlterationManager;

/**
 * <p>
 * Title: ListAlterationAction
 * <p>
 * Description: 变动单页面访问控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author wzou@yj-technology.com
 * @version $Id: ListAlterationAction.java 11002 2008-02-18 03:33:05Z wzou $
 */
public class ListAlterationAction extends ValueListAction {
	private static final long serialVersionUID = -2681790591477283627L;

	private List<Alteration> alteration;

	private final AlterationManager alterationManager;

	private String toolingDevFlag;

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public ListAlterationAction(AlterationManager alterationManager) {
		this.alterationManager = alterationManager;
	}

	/**
	 * 根据页面上传来的alterationIds集合 加裁所有的工装变动实体集合
	 * 
	 * @param
	 * @return
	 */
	public void prepare() throws Exception {
		if (this.hasId("toolingDevFlag")) {
			if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
				this.toolingDevFlag = "TOOLING";
			} else {
				this.toolingDevFlag = "DEVICE";
			}
		}
		if (alteration == null && this.hasIds("alterationIds")) {
			alteration = alterationManager.loadAllAlteration(this
					.getIds("alterationIds"));
		}
	}

	public String execute() throws Exception {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return this.enabled();
		}
		if (isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	/**
	 * 删除工装变动对象 如果删除成功，则提示删除成功消息，如果删除失败，进行失败处理并提示删除失败消息
	 * 
	 * @param
	 * @return
	 */
	private void delete() {
		try {
			this.alterationManager.deleteAllAlteration(alteration);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("alteration.delete.error"));
		}
		this.addActionMessage(this.getText("alteration.delete.success"));
	}
	
	public String disabled() {
		this.alterationManager.disabledAllAlterations(alteration);
		if(toolingDevFlag.equalsIgnoreCase("TOOLING")){
			this.addActionMessage(this.getText("faultBillTooling.disabled.success"));
		}else {
			this.addActionMessage(this.getText("faultBillDevice.disabled.success"));
		}
		return SUCCESS;
	}
	
	public String enabled() {
		this.alterationManager.enabledAllAlterations(alteration);
		if(toolingDevFlag.equalsIgnoreCase("TOOLING")){
			this.addActionMessage(this.getText("faultBillTooling.enabled.success"));
		}else {
			this.addActionMessage(this.getText("faultBillDevice.enabled.success"));
		}
		return SUCCESS;
	}
	
	public List<Alteration> getAlteration() {
		return alteration;
	}

	public void setAlteration(List<Alteration> alteration) {
		this.alteration = alteration;
	}

	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 * 
	 * @param
	 * @return String alteration valuelist里配置的bean名称
	 */
	@Override
	protected String getAdapterName() {
		return "alteration";
	}
	
	public List<LabelValue> getSealedStatus() {
		LabelValue[] arrays = this.wrapEnum(UnSealedStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),
				this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
}
