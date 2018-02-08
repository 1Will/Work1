package main.service;

import java.util.List;

import main.pojo.News;

import org.hibernate.Session;

public interface NewsService  {
	
	public List<News> getNews(long userid); //根据Userid 获取消息列表
	
	public News getNewsById(long newsId); //根据newsId 获取单个实例
	
	public void updateNews(News news);//更新阅读状态
	
	public void deleteNews(long newsId);//删除单条数据
	
	
    public Session getSuperSession(); 
	
}
