package main.service;

import java.util.List;

import main.pojo.PersonnelFiles;

import org.hibernate.Session;

public interface PersonnelFilesService {
    
	public  List<PersonnelFiles> getAllPersonnelFiles(); //初始化获取所有集合 
	public  PersonnelFiles getPersonnelFilesById(Long id); //根据id获取单个实例
	
	public  List<PersonnelFiles> getPersonnelFilesByName(String name); //根据登录用户名 获取单个个体
	
	public  List<PersonnelFiles> getPersonnelFilesByCode(String code); //根据员工编号code 获取单个个体
	
	public Session getSuperSession();
	
}
