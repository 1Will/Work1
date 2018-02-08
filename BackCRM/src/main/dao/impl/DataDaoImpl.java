package main.dao.impl;

import java.util.HashMap;
import java.util.List;

import main.dao.DataDao;
import main.pojo.Data;
import main.pojo.PersonnelFiles;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class DataDaoImpl extends HibernateDaoSupport implements DataDao {
	

	@Override
	public void storeData(Data data) {
		if(data.getId()!=null){
			this.getHibernateTemplate().merge(data);
		}else{
			this.getHibernateTemplate().save(data);
		}
	}
	
	@Override
	public List<Data> loadByMonthAndCode(String month,String code) {
		String hql="select d from Data d where d.month='"+month+"' and d.personnelFiles.code='"+ code+"'";
		return this.getHibernateTemplate().find(hql);
	}
	

	@Override
	public Object loadAllDataByYear(HashMap map) {
		Session session =getSession();
		StringBuffer sf = new StringBuffer();
		sf.append("select isnull(sum( d.contractManagementMoney ),0),isnull(sum( d.contractManagementNum ),0),isnull(sum( d.financialManagementNum ),0), ")
		   .append("isnull(sum( d.financialManagementMoney ),0),isnull(sum( d.billingRecordNum ),0),isnull(sum( d.billingRecordMoney ),0)")
		   .append(" from Data d where 1=1");
		if(map.get("year")!=null){
			sf.append(" and d.year like '%"+map.get("year")+"%'");
		}
		if(map.get("code")!=null){
			sf.append(" and d.personnelFiles.code ='"+map.get("code")+"'");
		}
		Query query = session.createQuery(sf.toString());
		return query.uniqueResult();
		
		
	}
	
	public Session getSuperSession() {
		
		return this.getSession(true);
	}


	
}
