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
package com.yongjun.tdms.service.codevalue;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.codevalue.CodeValue;

/**
 * @author qs
 * @version $Id: CodeValueManager.java 11325 2008-03-15 06:48:17Z wzou $
 */
public interface CodeValueManager {
	
	public void storeCodeValue(CodeValue codeValue);
	
	public void deleteCodeValue(CodeValue codeValue);
	
	public void deleteAllCodeValue(Collection<CodeValue> codeValues);
	
	public List<CodeValue> loadAllCodeValue(Long[] codeValueIds);
	
	public CodeValue loadCodeValue(Long codeValueId);
	
	public List<CodeValue> loadAllCodeValue();
	
	public List<CodeValue> loadByKey(String keyName,Object codeValue)throws DaoException;
	
	void disabledAllCodeValues(Collection<CodeValue> codeValues);
	
	void enabledAllChecks(Collection<CodeValue> codeValues);
}
