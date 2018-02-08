package main.java.task;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.ApplicationContext;

import main.service.EventService;

public class WeekTimerTask extends BaseTimerTask {
	public WeekTimerTask(ApplicationContext context) {
		super(context);
	}
	public static String Month[] = { "һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ", "ʮһ", "ʮ��" };
	public static String Week[] = { "һ", "��", "��", "��", "��" };
	EventService eventService = (EventService) context.getBean("eventService");

	public void run() {
		if (isLastDay()) {
			logger.info(formatter2.format(new Date()) + "��ʼ����ȫ���ܣ�");
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR) + 1;
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			int mon = cal.get(Calendar.MONTH);// ��ȡ�·�
			int w = 0;
			String name = "";
			String startDate = "";
			String endDate = "";
			try {
				while (cal.get(Calendar.YEAR) == year) {
					int week = cal.get(Calendar.DAY_OF_WEEK);// ��ȡ�ܼ���������1
					if (cal.get(Calendar.MONTH) != mon) {// ������
						w = 0;
						mon = cal.get(Calendar.MONTH);
					}
					if (week == 1) {
						name = Month[cal.get(Calendar.MONTH)] + "��_��" + Week[w] + "��";
						startDate = formatter2.format(new Date(cal.getTimeInMillis()));
						cal.add(Calendar.DATE, 6);
						endDate = formatter2.format(new Date(cal.getTimeInMillis()));
						cal.add(Calendar.DATE, 1);
						insertWeek(name, startDate, endDate);
						w++;
					} else {
						cal.add(Calendar.DATE, 1);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("����ȫ�����д�");
			}
			logger.info(formatter2.format(new Date()) + "����ȫ���ܽ�����");
		}
	}

	public void insertWeek(String name, String startDate, String endDate) {
		String sql = "insert into dbo.t_week (NAME,STARTDATE,ENDDATE) VALUES ('" + name + "','" + startDate + "','"
				+ endDate + "')";
		eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
	}

	 public static void main(String args[]){
//		 System.out.println(isLastDay());
//		 WeekTimerTask w =new WeekTimerTask();
//		 w.run();
	 }
	public static boolean isLastDay() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		// System.out.println(now.get(Calendar.MONTH));
		// System.out.println(calendar.get(Calendar.MONTH));
		// System.out.println(now.get(Calendar.DATE));
		// System.out.println(calendar.get(Calendar.DATE));
		if (now.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
				&& now.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
			return true;
		} else {
			return false;
		}
	}
}
