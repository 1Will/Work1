package com.yongjun.tdms.presentation.webwork.action.notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.notice.ReadStatus;
import com.yongjun.tdms.model.notice.ReceviceNotice;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;

public class ListReceviceNoticeAction extends ValueListAction {
	private static final long serialVersionUID = -8621959686198720642L;
	private final ReceviceNoticeManager receviceNoticeManager;
	private List<ReceviceNotice> receviceNotices;

	public ListReceviceNoticeAction(ReceviceNoticeManager receviceNoticeManager) {
		this.receviceNoticeManager = receviceNoticeManager;
	}

	public void prepare() {
		if (hasIds("receviceNoticeIds"))
			this.receviceNotices = this.receviceNoticeManager.loadReceviceNotices(getIds("receviceNoticeIds"));
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
		if (hasKey("unread")) {
			return unread();
		}
		return "success";
	}

	public String unread() {
		this.receviceNoticeManager.unreadAllReceviceNotices(this.receviceNotices);
		return "success";
	}

	public List<LabelValue> getStatus() {
		LabelValue[] arrays = wrapEnum(ReadStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(), getText("select.option.all"));
		List tmp = new ArrayList();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String disabled() {
		this.receviceNoticeManager.disabledAllReceviceNotices(this.receviceNotices);
		addActionMessage(getText("receviceNotice.disabled.success"));
		return "success";
	}

	public String enabled() {
		this.receviceNoticeManager.enabledAllReceviceNotices(this.receviceNotices);
		addActionMessage(getText("receviceNotice.enabled.success"));
		return "success";
	}

	public String delete() {
		this.receviceNoticeManager.deleteReceviceNotices(this.receviceNotices);
		addActionMessage(getText("receviceNotice.delete.success"));
		return "success";
	}

	private boolean isEnabled() {
		return hasKey("enabled");
	}

	protected String getAdapterName() {
		return "receviceNotices";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (!hasId("onlyInvalid")) {
			map.put("onlyValid", Boolean.valueOf(true));
		}
		if (this.request.getParameter("receviceNotice.id") != null) {
			int rNId = Integer.valueOf(this.request.getParameter("receviceNotice.id")).intValue();
			map.put("receviceNotice.id", Integer.valueOf(rNId));
		}
		
		map.put("loginUser.id", getLoginUser().getId());
		if ("-1".equals(this.request.getParameter("readStatus"))) {
			map.remove("readStatus");
		}
		
		return map;
	}

	public User getLoginUser() {
		return this.userManager.getUser();
	}
}
