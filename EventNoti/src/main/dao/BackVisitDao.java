package main.dao;

import main.pojo.BackVisit;

import org.hibernate.Session;


public interface BackVisitDao {

	public void saveBackVisit(BackVisit backVisit);
	public Session getSuperSession();

}
