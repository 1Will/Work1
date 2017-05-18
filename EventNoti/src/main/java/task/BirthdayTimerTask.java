package main.java.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.pojo.ContactArchives;
import main.service.ContactArchivesService;
import main.service.EventService;
import main.util.DateAlert;

public class BirthdayTimerTask extends TimerTask {
	public static EventService eventService;// ���ݿ�����ӿ�
	public static ContactArchivesService contactArchivesService;// ��ϵ�����ݿ�����ӿ�
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static int day = 1;// ��ǰ1��֪ͨ

	public void run() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			contactArchivesService = (ContactArchivesService) context.getBean("contactArchivesService");
			// ������д��Ҫִ�е�����
			List<ContactArchives> caList = DateAlert.getObjByDay(new ContactArchives(), day, "birthday");
			for (int i = 0; i < caList.size(); i++) {
				// System.out.println(caList.get(i).getContactName());
				// ��ȡ����������
				String creator = (String) eventService.getSuperSession()
						.createSQLQuery("select CREATOR from dbo.t_contactArchives where id =" + caList.get(i).getId())
						.uniqueResult();
				// ��ȡ������id
				if (creator != null) {
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where NAME ='" + creator + "'").uniqueResult();
					// ��dbo.t_work_warnning�в�������
					String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
							+ "values (0,'�ͻ���������','�пͻ�:"+ caList.get(i).getContactName()+ "("+ caList.get(i).getCustomerName()+ ")��������','"+ formatter.format(new Date())+ "',0,"+ creatorId.intValue() + ",null);";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
					// ��ȡ�ող����id���˴��������߳����⣬�޸�����ʱ�䲻һ��
					BigDecimal id = (BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT IDENT_CURRENT('dbo.t_work_warnning');").uniqueResult();
					// ��dbo.t_work_warnning_detail�в�������
					String sql2 = "insert into dbo.t_work_warnning_detail (VERSION,NAME,CODE,WORK_WARNNING_ID,WARN_DATE,REMAIN_DAYS) "
							+ "values (0,'"+ caList.get(i).getContactName()+ "','"+ caList.get(i).getCustomerInfoCode()+ "',"+ id+ ",'"+ formatter.format(new Date())+ "'," + day + ");";
					eventService.getSuperSession().createSQLQuery(sql2).executeUpdate();
				}
			}
			System.out.println("***********************��������ִ�гɹ�********************");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("xxxxxxxxxxxxxxxxx �������ѽ�����Ϣ�����쳣 xxxxxxxxxxxxxxxxx");
		}
	}

}
