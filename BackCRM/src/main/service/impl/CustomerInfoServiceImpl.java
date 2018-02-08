package main.service.impl;

import java.util.List;

import main.dao.CustomerInfoDao;
import main.pojo.CustomerInfo;
import main.service.CustomerInfoService;

import org.hibernate.Session;

public class CustomerInfoServiceImpl implements CustomerInfoService {
	
	private CustomerInfoDao customerInfoDao;

	public void updateCustomerInfo(CustomerInfo customerInfo) {
		customerInfoDao.updateCustomerInfo(customerInfo);
		
	}
	public void saveCustomerInfo(CustomerInfo customerInfo) {
		customerInfoDao.saveCustomerInfo(customerInfo);
		
	}


	public  CustomerInfo getCustomerInfoById(Long id){
		return customerInfoDao.getCustomerInfoById(id);
	}

	public  List<CustomerInfo> getAllCustomerInfo(){
		return customerInfoDao.getAllCustomerInfo();
	}
	
	@Override //获取最大id
	public Object findLastCustomerId() {
		return customerInfoDao.findLastCustomerId();
	}

	
	public Session getSuperSession() {
		return customerInfoDao.getSuperSession();
	}


	public  List<CustomerInfo> getAllCustomer(){
		return customerInfoDao.getAllCustomer();
	}
	public CustomerInfo getById(Long id)
	{
		return customerInfoDao.getById(id);
	}
	
	public Long getMaxId(){
		return customerInfoDao.getMaxId();
	}
	
	public  List<CustomerInfo> getCustomerInfoByName(String name) 
	{
		return customerInfoDao.getCustomerInfoByName(name);
	}
	
	@Override
	public List<CustomerInfo> getCustomerInfosByNameAndBusinessType(
			String name, Long businessType) {
		return customerInfoDao.getCustomerInfosByNameAndBusinessType(name, businessType);
	}
	
	@Override
	public List<CustomerInfo> getCustomerInfoByDate(String date, String name) {
		return customerInfoDao.getCustomerInfoByDate(date, name);
	}
	
	
	
	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}
	
	public void setCustomerInfoDao(CustomerInfoDao customerInfoDao) {
		this.customerInfoDao = customerInfoDao;
	}

	
	
}
