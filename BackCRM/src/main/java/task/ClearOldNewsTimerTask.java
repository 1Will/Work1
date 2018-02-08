package main.java.task;

import java.util.Date;
import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;

public class ClearOldNewsTimerTask extends BaseTimerTask {
	
	private final static int day = -61;   // ��ǰ61��   ������ǰ
	
	public ClearOldNewsTimerTask(ApplicationContext context) {
		super(context);
	}
	
	public void run() {
		logger.info(formatter2.format(new Date())+": ��ʼɾ��������ǰ��΢���¼���Ϣ��");
		eventService = (EventService) context.getBean("eventService");
		String sql ="DELETE  dbo.t_news where CURRENTDATE <='"+DateAlert.getEarlyYearDate(day)+"'" ;
		eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
		
		logger.info(formatter2.format(new Date())+": ɾ��������ǰ��΢���¼���Ϣ������");
		
	}
}
