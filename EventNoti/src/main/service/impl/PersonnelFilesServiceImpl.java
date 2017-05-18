package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.PersonnelFilesDao;
import main.pojo.PersonnelFiles;
import main.service.PersonnelFilesService;

public class PersonnelFilesServiceImpl implements PersonnelFilesService {
    
	private PersonnelFilesDao personnelFilesDao;
	@Override
	public List<PersonnelFiles> getPersonnelFilesById() {
		return personnelFilesDao.getPersonnelFilesById();
	}

	@Override
	public PersonnelFiles getPersonnelFilesById(Long id) {
		return personnelFilesDao.getPersonnelFilesById(id);
	}

	@Override
	public Session getSuperSession() {
		return personnelFilesDao.getSuperSession();
	}

	public PersonnelFilesDao getPersonnelFilesDao() {
		return personnelFilesDao;
	}

	public void setPersonnelFilesDao(PersonnelFilesDao personnelFilesDao) {
		this.personnelFilesDao = personnelFilesDao;
	}
	
	

}
