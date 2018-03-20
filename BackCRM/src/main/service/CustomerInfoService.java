package main.service;

import java.util.List;

import main.pojo.CustomerInfo;

import org.hibernate.Session;

public interface CustomerInfoService {

	public void saveCustomerInfo(CustomerInfo customerInfo);
	
	public void updateCustomerInfo(CustomerInfo customerInfo); //����
	
	public  CustomerInfo getCustomerInfoById(Long id);
	
	public List<CustomerInfo> getAllCustomerInfo();

	public Object findLastCustomerId();//��ȡ��ǰ���ݱ������id

	public Session getSuperSession();
	
	public Long getMaxId();

	public List<CustomerInfo> getAllCustomer();
	
	public CustomerInfo getById(Long id);
	
	public  List<CustomerInfo> getCustomerInfoByName(String name) ;
	
    public  List<CustomerInfo> getCustomerInfosByNameAndBusinessType(String name,Long businessType) ;
	
	public List<CustomerInfo> getCustomerInfoByDate(String date,String name);//�������ں���������CustomerInfo����

}