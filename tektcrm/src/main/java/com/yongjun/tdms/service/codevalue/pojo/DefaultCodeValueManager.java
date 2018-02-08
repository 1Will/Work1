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
package com.yongjun.tdms.service.codevalue.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;

import com.yongjun.tdms.dao.codevalue.CodeValueDao;
import com.yongjun.tdms.model.codevalue.CodeValue;
import com.yongjun.tdms.service.codevalue.CodeValueManager;

/**
 * @author qs
 * @version $Id: DefaultCodeValueManager.java 11325 2008-03-15 06:48:17Z wzou $
 */
public class DefaultCodeValueManager extends BaseManager implements
		CodeValueManager {
	private final CodeValueDao codeValueDao;
	
	public DefaultCodeValueManager(CodeValueDao codeValueDao){
		this.codeValueDao = codeValueDao;
	}

	public void deleteAllCodeValue(Collection<CodeValue> codeValues) {
		this.codeValueDao.deleteAllCodeValue(codeValues);
	}

	public void deleteCodeValue(CodeValue codeValue) {
		this.codeValueDao.deleteCodeValue(codeValue);
	}

	public List<CodeValue> loadAllCodeValue(Long[] codeValueIds) {
		return this.codeValueDao.loadAllCodeValue(codeValueIds);
	}

	public List<CodeValue> loadAllCodeValue() {
		return this.codeValueDao.loadAllCodeValue();
	}

	public List<CodeValue> loadByKey(String keyName,Object codeValue) throws DaoException {
		return this.codeValueDao.loadByKey(keyName,codeValue);
	}

	public CodeValue loadCodeValue(Long codeValueId) {
		return this.codeValueDao.loadCodeValue(codeValueId);
	}

	public void storeCodeValue(CodeValue codeValue) {
		this.codeValueDao.storeCodeValue(codeValue);
	}

	public void disabledAllCodeValues(Collection<CodeValue> codeValues) {
		for (CodeValue codeValue : codeValues) {
			codeValue.setDisabled(true);
			codeValueDao.storeCodeValue(codeValue);
		}
	}

	public void enabledAllChecks(Collection<CodeValue> codeValues) {
		for (CodeValue codeValue : codeValues) {
			codeValue.setDisabled(false);
			codeValueDao.storeCodeValue(codeValue);
		}
	}
}
