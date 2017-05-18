package main.service.impl;

import java.util.List;

import main.dao.CodeValueDao;
import main.pojo.CodeValue;
import main.service.CodeValueService;

public class CodeValueServiceImpl implements CodeValueService {
         private CodeValueDao codeValueDao;
	@Override
	public List<CodeValue> getCodeValueByCvid(Long id) {
		return codeValueDao.getCodeValueByCvid(id);
	}
	
	public CodeValueDao getCodeValueDao() {
		return codeValueDao;
	}
	public void setCodeValueDao(CodeValueDao codeValueDao) {
		this.codeValueDao = codeValueDao;
	}
   
	
	
}
