package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.NewsDao;
import main.pojo.News;
import main.service.NewsService;

public class NewsServiceImpl implements NewsService {
    
	private  NewsDao newsDao;

	@Override
	public List<News> getNews(long userid) {
		return newsDao.getNews(userid);
	}
	
	@Override
	public News getNewsById(long newsId) {
		return newsDao.getNewsById(newsId);
	}
	
	@Override
	public void updateNews(News news) {
		newsDao.updateNews(news);
	}

	@Override
	public void deleteNews(long newsId) {
        newsDao.deleteNews(newsId);		
	}

	@Override
	public Session getSuperSession() {
		return newsDao.getSuperSession();
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	
	
	
	
	
	
	
	
}
