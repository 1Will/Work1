package main.service;

import java.util.List;

import main.pojo.UsersInfo;

import org.hibernate.Session;

public interface UsersInfoService {

		public  List<UsersInfo> getAllUsersInfoByEnabled(); //初始化获取所有在职人员集合 
		
		public  List<UsersInfo> getAllUsersInfoByOpenId(); //初始化获取所有有微信ID人员集合 
		
		public  UsersInfo getUsersInfoById(Long id); //根据id获取单个实例
		
		public  List<UsersInfo> getUsersInfoByCode(String code); //根据员工编号code 获取单个个体
		
		public Session getSuperSession();

	
}
