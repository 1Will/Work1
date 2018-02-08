package main.dao;

import java.util.List;

import org.hibernate.Session;

import main.pojo.CodeValue;


public interface CodeValueDao
{
    public List<CodeValue> getCodeValueByCvid(Long id); //���cv_id ��ȡcodeValue����
    
    public CodeValue getCodeValueById(Long id);//���id��ȡ����ʵ��
    
    public Session getSuperSession();

    public CodeValue getCodeValueByCode(String code);

}