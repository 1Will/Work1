package main.java;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class NFDFlightDataTaskListener implements  ServletContextListener { 
	 public void contextInitialized(ServletContextEvent sce) {
         new TimerManager(24*60*60*1000L,8,22,0,"0001");
         //��������һ�£�����sql����߳�����
         new TimerManager(24*60*60*1000L,8,03,5,"0002");
         new TimerManager(24*60*60*1000L,11,17,5,"0003");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
         System.out.println("*****************�������ر�***************");
    }
}
