package com.yongjun.tdms.model.runmaintenance.tooling.record;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

public class ToolingChangeDoc extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = -8542620759414436781L;
	private String fileNo;
	private String name;
	private String fileName;
	private String position;
	private String comment;
	private ToolingChangeBill changeBill;
	
	public ToolingChangeDoc() {}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Override
	public int hashCode() {
		return fileNo.hashCode();
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof ToolingChangeDoc)) { return false; }
		
		ToolingChangeDoc changeDoc = (ToolingChangeDoc)o;
		
		if (!(fileNo.equals(changeDoc.getFileNo())))  { return false; }
		return true;
	}

	public ToolingChangeBill getChangeBill() {
		return changeBill;
	}

	public void setChangeBill(ToolingChangeBill changeBill) {
		this.changeBill = changeBill;
	}

}
