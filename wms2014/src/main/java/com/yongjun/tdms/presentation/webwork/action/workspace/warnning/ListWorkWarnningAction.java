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
package com.yongjun.tdms.presentation.webwork.action.workspace.warnning;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;

/**
 * <p>Title: ListWorkWarnningAction
 * <p>Description: 我的提醒查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListWorkWarnningAction extends ValueListAction {
	private static final long serialVersionUID = -5846328003010519171L;
	
	private final WorkWarnningManager workWarnningManager;
	
	//已读
	private boolean onlyRead;
	//未读
	private boolean onlyUnRead;
	//我的提醒集合
	private List<WorkWarnning> workWarnnings;
	
	public ListWorkWarnningAction(WorkWarnningManager workWarnningManager) {
		this.workWarnningManager = workWarnningManager;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "warnnings";
	}

	/**
	 * 处理已读和未读的操作
	 */
	public String execute() {
		//已读操作
		if (this.isRead()) {
			return readWorkWarnning();
		}
		//未读操作
		if (this.isUnRead()) {
			return unReadWorkWarnning();
		}
		return SUCCESS;
	}
	
	/**
	 * 置我的提醒为已读
	 * @return SUCCESS
	 */
	public String readWorkWarnning() {
		this.workWarnningManager.readAllWorkWarnnings(this.workWarnnings);
		this.addActionMessage(this.getText("warnning.read.success"));
		return SUCCESS;
	}
	
	/**
	 * 置我的提醒为未读
	 * @return SUCCESS
	 */
	public String unReadWorkWarnning() {
		this.workWarnningManager.unReadAllWorkWarnnings(this.workWarnnings);
		this.addActionMessage(this.getText("warnning.unRead.success"));
		return SUCCESS;
	}
	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		if (this.hasIds("workWarnningIds")) {
			this.workWarnnings = this.workWarnningManager.loadAllWorkWarnnings(this.getIds("workWarnningIds"));
		}
	}
    /**
     * 设置有效或失效的的参数
     */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (onlyRead) {
			map.put("onlyRead", true);
		}
		if (onlyUnRead) {
			map.put("onlyUnRead", true);
		}
		map.put("loginUser.id", this.getLoginUser().getId());
		return map;
	}
	
	public boolean isOnlyRead() {
		return onlyRead;
	}

	public void setOnlyRead(boolean onlyRead) {
		this.onlyRead = onlyRead;
	}

	public boolean isOnlyUnRead() {
		return onlyUnRead;
	}

	public void setOnlyUnRead(boolean onlyUnRead) {
		this.onlyUnRead = onlyUnRead;
	}
	
	/**
	 * 判断页面是否点击了已读按钮
	 * @return true | false
	 */
	private boolean isRead() {
		return this.hasKey("read");
	}
	
	/**
	 * 判断页面是否点击了未读按钮
	 * @return true | false
	 */
	private boolean isUnRead() {
		return this.hasKey("unRead");
	}
	
	/**
	 * 获取当前登录的用户
	 * @return User 当前登录的用户
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
