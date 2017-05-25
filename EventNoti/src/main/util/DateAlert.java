package main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.service.EventService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DateAlert {
	public static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static EventService eventService=(EventService) context.getBean("eventService");
	
	/**
	 * 
	 * @param day为负,当前日期减day，为正数，当前日期加day；
	 * @return 返回日期格式"MM-dd"
	 */
	public static String getEarlyDate(int day){
		Date date =new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		Calendar c = Calendar.getInstance();  
        c.setTime(date);   //设置当前日期  
        c.add(Calendar.DATE, day);
        date = c.getTime();
        return formatter.format(date);
	}
	
	/**
	 * 
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
	
	/**
	 * 
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return 返回间隔天数,正負代表前後
	 */
	public static Long betweenDays(Date beginDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(beginDate);
		c2.setTime(endDate);
		//可以用正負代表前後
		return (Long)((c1.getTimeInMillis() - c2.getTimeInMillis())/(1000*60*60*24));
	}
	
	/**
	 * 
	 * @param clazz 类的对象
	 * @param day为负,当前日期减day，为正数，当前日期加day；
	 * @param dateName 为要模糊查找类型T的属性名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjByDay(T clazz,int day ,String dateName){
		String sql ="from "+clazz.getClass().getSimpleName()+" c where convert(varchar,c."+dateName+",120) like '%-"+getEarlyDate(day)+"%'";
		List<T> list =  eventService.getSuperSession().createQuery(sql).list();  
		return list;
	}
	
	/**
	 * 
	 * @param clazz 类的对象
	 * @param day为负,当前日期减day，为正数，当前日期加day；
	 * @param dateName 为要模糊查找类型T的属性名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjByYearDay(T clazz,int day ,String dateName){
		String sql ="from "+clazz.getClass().getSimpleName()+" c where convert(varchar,c."+dateName+",120) like '"+getEarlyYearDate(day)+"%'";
		List<T> list =  eventService.getSuperSession().createQuery(sql).list();  
		return list;
	}
}
