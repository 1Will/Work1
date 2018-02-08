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
package com.yongjun.tdms.presentation.webwork.action.notice;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.notice.ReadStatus;
import com.yongjun.pluto.model.notice.ReceviceNotice;
import com.yongjun.pluto.model.notice.SendStatus;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;

/**
 * <p>Title: ListReceviceNoticeAction
 * <p>Description: 接收通知查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListReceviceNoticeAction extends ValueListAction {
	private static final long serialVersionUID = -8621959686198720642L;
	
	private final ReceviceNoticeManager receviceNoticeManager;
	
	private List<ReceviceNotice> receviceNotices;
	
	public ListReceviceNoticeAction(ReceviceNoticeManager receviceNoticeManager) {
		this.receviceNoticeManager = receviceNoticeManager;
	}
	
	
	public void prepare() {
		if (this.hasIds("receviceNoticeIds")) {
			
			this.receviceNotices = this.receviceNoticeManager.loadReceviceNotices(this.getIds("receviceNoticeIds"));
		}
	}
	
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		if (this.isDelete()) {
			return delete();
		}
		if(this.hasKey("unread")){
			return this.unread();
		}
		return SUCCESS;
	}
	public String unread(){
		this.receviceNoticeManager.unreadAllReceviceNotices(receviceNotices);
		return SUCCESS;
	}
	public List<LabelValue> getStatus() {  //获得发送状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(ReadStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public String disabled() {
		this.receviceNoticeManager.disabledAllReceviceNotices(receviceNotices);
		this.addActionMessage(this.getText("receviceNotice.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.receviceNoticeManager.enabledAllReceviceNotices(receviceNotices);
		this.addActionMessage(this.getText("receviceNotice.enabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 删除选中的收通知
	 * @return 
	 */
	public String delete() {
		this.receviceNoticeManager.deleteReceviceNotices(receviceNotices);
		this.addActionMessage(this.getText("receviceNotice.delete.success"));
		return SUCCESS;
	}
   
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	
	@Override
	protected String getAdapterName() {
		return "receviceNotices";
	}
	/**
	 * 获取当前登录的用户
	 * @return User 当前登录的用户
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

}
