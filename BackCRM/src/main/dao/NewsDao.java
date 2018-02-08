package main.dao;

import java.util.List;

import main.pojo.News;

import org.hibernate.Session;

public interface NewsDao {
   
	public List<News> getNews(long userid); //����Userid ��ȡ��Ϣ�б�

	public News getNewsById(long newsId); //����newsId ��ȡ����ʵ��
	
	public void updateNews(News news);//�����Ķ�״̬

	public void deleteNews(long newsId);//ɾ����������
	
    public Session getSuperSession(); 
	
}
