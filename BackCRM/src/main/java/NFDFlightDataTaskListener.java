package main.java;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class NFDFlightDataTaskListener implements  ServletContextListener {
	 public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	 public void contextInitialized(ServletContextEvent sce) {
		 
		 //生日提醒
         new TimerManager(context,24*60*60*1000L,8,15,0,"0001");
         //让时间不一致，避免sql语句线程问题,合同提醒
         new TimerManager(context,24*60*60*1000L,8,20,0,"0002");
         //更新拜访时间间隔、告警状态、下次拜访提醒
         new TimerManager(context,24*60*60*1000L,8,00,0,"0003");
         //项目计划提醒
         new TimerManager(context,24*60*60*1000L,8,10,0,"0004");
         //磁盘容量检查
         new TimerManager(context,24*60*60*1000L,8,25,0,"0005");
         //日报提醒检查  每天12点
         new TimerManager(context,24*60*60*1000L,12,00,0,"0006");
         //每天9点 进行日报检查提醒
         new TimerManager(context,24*60*60*1000L,9,00,0,"0007");
         //添加每周，一年才执行一次
         new TimerManager(context,24*60*60*1000L,1,00,0,"0008");
         //添加统计表数据,一个月执行一次
         new TimerManager(context,24*60*60*1000L,1,40,0,"0009");
         
         new TimerManager(context,24*60*60*1000L,8,30,0,"0010");
         //添加早会，每天执行一次
         new TimerManager(context,24*60*60*1000L,16,30,0,"0012");
         //Timer检查
//         new TimerManager(context,60*1000L,18,11,0,"0011");
         //记录每天所有人的操作情况
         new TimerManager(context,24*60*60*1000L,2,0,0,"0013");
       //一般不执行，只要导入合同等数据，只要执行的timer，恢复我的数据我的团队数据
//         new TimerManager(context,2400*60*60*1000L,15,8,0,"0014");
         //生产经营计划扫描
         new TimerManager(context,24*60*60*1000L,0,30,0,"0015");
         //资格证书扫描
         new TimerManager(context,24*60*60*1000L,9,10,0,"0016");
         //定时 清理t_news 表2个月前的数据
         new TimerManager(context,24*60*60*1000L,0,10,0,"0017");
         //会议室扫描
         new TimerManager(context,60*1000L,8,00,0,"0018");
         //日程扫描
         new TimerManager(context,60*1000L,8,01,0,"0019");
         
    }
	 
    public void contextDestroyed(ServletContextEvent sce) {
         System.out.println("*****************监听器关闭***************");
    }
}
