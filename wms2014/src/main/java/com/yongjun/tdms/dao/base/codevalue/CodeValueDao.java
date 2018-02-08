package com.yongjun.tdms.dao.base.codevalue;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.codevalue.CodeValue;

/**
 * @author qs
 * @version $Id: CodeValueDao.java 11319 2008-03-14 08:25:24Z wzou $
 */
public interface CodeValueDao {
	List<CodeValue> loadAllValuesByCodeId(Long id);
	
	List<Long> loadAllIdsByCodeId(Long id);
	
	CodeValue loadCodeTypeByCode(String code);
	
	List<CodeValue> loadAllValues();
	
	void storeCodeValue(CodeValue codeValue);

	CodeValue loadCodeValue(Long id);
	
	CodeValue loadCodeTypeByRealCode(String realCode);
	
	CodeValue loadCodeTypeByReferCode(String referCode);
	
	void deleteAllCodeValue(Collection<CodeValue> codeValueDetails);
	
	List<CodeValue> loadAllValues(Long[] codeValueDetailIds);
	
	//判断编码名称是否存在
	int getCodeValueByValue(String codeValue);
	//判断编码明细中编码名称是否存在 
	int getcodeValueDetailByValueCount(String codeValue,Long id);
}
