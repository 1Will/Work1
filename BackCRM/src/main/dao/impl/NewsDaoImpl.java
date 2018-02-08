package main.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import main.dao.NewsDao;
import main.pojo.Daily;
import main.pojo.News;
import main.pojo.Supplier;

public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<News> getNews(long userid) {
		String hql="select n from News n where n.userid="+userid+" order by n.id desc";
		return this.getHibernateTemplate().find(hql);
	}

	@Override
	public void updateNews(News news){
		try {
			this.getHibernateTemplate().merge(news);
		} catch (Exception e) {
		     e.printStackTrace();
		}
	}

	@Override
	public void deleteNews(long newsId) {
		
		try {
		  News news=(News) this.getSession().load(News.class, newsId);
			this.getHibernateTemplate().delete(news);
		} catch (Exception e) {
		     e.printStackTrace();
		}
		
	}
	
	@Override
	public News getNewsById(long newsId) {
		News news  = null;
		try {
			news=(News) this.getSession().load(News.class, newsId);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return news;
	}
	
	@Override
	public Session getSuperSession() {
		return this.getSession(true);
	}




	

}
