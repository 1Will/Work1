package main.service;

import java.util.List;

import main.pojo.PersonnelFiles;

import org.hibernate.Session;

public interface PersonnelFilesService {
    
	public  List<PersonnelFiles> getPersonnelFilesById(); //��ʼ����ȡ���м��� 
	public  PersonnelFiles getPersonnelFilesById(Long id); //����id��ȡ����ʵ��
	public Session getSuperSession();
	
}
