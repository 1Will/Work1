package com.yongjun.tdms.presentation.webwork.action.notice;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.notice.SendNotice;
import com.yongjun.pluto.model.notice.SendStatus;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.service.notice.SendNoticeManager;

public class ListNoticeAction extends ValueListAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SendNoticeManager sendNoticeManager;
	private List<SendNotice > sendNotices;
    public ListNoticeAction(SendNoticeManager sendNoticeManager){
    	this.sendNoticeManager=sendNoticeManager;
    	
    }
	public void prepare() throws Exception {
		if (this.sendNotices == null && this.hasIds("sendNoticeIds")) {
			this.sendNotices = this.sendNoticeManager.loadSendNotices(this
					.getIds("sendNoticeIds"));
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
		return SUCCESS;
	}
	public String disabled() {
		this.sendNoticeManager.disabledAllSendNotices(sendNotices);
		this.addActionMessage(this.getText("sendNotice.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.sendNoticeManager.enabledAllSendNotices(sendNotices);
		this.addActionMessage(this.getText("sendNotice.enabled.success"));
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	public String delete() {
		this.sendNoticeManager.deleteSendNotices(sendNotices);
		this.addActionMessage(this.getText("sendNotice.delete.success"));
		return SUCCESS;
	}
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	public List<LabelValue> getStatus() {  //获得发送状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(SendStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	@Override
	protected String getAdapterName() {
		
		return "notice";
	}
	public List<SendNotice> getSendNotices() {
		return sendNotices;
	}
	public void setSendNotices(List<SendNotice> sendNotices) {
		this.sendNotices = sendNotices;
	}
	/**
	 * 获取当前登录的用户
	 * @return User 当前登录的用户
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
