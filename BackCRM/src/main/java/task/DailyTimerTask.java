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

	private final static int day = -1;// 提前1天通知

	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		logger.info(formatter2.format(new Date()) + " 开始统计昨天日报情况！");
		Calendar cal = Calendar.getInstance();
		int w = cal.get(Calendar.DAY_OF_WEEK);
		int mon = cal.get(Calendar.MONTH)+1;
		int da =cal.get(Calendar.DAY_OF_MONTH);
		boolean guoqing = !((mon==10)&&(da<8));//国庆节为false
		logger.info("DAY_OF_WEEK :"+w);
		if (w != 1 && w != 2 && guoqing) {
			cal.add(Calendar.DATE, -1);
			eventService = (EventService) context.getBean("eventService");
			List<Daily> dailyList = DateAlert.getObjByYearDay(new Daily(), day, "currentDate");
			List<UsersInfo> userList = eventService.getSuperSession().createQuery("from UsersInfo u where u.enabled =1 and u.code <> ''").list();
			//微信日报通知组
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
			logger.info("未提交日报的用户id集合  :"+perUser.toString());  //未提交日报的用户id集合
			// 通过时间比较 进行9点 12点的事件录入
			int t = cal.get(Calendar.HOUR_OF_DAY); 
			logger.info("作为判断的小时值："+t);
			BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10005'").uniqueResult();
		if (t>11) {
			//12点的日报检查	
			// 每人都提示项
			String allSql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
					+ "("+etId.longValue()+",'日报提交情况','{\"users\":\"" + wxrbList.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
					+ formatter.format(cal.getTime()) + "，公司有 " + userList.size() + " 人未提交日报\"}','E','0')";
			eventService.getSuperSession().createSQLQuery(allSql).executeUpdate();
			// 未写日报人提示
			String persql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
					+ "("+etId.longValue()+",'日报未提交','{\"users\":\"" + perUser.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
					+ formatter.format(cal.getTime()) + "，您的日报没有按时提交\"}','E','0')";
			eventService.getSuperSession().createSQLQuery(persql).executeUpdate();
	     } else {
            //9点的日报检查
	    	 String persql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
						+ "("+etId.longValue()+",'日报未提交','{\"users\":\"" + perUser.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
						+ formatter.format(cal.getTime()) + "，温馨提示：您的日报还没有提交\"}','E','0')";
			eventService.getSuperSession().createSQLQuery(persql).executeUpdate();
			}
			
		} else {
			if(!guoqing){
				logger.info("国庆不执行检查！");
			}else {
				logger.info("周末和周一不执行检查！");
			}
		}
		logger.info(formatter2.format(new Date()) + " 统计昨天日报情况结束！");
	}
}
