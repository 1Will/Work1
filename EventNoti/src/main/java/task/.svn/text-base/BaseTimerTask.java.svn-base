package main.java.task;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

import main.service.EventService;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTimerTask extends TimerTask {
	public static EventService eventService;// 数据库操作接口
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	public Logger logger = Logger.getRootLogger();

	@Override
	public void run() {

	}
}
