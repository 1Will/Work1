package main.java.task;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.service.EventService;

import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;
public class MeetingTimerTask extends BaseTimerTask {

	public MeetingTimerTask(ApplicationContext context) {
		super(context);
	}
	private final static int daybegin = 1;
	@Override
	@SuppressWarnings({"rawtypes","unchecked"})
	public void run() {
		logger.info(formatter2.format(new Date()) + "判断明天是否！");
		Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE,1);
			String datebegin =getEarlyYearDate(daybegin);
			String dateend=getEarlyYearDate(daybegin+1);
			eventService = (EventService) context.getBean("eventService");
	        logger.info("sqlmeeting");
	        
//	            String sqlMeetngName="SELECT convert(varchar(255),pf.id) from t_meetingDay md ,t_personnelFiles pf  where   pf.id = md.hostperson  and md.[DATE] >= "+"cast ('"+datebegin+"' as datetime )"+"  and  md.[DATE] < "+"cast ('"+dateend+"' as datetime )";
//				String sqlMeetngName="select convert(varchar(255),p.name) from "+"(select md.hostperson id FROM  t_meetingDay as md"+" where convert(varchar,md.date,120) like '"+date+"%')"+" t ,t_personnelFiles p where p.id=t.id";
//			    List hostNameId=(List) eventService.getSuperSession().createSQLQuery(sqlMeetngName).list();
//				logger.info(hostNameId.get(0));
			    String sqlMeetngTheme="SELECT convert(varchar(255),mm.theme) as theme,convert(varchar(255),t.id) as id from t_personnelFiles pf , t_meetingDay md ,t_meetingMonth mm ,t_users t where  pf.id = md.hostperson and  mm.id = md.MEETINGMONTH and pf.code=t.code and md.[DATE] >= "+"cast ('"+datebegin+"' as datetime )"+"  and  md.[DATE] < "+"cast ('"+dateend+"' as datetime )";
			    List<Map> listsObjects=null;
			    listsObjects=(List<Map>) eventService.getSuperSession().createSQLQuery(sqlMeetngTheme).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			   if(listsObjects!=null&& listsObjects.size()>0){
				   HashMap mapIndex = (HashMap) listsObjects.get(0);
				    logger.info(mapIndex.get("theme"));
				    String hostTheme=(String) mapIndex.get("theme");
				    BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10029'").uniqueResult();
				    String meetingsql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
							+ "("+etId.longValue()+",'早会提醒','{\"users\":\"" + mapIndex.get("id").toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""
							+ formatter.format(cal.getTime()) + "，温馨提示：您明天主持早会，主题："+hostTheme+"\"}','E','0')";
					eventService.getSuperSession().createSQLQuery(meetingsql).executeUpdate();
			   }
			   
	}
	/**
	 * @param day为负,当前日期减day，为正数，当前日期加day；
	 * @return 返回日期格式"yyyy-MM-dd"
	 */
	public static String getEarlyYearDate(int day){
		Date date =new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();  
		c.setTime(date);   //设置当前日期  
		c.add(Calendar.DATE, day);
		date = c.getTime();
		return formatter.format(date);
	}
}
