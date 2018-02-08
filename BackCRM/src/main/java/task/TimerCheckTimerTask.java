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
		logger.info(formatter2.format(new Date()) + " ��ʼִ��Timer��飡");
		eventService = (EventService) context.getBean("eventService");
		Object num =  eventService.getSuperSession().createSQLQuery("SELECT COUNT(*) FROM Master.dbo.SYSPROCESSES WHERE [DBID] IN ( SELECT DBID FROM Master.dbo.SYSDATABASES WHERE NAME='tektcrm')").uniqueResult();;
		String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES (31,'Timer�������','{\"users\":\"2\",\"content\":\"Timer�������,��ǰ������Ϊ:"+num+"\"}','E','0')";
		int i =eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
		eventService.getSuperSession().close();
		logger.info("ִ�д�����" +i);
		logger.info(formatter2.format(new Date()) + "Timer��������");
	}
}
