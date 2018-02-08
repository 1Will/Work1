package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.ContactArchives;
import main.service.ContactArchivesService;
import main.service.EventService;
import main.util.DateAlert;

public class BirthdayTimerTask extends BaseTimerTask {
	public BirthdayTimerTask(ApplicationContext context) {
		super(context);
	}

	public static ContactArchivesService contactArchivesService;// 联系人数据库操作接口
	private final static int day = 1;// 提前1天通知

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": 开始执行客户生日扫描提醒！");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			contactArchivesService = (ContactArchivesService) context.getBean("contactArchivesService");
			// 在这里写你要执行的内容
			List<ContactArchives> caList = DateAlert.getObjByDay(new ContactArchives(), day, "birthday");
			for (int i = 0; i < caList.size(); i++) {
				// System.out.println(caList.get(i).getContactName());
				// 获取创建人姓名，只有姓名信息。。
				String creator = (String) eventService.getSuperSession()
						.createSQLQuery("select CREATOR from dbo.t_contactArchives where id =" + caList.get(i).getId())
						.uniqueResult();
				// 获取创建人id
				if (creator != null) {
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where NAME ='" + creator + "'").uniqueResult();
					
					BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10016'").uniqueResult();
					String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
							+ "("+etId.longValue()+",'生日提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有客户:"+ caList.get(i).getContactName()+ "("+ caList.get(i).getCustomerName()+ ")生日提醒" + "\"}','E','0')";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
			logger.info(formatter2.format(new Date())+"生日提醒执行结束!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("生日提醒解析信息发生异常 !");
		}
	}

}
