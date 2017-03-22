package com.yongjun.tdms.dao.codevalue;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.codevalue.CodeValue;

public interface CodeValueDao {
	public void storeCodeValue(CodeValue codeValue);
	
	public void deleteCodeValue(CodeValue codeValue);
	
	public void deleteAllCodeValue(Collection<CodeValue> codeValues);
	
	public List<CodeValue> loadAllCodeValue(Long[] codeValueIds);
	
	public CodeValue loadCodeValue(Long codeValueId);
	
	public List<CodeValue> loadAllCodeValue();
	
	public List<CodeValue> loadByKey(String keyName,Object codeValue)throws DaoException;
}
