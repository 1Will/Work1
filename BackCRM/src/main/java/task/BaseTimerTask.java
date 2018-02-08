package main.java.task;

import java.text.SimpleDateFormat;
import java.util.TimerTask;

import main.service.EventService;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public abstract class BaseTimerTask extends TimerTask {
	public static EventService eventService;// 数据库操作接口
	/**
	 * yyyy-MM-dd
	 */
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public ApplicationContext context ;
	public Logger logger = Logger.getRootLogger();
	
	public BaseTimerTask(ApplicationContext context){
		this.context =context;
	}
	
	@Override
	public void run() {

	}
}
