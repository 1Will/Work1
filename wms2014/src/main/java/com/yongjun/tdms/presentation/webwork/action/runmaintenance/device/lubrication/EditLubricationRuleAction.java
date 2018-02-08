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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.lubricationOil.LubricationOilManager;
import com.yongjun.tdms.service.runmaintenance.lubrication.LubricationRuleManager;

/**
 * <p>Title: EditLubricationRuleAction
 * <p>Description: 润滑标准访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: EditLubricationRuleAction.java 11434 2008-03-18 13:05:07Z zbzhang $
 */
public class EditLubricationRuleAction extends PrepareAction {
	private static final long serialVersionUID = 3774135688205317086L;

	private final DeviceCardManager deviceCardManager;
	private final LubricationRuleManager lubricationRuleManager;
	private final LubricationOilManager lubricationOilManager;
	private final CodeValueManager codeValueManager;

	private DeviceCard device;
	private LubricationRule lubricationRule;
	private LubricationOil lubricationOil;

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public EditLubricationRuleAction(DeviceCardManager deviceCardManager,
			LubricationRuleManager lubricationRuleManager,
			LubricationOilManager lubricationOilManager,
			CodeValueManager codeValueManager) {
		this.deviceCardManager = deviceCardManager;
		this.lubricationRuleManager = lubricationRuleManager;
		this.lubricationOilManager = lubricationOilManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() {
		if (null == this.lubricationRule) {
			if (this.hasId("lubricationRule.id")) {
				this.lubricationRule = this.lubricationRuleManager
						.loadLubricationRule(this.getId("lubricationRule.id"));
					this.device = this.lubricationRule.getDevice();
				if (!this.hasId("lubricationOil.id")) {
					this.lubricationOil=this.lubricationRule.getLubricatingOil();
				}
			} else {
				this.lubricationRule = new LubricationRule();
			}
		}
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
	}
	/**
	 * 保存润滑标准
	 * @return NEW | SUCCESS
	 */
	public String save(){
		boolean isNew = this.lubricationRule.isNew();
		
		if (this.hasId("lubricationOil.id")) {              //获取润滑油材质
			this.lubricationOil = this.lubricationOilManager.loadLubricationOil(this.getId("lubricationOil.id"));
			this.lubricationRule.setLubricatingOil(lubricationOil);
		}
		/*
		if (!StringUtils.isEmpty(request.getParameter("dutyPeople.name"))) {
			lubricationRule.setDutyPeople(request.getParameter("dutyPeople.name"));
		}*/
		if (!StringUtils.isEmpty(request.getParameter("lubricationRules.name"))) {
			lubricationRule.setMethodDsp(request.getParameter("lubricationRules.name"));
		}
		
		this.lubricationRule.setDevice(device);
		this.lubricationRuleManager.storeLubricationRule(lubricationRule);
		if (isNew) {
			this.addActionMessage(this.getText("lubricationRule.add.success", Arrays
					.asList(new Object[] { lubricationRule.getRuleDsp() })));

			return NEW;
		} else {       
				this.addActionMessage(this.getText("lubricationRule.edit.success", Arrays
						.asList(new Object[] { lubricationRule.getRuleDsp() })));
				return SUCCESS;
		}
	}

	public LubricationRule getLubricationRule() {
		return lubricationRule;
	}

	public void setLubricationRule(LubricationRule lubricationRule) {
		this.lubricationRule = lubricationRule;
	}

	public List<LubricationOil> getLubricationRuleOil() {
		return lubricationOilManager.loadAllLubricationOils();
	}

	public LubricationOil getLubricationOil() {
		return lubricationOil;
	}

	public void setLubricationOil(LubricationOil lubricationOil) {
		this.lubricationOil = lubricationOil;
	}
	
	/**
	 * 获取所有的润滑方法
	 * @return List 润滑方法集合
	 */
	public List getLubricationMethods() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.LUBRICATION_METHOD);
	}

}
