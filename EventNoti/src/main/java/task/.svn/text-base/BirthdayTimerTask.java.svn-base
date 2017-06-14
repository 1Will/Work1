package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.pojo.ContactArchives;
import main.service.ContactArchivesService;
import main.service.EventService;
import main.util.DateAlert;

public class BirthdayTimerTask extends BaseTimerTask {
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
				// 获取创建人姓名
				String creator = (String) eventService.getSuperSession()
						.createSQLQuery("select CREATOR from dbo.t_contactArchives where id =" + caList.get(i).getId())
						.uniqueResult();
				// 获取创建人id
				if (creator != null) {
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where NAME ='" + creator + "'").uniqueResult();
					// 向dbo.t_work_warnning中插入数据
					String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
							+ "values (0,'客户生日提醒','有客户:"+ caList.get(i).getContactName()+ "("+ caList.get(i).getCustomerName()+ ")生日提醒','"+ formatter2.format(new Date())+ "',0,"+ creatorId.intValue() + ",null);";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					// 获取刚刚插入的id，此处可能有线程问题，修改启动时间不一致
					BigDecimal id = (BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT IDENT_CURRENT('dbo.t_work_warnning');").uniqueResult();
					// 向dbo.t_work_warnning_detail中插入数据
					String sql2 = "insert into dbo.t_work_warnning_detail (VERSION,NAME,CODE,WORK_WARNNING_ID,WARN_DATE,REMAIN_DAYS) "
							+ "values (0,'"+ caList.get(i).getContactName()+ "','"+ caList.get(i).getCustomerInfoCode()+ "',"+ id+ ",'"+ formatter2.format(new Date())+ "'," + day + ");";
					eventService.getSuperSession().createSQLQuery(sql2).executeUpdate();
				}
			}
			logger.info(formatter2.format(new Date())+"生日提醒执行结束!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("生日提醒解析信息发生异常 !");
		}
	}

}
