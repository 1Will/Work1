package main.service;

import java.util.List;

import main.pojo.CustomerInfo;

import org.hibernate.Session;

public interface CustomerInfoService {

	public void saveCustomerInfo(CustomerInfo customerInfo);

	public  CustomerInfo getCustomerInfoById(Long id);
	
	public List<CustomerInfo> getAllCustomerInfo();

	public Object findLastCustomerId();//获取当前数据表中最大id

	public Session getSuperSession();

	public List<CustomerInfo> getAllCustomer();
	public CustomerInfo getById(Long id);
	public  List<CustomerInfo> getCustomerInfoByName(String name) ;

}
