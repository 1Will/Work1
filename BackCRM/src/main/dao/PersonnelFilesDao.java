package main.dao;


import java.util.List;

import main.pojo.PersonnelFiles;

import org.hibernate.Session;


public interface PersonnelFilesDao {

	public  List<PersonnelFiles> getAllPersonnelFiles(); //��ʼ����ȡ���м��� 
	
	public  List<PersonnelFiles> getPerFilesByBusinessType(Long businessType); //����ҵ������ ��ȡ���¼���
	
	public  List<PersonnelFiles> getPersonnelFilesByName(String name); //���ݵ�¼�û��� ��ȡ��������

	public  List<PersonnelFiles> getPersonnelFilesByCode(String code); //����Ա�����code ��ȡ��������
	
	public  PersonnelFiles getPersonnelFilesById(Long id); //����id��ȡ����ʵ��
	
	public Session getSuperSession();
}
