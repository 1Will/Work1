package main.java.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.pojo.Daily;
import main.pojo.UsersInfo;
import main.service.EventService;
import main.util.DateAlert;

public class DailyTimerTask extends BaseTimerTask {
	private final static int day = -1;// 提前1天通知

	@Override
	public void run() {
		logger.info(formatter2.format(new Date()) + " 开始统计昨天日报情况！");
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK);
		logger.info(w);
		if (w != 1 && w != 2) {
			cal.add(Calendar.DATE, -1);
			eventService = (EventService) context.getBean("eventService");
			List<Daily> dailyList = DateAlert.getObjByYearDay(new Daily(), day, "currentDate");
			@SuppressWarnings("unchecked")
			List<UsersInfo> userList = eventService.getSuperSession()
					.createQuery("from UsersInfo u where u.enabled =1 and u.code <> ''").list();
			List<Long> allUser = new ArrayList<Long>();
			for (int i = 0; i < userList.size(); i++) {
				allUser.add(userList.get(i).getId());
			}
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
			logger.info(allUser.toString());
			logger.info(perUser.toString());
			// 每人都提示项
			String allSql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG) VALUES "
					+ "(6,'日报提交情况','{\"users\":\"" + allUser.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
					+ formatter.format(cal.getTime()) + "，公司有 " + userList.size() + " 人未提交日报\"}','E')";
			eventService.getSuperSession().createSQLQuery(allSql).executeUpdate();
			// 未写日报人提示
			String persql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG) VALUES "
					+ "(6,'日报未提交','{\"users\":\"" + perUser.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
					+ formatter.format(cal.getTime()) + "，您的日报没有按时提交\"}','E')";
			eventService.getSuperSession().createSQLQuery(persql).executeUpdate();
		} else {
			logger.info("周末和周一不执行检查！");
		}
		logger.info(formatter2.format(new Date()) + " 统计昨天日报情况结束！");
	}

	public static void main(String args[]) {
		DailyTimerTask d = new DailyTimerTask();
		d.run();
	}
}
