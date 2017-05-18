package main.service.impl;

import java.util.List;

import main.dao.CustomerInfoDao;
import main.pojo.CustomerInfo;
import main.service.CustomerInfoService;

import org.hibernate.Session;

public class CustomerInfoServiceImpl implements CustomerInfoService {
	
	private CustomerInfoDao customerInfoDao;


	
	public void saveCustomerInfo(CustomerInfo customerInfo) {
		customerInfoDao.saveCustomerInfo(customerInfo);
		
	}

	public  CustomerInfo getCustomerInfoById(Long id){
		return customerInfoDao.getCustomerInfoById(id);
	}

	public  List<CustomerInfo> getAllCustomerInfo(){
		return customerInfoDao.getAllCustomerInfo();
	}
	
	@Override //��ȡ���id
	public Object findLastCustomerId() {
		return customerInfoDao.findLastCustomerId();
	}

	
	public Session getSuperSession() {
		return customerInfoDao.getSuperSession();
	}

	
	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}

	public void setCustomerInfoDao(CustomerInfoDao customerInfoDao) {
		this.customerInfoDao = customerInfoDao;
	}

	public  List<CustomerInfo> getAllCustomer(){
		return customerInfoDao.getAllCustomer();
	}
	public CustomerInfo getById(Long id)
	{
		return customerInfoDao.getById(id);
	}
	public  List<CustomerInfo> getCustomerInfoByName(String name) 
	{
		return customerInfoDao.getCustomerInfoByName(name);
	}

}
