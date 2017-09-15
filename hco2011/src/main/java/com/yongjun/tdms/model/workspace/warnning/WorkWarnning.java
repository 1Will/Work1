package com.yongjun.tdms.model.workspace.warnning;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.security.User;
import java.util.Date;

public class WorkWarnning extends BaseInfoEntity {
	private static final long serialVersionUID = 448275123928744135L;
	private String name;
	private String type;
	private String content;
	private Date warnningDate;
	private boolean readFlag = false;
	private User warnedPeople;
	private Long remindObjectId;
	private String url;

	public int hashCode() {
		return getId().hashCode();
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof WorkWarnning)) {
			return false;
		}
		return false;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getWarnedPeople() {
		return this.warnedPeople;
	}

	public void setWarnedPeople(User warnedPeople) {
		this.warnedPeople = warnedPeople;
	}

	public Date getWarnningDate() {
		return this.warnningDate;
	}

	public void setWarnningDate(Date warnningDate) {
		this.warnningDate = warnningDate;
	}

	public boolean isReadFlag() {
		return this.readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}

	public Long getRemindObjectId() {
		return this.remindObjectId;
	}

	public void setRemindObjectId(Long remindObjectId) {
		this.remindObjectId = remindObjectId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
