package main.service;


import java.util.List;

import main.pojo.ContactArchives;

import org.hibernate.Session;

public interface ContactArchivesService {

	public void saveContsctArchives(ContactArchives contsctArchives);

	public  ContactArchives getContactArchivesById(Long id);
    
	public List<ContactArchives> getAllContactArchives();
	
	public Session getSuperSession();
	
	public  List<ContactArchives> getAllContactArchives(Long id);

	public List<ContactArchives> getContactArchivesByDate(String date,String name);//根据日期和姓名返回ContactArchives集合

}
