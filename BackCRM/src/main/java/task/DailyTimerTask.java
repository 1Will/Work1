package main.java.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.Daily;
import main.pojo.UsersInfo;
import main.service.EventService;
import main.util.DateAlert;

public class DailyTimerTask extends BaseTimerTask {
	public DailyTimerTask(ApplicationContext context) {
		super(context);
	}

	private final static int day = -1;// ��ǰ1��֪ͨ

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		logger.info(formatter2.format(new Date()) + " ��ʼͳ�������ձ������");
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK);
		int mon = cal.get(Calendar.MONTH)+1;
		int da =cal.get(Calendar.DAY_OF_MONTH);
		boolean guoqing = !((mon==10)&&(da<8));//�����Ϊfalse
		logger.info("DAY_OF_WEEK :"+w);
		if (w != 1 && w != 2 && guoqing) {
			cal.add(Calendar.DATE, -1);
			eventService = (EventService) context.getBean("eventService");
			List<Daily> dailyList = DateAlert.getObjByYearDay(new Daily(), day, "currentDate");
			List<UsersInfo> userList = eventService.getSuperSession().createQuery("from UsersInfo u where u.enabled =1 and u.code <> ''").list();
			//΢���ձ�֪ͨ��
			List<Long> wxrbList = eventService.getSuperSession().createSQLQuery("SELECT ug.USER_ID FROM t_group_user as ug,t_groups as g where g.id=ug.GROUP_ID and g.CODE='WXRB-000001'").list();
//			List<Long> allUser = new ArrayList<Long>();
//			for (int i = 0; i < userList.size(); i++) {
//				allUser.add(userList.get(i).getId());
//			}
			for (int i = 0; i < dailyList.size(); i++) {
				if (dailyList.get(i).getIsSaved().equals("1")) {
					for (int j = 0; j < userList.size(); j++) {
						if (userList.get(j).getId().equals(dailyList.get(i).getRapporteurId())) {
							userList.remove(j);
						}
					}
				}
			}
			List<Long> perUser = new ArrayList<Long>();
			for (int i = 0; i < userList.size(); i++) {
				perUser.add(userList.get(i).getId());
			}
			logger.info("δ�ύ�ձ����û�id����  :"+perUser.toString());  //δ�ύ�ձ����û�id����
			// ͨ��ʱ��Ƚ� ����9�� 12����¼�¼��
			int t = cal.get(Calendar.HOUR_OF_DAY); 
			logger.info("��Ϊ�жϵ�Сʱֵ��"+t);
			BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10005'").uniqueResult();
		if (t>11) {
			//12����ձ����	
			// ÿ�˶���ʾ��
			String allSql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
					+ "("+etId.longValue()+",'�ձ��ύ���','{\"users\":\"" + wxrbList.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
					+ formatter.format(cal.getTime()) + "����˾�� " + userList.size() + " ��δ�ύ�ձ�\"}','E','0')";
			eventService.getSuperSession().createSQLQuery(allSql).executeUpdate();
			// δд�ձ�����ʾ
			String persql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
					+ "("+etId.longValue()+",'�ձ�δ�ύ','{\"users\":\"" + perUser.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
					+ formatter.format(cal.getTime()) + "�������ձ�û�а�ʱ�ύ\"}','E','0')";
			eventService.getSuperSession().createSQLQuery(persql).executeUpdate();
	     } else {
            //9����ձ����
	    	 String persql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
						+ "("+etId.longValue()+",'�ձ�δ�ύ','{\"users\":\"" + perUser.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
						+ formatter.format(cal.getTime()) + "����ܰ��ʾ�������ձ���û���ύ\"}','E','0')";
			eventService.getSuperSession().createSQLQuery(persql).executeUpdate();
			}
			
		} else {
			if(!guoqing){
				logger.info("���첻ִ�м�飡");
			}else {
				logger.info("��ĩ����һ��ִ�м�飡");
			}
		}
		logger.info(formatter2.format(new Date()) + " ͳ�������ձ����������");
	}
}
