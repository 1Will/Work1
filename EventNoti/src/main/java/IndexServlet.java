package main.java;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import main.pojo.EventNew;
import main.service.EventService;
import main.service.ProjectInfoService;
import main.util.HandlerBase;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IndexServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    private static Logger logger=Logger.getLogger(IndexServlet.class);
	public static EventService eventService;// 数据库操作接口
	public static Timer timer;// 定时器
	public static Session session;
	public static Transaction transaction;
	public  ProjectInfoService projectInfoService;	
  
    public IndexServlet() {  
        super();  
    }  
      
    public void destroy() {  
        super.destroy();   
    }  
     
    public void init(ServletConfig config) throws ServletException {  
        logger.info("進入contextInitialized方法");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		eventService=(EventService) context.getBean("eventService");
		projectInfoService=(ProjectInfoService) context.getBean("projectInfoService");
		ServletContext application =config.getServletContext();
		/*获取项目列表
		 List<ProjectInfo> ProjectInfoList = new ArrayList<ProjectInfo>();
		 ProjectInfoList=projectInfoService.getProjectInfoById();
		 application.setAttribute("ProjectList", ProjectInfoList);
		 System.out.print("ProjectInfoList项目列表集合长度为："+ProjectInfoList.size());
		*/
        IndexServlet.timerTask();  
    }  
      
    public static void timerTask() {  
        new Timer().schedule(new TimerTask(){  
            public void run() {  
            	autoWorkOff();
            }  
        },new Date(),20000);  
   }
    //定时任务执行方法
    public static void autoWorkOff() {
		List<EventNew> events = eventService.getEvent();
		session = eventService.getSuperSession();
		HandlerBase handBase = new HandlerBase();
		for (EventNew event : events) {
			//System.out.println(event.getName());
			logger.info("事件名称是："+event.getName());
			try {
				
				handBase.setEvent(event);
				HandlerBase.setEventService(eventService);
				session.beginTransaction();
				String type = event.getEventType().getCode();
				if(type.equals("10000")){//根据eventType的code值判断事件类型
					
					handBase.qingJiaEvent();
				}else if(type.equals("10001")){
					handBase.publishNotification(event);
				}
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} 
			
		}
		
	}
}  