package main.dao;

import java.util.List;

import main.pojo.CustomerInfo;

import org.hibernate.Session;


public interface CustomerInfoDao {

	public void saveCustomerInfo(CustomerInfo customerInfo);
	public  CustomerInfo getCustomerInfoById(Long id); //��ȡ�������ݼ���

/*	public  List<CustomerInfo> getAllCustomerInfoById(Long[] ids);//��ȡ��������
*/	

public CustomerInfo getById(Long id);
public  List<CustomerInfo> getCustomerInfoByName(String name) ;

	public List<CustomerInfo> getAllCustomerInfo(); //��ʼ�� ��ȡ����ʵ��
	public Session getSuperSession();
	public Object findLastCustomerId();//��ȡ��ǰ���ݱ������id
	public List<CustomerInfo> getAllCustomer();
}
