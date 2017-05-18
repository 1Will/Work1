package main.service;

import main.pojo.BackVisit;

import org.hibernate.Session;

public interface BackVisitService {

	public void saveBackVisit(BackVisit backVisit);



	public Session getSuperSession();
	
	

}
