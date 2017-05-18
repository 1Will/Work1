package main.util;

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
	 * @param dayΪ��,��ǰ���ڼ�day��Ϊ��������ǰ���ڼ�day��
	 * @return �������ڸ�ʽ"MM-dd"
	 */
	public static String getEarlyDate(int day){
		Date date =new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		Calendar c = Calendar.getInstance();  
        c.setTime(date);   //���õ�ǰ����  
        c.add(Calendar.DATE, day);
        date = c.getTime();
        return formatter.format(date);
	}
	
	/**
	 * 
	 * @param dayΪ��,��ǰ���ڼ�day��Ϊ��������ǰ���ڼ�day��
	 * @return �������ڸ�ʽ"yyyy-MM-dd"
	 */
	public static String getEarlyYearDate(int day){
		Date date =new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();  
		c.setTime(date);   //���õ�ǰ����  
		c.add(Calendar.DATE, day);
		date = c.getTime();
		return formatter.format(date);
	}
	
	/**
	 * 
	 * @param clazz ��Ķ���
	 * @param dayΪ��,��ǰ���ڼ�day��Ϊ��������ǰ���ڼ�day��
	 * @param dateName ΪҪģ����������T��������
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
	 * @param clazz ��Ķ���
	 * @param dayΪ��,��ǰ���ڼ�day��Ϊ��������ǰ���ڼ�day��
	 * @param dateName ΪҪģ����������T��������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjByYearDay(T clazz,int day ,String dateName){
		String sql ="from "+clazz.getClass().getSimpleName()+" c where convert(varchar,c."+dateName+",120) like '"+getEarlyYearDate(day)+"%'";
		List<T> list =  eventService.getSuperSession().createQuery(sql).list();  
		return list;
	}
	
}
