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
package com.yongjun.tdms.service.base.codevalue;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author qs
 * @version $Id: CodeValueManager.java 11325 2008-03-15 06:48:17Z wzou $
 */
@Transactional(readOnly = true)
public interface CodeValueManager {
	List<CodeValue> loadAllValuesByCodeId(Long id);
	
	CodeValue loadCodeTypeByReferCode(String referCode);
	
	List<CodeValue> loadAllValues();
	
	List<CodeValue> LoadAllValuesByCode(String code);
	
	@Transactional
	void deleteAllCodeValue(Collection<CodeValue> codeValueDetails);
	
	List<CodeValue> loadAllValues(Long[] codeValueDetailIds);
	
	CodeValue loadCodeValue(Long id);
	
	@Transactional
	void storeCodeValue(CodeValue codeValue);
	
	List<CodeValue> createSelectCodeValues(String label,  String code);
	
	List<CodeValue> createSelectCodeValues(String label,  String code, String flag);
	
	List<CodeValue> createSelectCodeValuesOfDiscard(String label);
	
	CodeValue loadCodeTypeByCode(String code);
	
	CodeValue loadCodeTypeByRealCode(String realCode);
	
	@Transactional
	void disabledAllCodeValues(Collection<CodeValue> codeValues);
	
	@Transactional
	void enabledAllChecks(Collection<CodeValue> codeValues);
	
	List<Long> LoadAllIdsByCode(String code);
	
	//判断编码名称是否存在
	int getCodeValueByValue(String codeValue);
	//判断编码明细编码名称是否存在
	int getcodeValueDetailByValueCount(String codeValue,Long id);
}
