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
package com.yongjun.tdms.presentation.webwork.action.base.warnning;

import java.util.List;

import com.yongjun.pluto.model.base.warnning.WarnningRule;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.warnning.WarnningRuleManager;

/**
 * <p>Title: ListWarnningRuleAction
 * <p>Description: 提醒规则的提醒规则查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListWarnningRuleAction extends ValueListAction {
	private static final long serialVersionUID = 326722012368469501L;
	
	private final WarnningRuleManager warnningRuleManager;
	
	private List<WarnningRule> warnningRuls;
	
	public ListWarnningRuleAction(WarnningRuleManager warnningRuleManager) {
		this.warnningRuleManager = warnningRuleManager;
	}
	
	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		if (this.hasIds("warnningRuleIds")) {
			this.warnningRuls = this.warnningRuleManager.loadAllWarnningRules(this.getIds("warnningRuleIds"));
		}
	}
	
	/**
	 * 处理删除的操作
	 */
	public String execute() {
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除页面选中的提醒规则
	 * @return SUCCESS
	 */
	public String delete() {
		this.warnningRuleManager.deleteAllWarnningRules(this.warnningRuls);
		this.addActionMessage(this.getText("warnningRule.delete.success"));
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "warnningRules";
	}

}
