package main.dao;

import java.util.List;

import main.pojo.ContactArchives;

import org.hibernate.Session;


public interface ContactArchivesDao {

	public void saveContactArchives(ContactArchives contactArchives);
	public  ContactArchives getContactArchivesById(Long id); //获取单条数据集合
//	public  List<ContactArchives> getAllContactArchivesById(Long[] ids);//获取多条集合
    public  List<ContactArchives> getAllContactArchives(); //直接获取所有
	public Session getSuperSession();
public  List<ContactArchives> getAllContactArchives(Long id) ;

}
