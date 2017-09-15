package com.yongjun.tdms.model.base.event;

import com.yongjun.pluto.model.BaseInfoEntity;

public class EventNew extends BaseInfoEntity {
	private static final long serialVersionUID = -1330109689813445448L;
	private Long id;
	private EventType eventType;
	private String name;
	private Long status;
	private Long orgId;
	private Long docId;
	private String userId;
	private String docType;
	private String moreinfo;
	private String effectflag;
	private String dealFlag = "0";//0表示没有被扫描

	public int hashCode() {
		return 0;
	}

	public boolean equals(Object arg0) {
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getMoreinfo() {
		return moreinfo;
	}

	public void setMoreinfo(String moreinfo) {
		this.moreinfo = moreinfo;
	}

	public String getEffectflag() {
		return effectflag;
	}

	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}

	public String getDealFlag() {
		return dealFlag;
	}

	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}

}
