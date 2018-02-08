package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.CodeValueDao;
import main.pojo.CodeValue;
import main.service.CodeValueService;

public class CodeValueServiceImpl implements CodeValueService {
         
	private CodeValueDao codeValueDao;
	@Override
	public List<CodeValue> getCodeValueByCvid(Long id) {
		return codeValueDao.getCodeValueByCvid(id);
	}
	

	@Override
	public CodeValue getCodeValueById(Long id) {
		// TODO Auto-generated method stub
		return codeValueDao.getCodeValueById(id);
	}

	@Override
	public Session getSuperSession() {
		// TODO Auto-generated method stub
		return codeValueDao.getSuperSession();
	}
   
	public CodeValueDao getCodeValueDao() {
		return codeValueDao;
	}
	public void setCodeValueDao(CodeValueDao codeValueDao) {
		this.codeValueDao = codeValueDao;
	}


	@Override
	public CodeValue getCodeValueByCode(String code) {
		// TODO Auto-generated method stub
		return this.codeValueDao.getCodeValueByCode(code);
	}
	
	
}
