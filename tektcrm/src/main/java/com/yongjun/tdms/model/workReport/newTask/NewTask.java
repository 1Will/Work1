package com.yongjun.tdms.model.workReport.newTask;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;

/**
 * 钉钉
 */
public class NewTask extends BaseInfoEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private Date startTime;
	private Date endTime;
	private CodeValue state;
	private String content;
	private String time;//提前提醒时间
	private String userDo;//具体执行人id
	private String doString;//保存执行人
	private String userCopy;//具体抄送人id
	private String copyString;//保存执行人
	private String isSaved;//存在并且等于0，，方可提交
	
	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public CodeValue getState() {
		return state;
	}

	public String getContent() {
		return content;
	}

	public String getUserDo() {
		return userDo;
	}

	public String getDoString() {
		return doString;
	}

	public String getUserCopy() {
		return userCopy;
	}

	public String getCopyString() {
		return copyString;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setState(CodeValue state) {
		this.state = state;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserDo(String userDo) {
		this.userDo = userDo;
	}

	public void setDoString(String doString) {
		this.doString = doString;
	}

	public void setUserCopy(String userCopy) {
		this.userCopy = userCopy;
	}

	public void setCopyString(String copyString) {
		this.copyString = copyString;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}

}
