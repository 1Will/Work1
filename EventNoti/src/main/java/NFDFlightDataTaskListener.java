package main.java;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class NFDFlightDataTaskListener implements  ServletContextListener { 
	 public void contextInitialized(ServletContextEvent sce) {
		 //生日提醒
         new TimerManager(24*60*60*1000L,1,10,0,"0001");
         //让秒数不一致，避免sql语句线程问题,合同提醒
         new TimerManager(24*60*60*1000L,1,15,0,"0002");
         //更新拜访时间间隔、告警状态
         new TimerManager(24*60*60*1000L,1,20,0,"0003");
         //项目计划提醒
         new TimerManager(24*60*60*1000L,1,25,0,"0004");
         //磁盘容量检查
         new TimerManager(24*60*60*1000L,1,30,0,"0005");
         //日报提醒检查
         new TimerManager(24*60*60*1000L,12,00,0,"0006");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
         System.out.println("*****************监听器关闭***************");
    }
}
