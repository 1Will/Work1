package com.yongjun.tdms.presentation.webwork.action.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.notice.SendNotice;
import com.yongjun.tdms.model.notice.SendStatus;
import com.yongjun.tdms.service.notice.SendNoticeManager;

public class ListNoticeAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final SendNoticeManager sendNoticeManager;
	private List<SendNotice> sendNotices;

	public ListNoticeAction(SendNoticeManager sendNoticeManager) {
		this.sendNoticeManager = sendNoticeManager;
	}

	public void prepare() throws Exception {
		if ((this.sendNotices == null) && (hasIds("sendNoticeIds")))
			this.sendNotices = this.sendNoticeManager.loadSendNotices(getIds("sendNoticeIds"));
	}

	public String execute() {
		if (isDisabled()) {
			return disabled();
		}
		if (isEnabled()) {
			return enabled();
		}
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public String disabled() {
		this.sendNoticeManager.disabledAllSendNotices(this.sendNotices);
		addActionMessage(getText("sendNotice.disabled.success"));
		return "success";
	}

	public String enabled() {
		this.sendNoticeManager.enabledAllSendNotices(this.sendNotices);
		addActionMessage(getText("sendNotice.enabled.success"));
		return "success";
	}

	public String delete() {
		this.sendNoticeManager.deleteSendNotices(this.sendNotices);
		addActionMessage(getText("sendNotice.delete.success"));
		return "success";
	}

	private boolean isEnabled() {
		return hasKey("enabled");
	}

	public List<LabelValue> getStatus() {
		LabelValue[] arrays = wrapEnum(SendStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(), getText("select.option.all"));
		List tmp = new ArrayList();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	protected String getAdapterName() {
		return "noticeHQL";
	}

	public List<SendNotice> getSendNotices() {
		return this.sendNotices;
	}

	public void setSendNotices(List<SendNotice> sendNotices) {
		this.sendNotices = sendNotices;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}

	protected Map getRequestParameterMap() {
		System.out.println("************************");
		Map map = super.getRequestParameterMap();
		User user = this.userManager.getUser();
		if (!hasId("onlyInvalid")) {
			map.put("sendUser.id", user.getId());
			map.put("onlyValid", Boolean.valueOf(true));
		}
		
		if ("-1".equals(this.request.getParameter("sendStatus"))) {
			map.remove("sendStatus");
		}
		return map;
		
	}
}
