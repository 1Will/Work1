package main.dao.impl;
import java.util.List;

import main.dao.CustomerInfoDao;
import main.pojo.CustomerInfo;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class CustomerInfoDaoImpl extends HibernateDaoSupport  implements CustomerInfoDao {

	
	public void saveCustomerInfo(CustomerInfo customerInfo)
	{
		try{
		    this.getHibernateTemplate().save(customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public void updateCustomerInfo(CustomerInfo customerInfo)
	{
		try{
			this.getHibernateTemplate().update(customerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public  CustomerInfo getCustomerInfoById(Long id) {
		
		CustomerInfo customerInfo=null;
		try {
//			customerInfo=(CustomerInfo)getHibernateTemplate().load(CustomerInfo.class, id);
			customerInfo=(CustomerInfo)getSession().load(CustomerInfo.class, id);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return customerInfo;
		
	}
	//��ʼ��ʱ��ȡ����ʵ��
		@SuppressWarnings("unchecked")
		public List<CustomerInfo> getAllCustomerInfo() {
			String hql = "from CustomerInfo";
			System.out.println("====sql===="+hql);
			return this.getHibernateTemplate().find(hql);
		}
		//��ȡ��ǰ���ݱ������idֵ
		@Override
		public Object findLastCustomerId() {
   			String hql="select c.id from CustomerInfo c order by Id desc";
   			System.out.println("��ȡ��ǰ���ݱ������idֵ��");
			return this.getHibernateTemplate().find(hql).get(0);
		}

    	public Session getSuperSession() {
		
		return this.getSession(true);
	    }
	

	@SuppressWarnings("unchecked")
	public  List<CustomerInfo> getAllCustomer() {
		return getHibernateTemplate().find(" select new main.pojo.CustomerInfo(info.id,info.customerName,info.customerType,info.keyContacter,info.archiveTime)  from main.pojo.CustomerInfo info order by info.id desc ");
		
	}

	public  CustomerInfo getById(Long id) {
		String hql="from CustomerInfo temp where temp.id="+id;
		return (CustomerInfo) this.getHibernateTemplate().find(hql).get(0);
	}
	
	public Long getMaxId() {
		String hql="select max(sa.id) from CustomerInfo sa";
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}
	
	// // select new main.pojo.CustomerInfo(info.id,info.customerName,info.customerType,info.keyContacter,info.archiveTime
	@SuppressWarnings("unchecked")
	public  List<CustomerInfo> getCustomerInfoByName(String name) {
		return getHibernateTemplate().find(" select info from main.pojo.CustomerInfo info where info.customerName like '%"+name+"%' order by info.id desc ");
	}
	
	@SuppressWarnings("unchecked")
	public  List<CustomerInfo> getCustomerInfosByNameAndBusinessType(String name,Long businessType) {
		return getHibernateTemplate().find(" select info from main.pojo.CustomerInfo info where info.customerName like '%"+name+"%' and info.businessType="+businessType+" order by info.id desc ");
	}

		//�������ں���������CustomerInfo����   convert(varchar,created_time,120) like   '2017-05-05%'   c.createdTime
		@SuppressWarnings("unchecked")
		@Override
		public List<CustomerInfo> getCustomerInfoByDate(String date,String name) {
			String hql="from CustomerInfo c where convert(varchar,c.createdTime,120) like '"+date+"%' and c.saleman='"+name+"'";
			return this.getHibernateTemplate().find(hql);
		}
		
	
	
	

}
