package main.java;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class NFDFlightDataTaskListener implements  ServletContextListener { 
	 public void contextInitialized(ServletContextEvent sce) {
		 //��������
         new TimerManager(24*60*60*1000L,1,10,0,"0001");
         //��������һ�£�����sql����߳�����,��ͬ����
         new TimerManager(24*60*60*1000L,1,15,0,"0002");
         //���°ݷ�ʱ�������澯״̬
         new TimerManager(24*60*60*1000L,1,20,0,"0003");
         //��Ŀ�ƻ�����
         new TimerManager(24*60*60*1000L,1,25,0,"0004");
         //�����������
         new TimerManager(24*60*60*1000L,1,30,0,"0005");
         //�ձ����Ѽ��
         new TimerManager(24*60*60*1000L,12,00,0,"0006");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
         System.out.println("*****************�������ر�***************");
    }
}
