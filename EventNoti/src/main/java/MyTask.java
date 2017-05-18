package main.java;

import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.pojo.EventNew;
import main.pojo.EventType;
import main.service.EventService;

public class MyTask {
	public static EventService eventService;// ���ݿ�����ӿ�
	
	public void execute(){  
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
		 eventService=(EventService) context.getBean("eventService");
		 EventType eventType = new EventType();
		 int dayMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		 int dayWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        try{  
              if(dayWeek==5){
            	  eventType = eventService.getEventTypeByCode("1002");
            	  EventNew event = new EventNew();
            	  event.setEventType(eventType);
            	  event.setName("�ܴ���");
            	  event.setMoreinfo("�ܴ����¼�");
            	  event.setEffectflag("E");
            	  eventService.saveEvent(event);
              }
         }catch(Exception ex){  
             ex.printStackTrace();  
         }  
     } 
}
