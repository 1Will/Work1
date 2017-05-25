package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.UsersInfoDao;
import main.pojo.UsersInfo;
import main.service.UsersInfoService;

public class UsersInfoServiceImpl implements UsersInfoService{

	 private  UsersInfoDao usersInfoDao;
	@Override
	public List<UsersInfo> getAllUsersInfoByEnabled() {
		return usersInfoDao.getAllUsersInfoByEnabled();
	}

	@Override
	public List<UsersInfo> getAllUsersInfoByOpenId() {
		return usersInfoDao.getAllUsersInfoByOpenId();
	}
	
	@Override
	public UsersInfo getUsersInfoById(Long id) {
		return usersInfoDao.getUsersInfoById(id);
	}

	@Override
	public List<UsersInfo> getUsersInfoByCode(String code) {
		return usersInfoDao.getUsersInfoByCode(code);
	}
	

	@Override
	public Session getSuperSession() {
		return usersInfoDao.getSuperSession();
	}

	
	public UsersInfoDao getUsersInfoDao() {
		return usersInfoDao;
	}

	public void setUsersInfoDao(UsersInfoDao usersInfoDao) {
		this.usersInfoDao = usersInfoDao;
	}


	
	
}
