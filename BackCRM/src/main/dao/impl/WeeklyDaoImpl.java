package main.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import main.dao.WeeklyDao;
import main.pojo.Weekly;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


public class WeeklyDaoImpl extends HibernateDaoSupport implements WeeklyDao {

	@Override
	public void saveWeekly(Weekly weekly) {

		try {
			this.getHibernateTemplate().save(weekly);
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
    
	@Override
	public void updateWeekly(Weekly weekly){
		try {
			this.getHibernateTemplate().merge(weekly);
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
	
	//根据id 获取周报 实例
	@Override
	public Weekly getWeeklyById(Long id) {
		Weekly weekly=null;
		try {
			weekly=(Weekly)getSession().load(Weekly.class, id);
		} catch (Exception e) {
		       e.printStackTrace();
		}
		return weekly;
	}
	
	@SuppressWarnings("unchecked")   //endDate
	@Override
	public List<Weekly> getWeeklyByweeklyName(String weeklyName ,String loginName) {
	//	String hql= "select w from  Weekly w where w.weeklyName like '%"+weeklyName+"%' and w.creator='"+loginName+"' and w.endDate>'"+thisYear+"' order by w.id desc ";
			String hql= "from  Weekly w where w.weeklyName like '%"+weeklyName+"%' and w.creator='"+loginName+"' order by w.id desc ";
		return this.getHibernateTemplate().find(hql);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Weekly> getWeeklyByweekId(Long weekId, String loginName) {
		String hql= "from  Weekly w where w.weekId = "+weekId+" and w.creator='"+loginName+"'";
		return this.getHibernateTemplate().find(hql);
	}
	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	public String getMaxWeeklyCode(String code, Long ratId)
	    {
	      String hql = "select w.code from Weekly as w where w.rapporteurId=" + ratId + " and w.code like '%" + code + "%'";
	      List codeList = getSession().createQuery(hql).list();
	      if (codeList.size() > 0) {
	        List items = new ArrayList();
	        for (int i = 0; i < codeList.size(); i++) {
	          String item = ((String)codeList.get(i)).substring(((String)codeList.get(i)).lastIndexOf("_0") + 2);
	          items.add(item);
	        }
	        Collections.sort(items);
	        return (String)items.get(items.size() - 1);
	      }
	      return null;
	    }
	 

	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}



	

	
}
