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
package com.yongjun.tdms.workflow.model.job;

import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.workflow.model.approver.JobApprover;
import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.doctype.DocType;
import com.yongjun.tdms.workflow.model.signlog.SignatureLog;

/**
 * @author qs
 * @version $Id: Job.java 7925 2007-10-22 02:37:55Z qsun $
 */
public class Job extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 2708674758649488876L;
	private Long docIdentify;
	private String docNo;
	private String name;
	private String docName;
	private String comment;
	private DocType docType;  
	private DocState docState;
	private Set<JobApprover> approvers = new HashSet<JobApprover>();
	private Set<SignatureLog> signatureLogs = new HashSet<SignatureLog>();
	
	public Job() {
		
	}
	
	public String getDocName() {
		return docName;
	}
	
	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	public Set<SignatureLog> getSignatureLogs() {
		return signatureLogs;
	}
	
	public void setSignatureLogs(Set<SignatureLog> signatureLogs) {
		this.signatureLogs = signatureLogs;
	}
	
	public Set<JobApprover> getApprovers() {
		return approvers;
	}
	
	public void setApprovers(Set<JobApprover> approvers) {
		this.approvers = approvers;
	}
	
	public DocState getDocState() {
		return docState;
	}
	
	public void setDocState(DocState docState) {
		this.docState = docState;
	}
	
	public DocType getDocType() {
		return docType;
	}
	
	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getName() {
		return name;		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getDocIdentify() {
		return docIdentify;
	}

	public void setDocIdentify(Long docIdentify) {
		this.docIdentify = docIdentify;
	}
	
	public String getDocNo() {
		return docNo;
	}
	
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof Job)) { return false; }		
		return false;
	}

}
