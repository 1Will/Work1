package main.java.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.pojo.ContractManagement;
import main.service.EventService;
import main.util.DateAlert;

public class ContractTimerTask extends TimerTask {
	public static EventService eventService;// 数据库操作接口
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
	private static int day = 1;// 提前1天通知

	public void run() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			// 在这里写你要执行的内容
			List<ContractManagement> cmList = DateAlert.getObjByYearDay(new ContractManagement(), day, "endTime");
			for (int i = 0; i < cmList.size(); i++) {
				// 获取销售人id
				BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where CODE ='" + cmList.get(i).getSaleman().getCode() + "'").uniqueResult();
				// 向dbo.t_work_warnning中插入数据
				String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
						+ "values (0,'合同到期提醒','有合同:"+ cmList.get(i).getContractName()+ "("+ formatter2.format(cmList.get(i).getEndTime())+ ")即将到期','"+ formatter.format(new Date())+ "',0," + creatorId.intValue() + ",null)";
				eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
			}
			System.out.println("执行当前时间" + formatter.format(Calendar.getInstance().getTime()));
			System.out.println("*************************合同提示执行完成***********************");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("xxxxxxxxxxxxxxxx 合同解析信息发生异常  xxxxxxxxxxxxxxxx");
		}
	}

}
