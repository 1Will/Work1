package main.dao;

import java.util.List;

import main.pojo.BackVisit;
import main.pojo.CustomerInfo;

import org.hibernate.Session;


public interface CustomerInfoDao {

	public void saveCustomerInfo(CustomerInfo customerInfo);

	public void updateCustomerInfo(CustomerInfo customerInfo); //更新
	
	public  CustomerInfo getCustomerInfoById(Long id); //获取单条数据集合

/*	public  List<CustomerInfo> getAllCustomerInfoById(Long[] ids);//获取多条集合
*/	
    public CustomerInfo getById(Long id);
    
    public Long getMaxId();

    public  List<CustomerInfo> getCustomerInfoByName(String name) ;

	public List<CustomerInfo> getAllCustomerInfo(); //初始化 获取所有实体
	
	public Object findLastCustomerId();//获取当前数据表中最大id
	
	public List<CustomerInfo> getAllCustomer();
	
	public List<CustomerInfo> getCustomerInfoByDate(String date,String name);//根据日期和姓名返回CustomerInfo集合
	
	public Session getSuperSession();
}
