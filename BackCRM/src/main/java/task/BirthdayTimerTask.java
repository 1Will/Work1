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

	public static ContactArchivesService contactArchivesService;// ��ϵ�����ݿ�����ӿ�
	private final static int day = 1;// ��ǰ1��֪ͨ

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": ��ʼִ�пͻ�����ɨ�����ѣ�");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			contactArchivesService = (ContactArchivesService) context.getBean("contactArchivesService");
			// ������д��Ҫִ�е�����
			List<ContactArchives> caList = DateAlert.getObjByDay(new ContactArchives(), day, "birthday");
			for (int i = 0; i < caList.size(); i++) {
				// System.out.println(caList.get(i).getContactName());
				// ��ȡ������������ֻ��������Ϣ����
				String creator = (String) eventService.getSuperSession()
						.createSQLQuery("select CREATOR from dbo.t_contactArchives where id =" + caList.get(i).getId())
						.uniqueResult();
				// ��ȡ������id
				if (creator != null) {
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where NAME ='" + creator + "'").uniqueResult();
					
					BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10016'").uniqueResult();
					String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
							+ "("+etId.longValue()+",'��������','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "�пͻ�:"+ caList.get(i).getContactName()+ "("+ caList.get(i).getCustomerName()+ ")��������" + "\"}','E','0')";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
			logger.info(formatter2.format(new Date())+"��������ִ�н���!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("�������ѽ�����Ϣ�����쳣 !");
		}
	}

}
