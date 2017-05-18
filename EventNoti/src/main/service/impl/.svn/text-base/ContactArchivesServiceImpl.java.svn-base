package main.service.impl;


import java.util.List;

import main.dao.ContactArchivesDao;
import main.pojo.ContactArchives;
import main.service.ContactArchivesService;

import org.hibernate.Session;



public class ContactArchivesServiceImpl implements ContactArchivesService {

	private ContactArchivesDao contactArchivesDao;

	

	@Override
	public void saveContsctArchives(ContactArchives contactArchives) {

		contactArchivesDao.saveContactArchives(contactArchives);
	}

	@Override
	public ContactArchives getContactArchivesById(Long id) {
		
		return contactArchivesDao.getContactArchivesById(id);
	}
    
	@Override
	public List<ContactArchives> getAllContactArchives(){
		return contactArchivesDao.getAllContactArchives();
	}
	
	@Override
	public Session getSuperSession() {
		return contactArchivesDao.getSuperSession();
	}

	
	public ContactArchivesDao getContactArchivesDao() {
		return contactArchivesDao;
	}

	public void setContactArchivesDao(ContactArchivesDao contactArchivesDao) {
		this.contactArchivesDao = contactArchivesDao;
	}
	public  List<ContactArchives> getAllContactArchives(Long id){
		return contactArchivesDao.getAllContactArchives( id);
	}

}
