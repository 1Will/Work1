package main.java;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class NFDFlightDataTaskListener implements  ServletContextListener { 
	 public void contextInitialized(ServletContextEvent sce) {
         new TimerManager(24*60*60*1000L,10,00,0,"0001");
         //��������һ�£�����sql����߳�����
         new TimerManager(24*60*60*1000L,10,00,5,"0002");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
         System.out.println("*****************�������ر�***************");
    }
}