package main.dao;

import java.util.List;

import org.hibernate.Session;

import main.pojo.CodeValue;


public interface CodeValueDao
{
    public List<CodeValue> getCodeValueByCvid(Long id); //根据cv_id 获取codeValue集合
    public Session getSuperSession();
}