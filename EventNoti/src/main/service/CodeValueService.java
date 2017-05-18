package main.service;

import java.util.List;

import main.pojo.CodeValue;

public interface CodeValueService {
	 public List<CodeValue> getCodeValueByCvid(Long id); //根据cv_id 获取codeValue集合

}
