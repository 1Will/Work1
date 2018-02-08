package main.java.task;

import java.util.Date;
import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;

public class ClearOldNewsTimerTask extends BaseTimerTask {
	
	private final static int day = -61;   // 提前61天   两个月前
	
	public ClearOldNewsTimerTask(ApplicationContext context) {
		super(context);
	}
	
	public void run() {
		logger.info(formatter2.format(new Date())+": 开始删除两个月前的微信事件信息！");
		eventService = (EventService) context.getBean("eventService");
		String sql ="DELETE  dbo.t_news where CURRENTDATE <='"+DateAlert.getEarlyYearDate(day)+"'" ;
		eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
		
		logger.info(formatter2.format(new Date())+": 删除两个月前的微信事件信息结束！");
		
	}
}
