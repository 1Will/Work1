package main.dao.impl;

import java.sql.SQLException;
import java.util.List;

import main.dao.DailyDao;
import main.pojo.Daily;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DailyDaoImpl extends HibernateDaoSupport implements DailyDao {

	@Override
	public void saveDaily(Daily daily) {

		try {
			this.getHibernateTemplate().save(daily);
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
	
	@Override
	public void updateDaily(Daily daily){
		try {
			this.getHibernateTemplate().merge(daily);
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Daily> getAllDaily() {
		String hql="select d from Daily d";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public Daily getDailyById(final Long id) {
		return (Daily) getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String sql ="from main.pojo.Daily where id = "+id;
				
				
				Daily aDaily =(Daily)session.createQuery(sql).list().get(0);
				return aDaily;
			}
		});
		
		
//		return (Daily)getHibernateTemplate().load(Daily.class, id);
//		Daily daily=null;
//		try {
//			daily=(Daily) getSession().load(Daily.class, id);
//		} catch (Exception e) {
//            e.printStackTrace();
//		}
//		return daily;
	
		
	}
	
	
	//获取当前数据表中最大ID
	@Override
	public Long getLastId() { 
		String hql="select d.id from Daily d order by Id desc";
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}
	
	//根据id更新日报
	@Override
	public void updateDaily( String leaderIdea, Long id) {
      //   String hql="update Daily d set d.leaderIdea ='"+daily.getLeaderIdea()+" where ID = "+id;
         Daily daily = (Daily)this.getHibernateTemplate().get(Daily.class, id);  
         daily.setLeaderIdea(leaderIdea);  
         daily.setId(id);  
        this.getHibernateTemplate().update(daily);  
	}
	
	//根据日期和姓名简称返回Daily集合
		@SuppressWarnings("unchecked")
		@Override
		public List<Daily> getDailyByDate(String date,String name ) {
			String hql="from Daily d where convert(varchar,d.currentDate,120) like '"+date+"%' and d.creator='"+name+"'";
			return this.getHibernateTemplate().find(hql);
		}
		
	
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}


	
}
