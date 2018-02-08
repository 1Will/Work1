package main.service;

import java.util.List;

import main.pojo.News;

import org.hibernate.Session;

public interface NewsService  {
	
	public List<News> getNews(long userid); //����Userid ��ȡ��Ϣ�б�
	
	public News getNewsById(long newsId); //����newsId ��ȡ����ʵ��
	
	public void updateNews(News news);//�����Ķ�״̬
	
	public void deleteNews(long newsId);//ɾ����������
	
	
    public Session getSuperSession(); 
	
}
