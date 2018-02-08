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

import java.util.Date;

import com.yongjun.tdms.workflow.model.docstate.DocState;
import com.yongjun.tdms.workflow.model.doctype.DocType;

/**
 * @author qs
 * @version $Id: QueryJobOfMineList.java 8066 2007-10-26 08:13:01Z qsun $
 */
public class QueryJobOfMineList {
	private Long id;
	private Long docIdentify;
	private String docNo;
	private String name;
	private String docName;
	private String comment;
	private DocType docType;
	private DocState docState;
	private Long approveId;
	private boolean finalApr;
	private Date lastModifiedTime;
	private DocState myDocState;
	private String submittor;
	private Date submittedTime;
	
	public QueryJobOfMineList(Long id, Long docIdentify, String docNo, String name, String docName,String comment, 
			DocType docType, DocState docState, Long approveId, boolean finalApr,Date lastModifiedTime,
			DocState myDocState,String submittor,Date submittedTime) {
		this.id = id;
		this.docIdentify = docIdentify;
		this.docNo = docNo;
		this.name = name;
		this.docName = docName;
		this.comment = comment;
		this.docType = docType;
		this.docState = docState;
		this.approveId = approveId;
		this.finalApr = finalApr;
		this.lastModifiedTime = lastModifiedTime;
		this.myDocState = myDocState;
		this.submittor = submittor;
		this.submittedTime = submittedTime;
	}

	public Long getApproveId() {
		return approveId;
	}

	public void setApproveId(Long approveId) {
		this.approveId = approveId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFinalApr() {
		return finalApr;
	}

	public void setFinalApr(boolean finalApr) {
		this.finalApr = finalApr;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public DocState getMyDocState() {
		return myDocState;
	}

	public void setMyDocState(DocState myDocState) {
		this.myDocState = myDocState;
	}

	public String getSubmittor() {
		return submittor;
	}

	public void setSubmittor(String submittor) {
		this.submittor = submittor;
	}

	public Date getSubmittedTime() {
		return submittedTime;
	}

	public void setSubmittedTime(Date submittedTime) {
		this.submittedTime = submittedTime;
	}
}
