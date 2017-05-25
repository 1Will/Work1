package main.dao.impl;

import java.util.List;

import main.dao.UsersInfoDao;
import main.pojo.PersonnelFiles;
import main.pojo.UsersInfo;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UsersInfoDaoImpl extends HibernateDaoSupport implements UsersInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UsersInfo> getAllUsersInfoByEnabled() {
		String hql="select u from UsersInfo u where u.enabled=1";
		System.out.println("===hql==="+hql);
		return this.getHibernateTemplate().find(hql);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsersInfo> getAllUsersInfoByOpenId() {
		String hql="select u from UsersInfo u where u.openId is not null";
		System.out.println("===hql==="+hql);
		return this.getHibernateTemplate().find(hql);
	}
	
	

	@SuppressWarnings("unchecked")
	public  UsersInfo getUsersInfoById(Long id) {
		
		UsersInfo usersInfo=null;
		try {
			usersInfo=(UsersInfo)getSession().load(UsersInfo.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return usersInfo;
	}
	
	
	//根据员工编号 获取人事信息
		@SuppressWarnings("unchecked")
		@Override
		public List<UsersInfo> getUsersInfoByCode(String code) {
			String hql="select u from UsersInfo u where u.code= '"+code+"'";
			return this.getHibernateTemplate().find(hql);
		}
	
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}


	

}
