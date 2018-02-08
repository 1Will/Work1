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
package com.yongjun.tdms.workflow.service.doctype.pojo;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.workflow.WorkFlowUtil;
import com.yongjun.tdms.workflow.dao.doctype.DocTypeDao;
import com.yongjun.tdms.workflow.model.doctype.DocType;
import com.yongjun.tdms.workflow.service.doctype.WfDocTypeManager;

/**
 * @author qs
 * @version $Id: DefaultWfDocTypeManager.java 8064 2007-10-26 08:12:04Z qsun $
 */
public class DefaultWfDocTypeManager extends BaseManager implements
		WfDocTypeManager {
	private final DocTypeDao docTypeDao;
	private List<String> validDocTypes;
	
	public DefaultWfDocTypeManager(DocTypeDao docTypeDao) {
		this.docTypeDao = docTypeDao;
	}
	
	public void setValidDocTypes(List<String> validDocTypes) {
		this.validDocTypes = validDocTypes;
	}
	
	public List<String> getValidDocTypes() {
		return validDocTypes;
	}
	
	public boolean isValidDocType(BaseInfoEntity pojo) {
		String clzName = WorkFlowUtil.getClzID(pojo);
		if (StringUtils.isEmpty(clzName)) {
			return false;
		}
		
		for (String docType : validDocTypes) {
			if (clzName.equals(docType)) {
				return true;
			}
		}
		return false;
	}
	
	public DocType loadDocTypeByCode(String clzId) {
		return docTypeDao.loadDocTypeByCode(clzId);
	}

	public List<DocType> createSelectDocType(String label) {
		DocType docType = new DocType();
		
		docType.setId(-1L);
		docType.setName(label);
		List<DocType> tmpList = this.docTypeDao.loadAllDocType();
		tmpList.add(0,docType);
		return tmpList;
	}

}
