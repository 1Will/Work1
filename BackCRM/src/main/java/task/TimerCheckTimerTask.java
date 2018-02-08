package main.java.task;

import java.util.Date;

import main.service.EventService;

import org.springframework.context.ApplicationContext;

public class TimerCheckTimerTask extends BaseTimerTask{

	public TimerCheckTimerTask(ApplicationContext context) {
		super(context);
	}

	@Override
	public void run() {
		logger.info(formatter2.format(new Date()) + " 开始执行Timer检查！");
		eventService = (EventService) context.getBean("eventService");
		Object num =  eventService.getSuperSession().createSQLQuery("SELECT COUNT(*) FROM Master.dbo.SYSPROCESSES WHERE [DBID] IN ( SELECT DBID FROM Master.dbo.SYSDATABASES WHERE NAME='tektcrm')").uniqueResult();;
		String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES (31,'Timer检查提醒','{\"users\":\"2\",\"content\":\"Timer检查提醒,当前连接数为:"+num+"\"}','E','0')";
		int i =eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
		eventService.getSuperSession().close();
		logger.info("执行次数：" +i);
		logger.info(formatter2.format(new Date()) + "Timer检查结束！");
	}
}
