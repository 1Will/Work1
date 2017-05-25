package main.service;

import java.util.List;

import main.pojo.PersonnelFiles;

import org.hibernate.Session;

public interface PersonnelFilesService {
    
	public  List<PersonnelFiles> getAllPersonnelFiles(); //��ʼ����ȡ���м��� 
	public  PersonnelFiles getPersonnelFilesById(Long id); //����id��ȡ����ʵ��
	
	public  List<PersonnelFiles> getPersonnelFilesByName(String name); //���ݵ�¼�û��� ��ȡ��������
	
	public  List<PersonnelFiles> getPersonnelFilesByCode(String code); //����Ա�����code ��ȡ��������
	
	public Session getSuperSession();
	
}
