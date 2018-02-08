package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.ContractManagement;
import main.service.EventService;
import main.util.DateAlert;

public class ContractTimerTask extends BaseTimerTask {
	public ContractTimerTask(ApplicationContext context) {
		super(context);
	}
	private final static int day = 1;// 提前1天通知

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": 开始执行合同到期扫描提醒！");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			// 在这里写你要执行的内容
			List<ContractManagement> cmList = DateAlert.getObjByYearDay(new ContractManagement(), day, "endTime");
			for (int i = 0; i < cmList.size(); i++) {
				// 获取销售人id
				logger.info("第 "+i+" 个合同提醒开始添加！");
				BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where CODE ='" + cmList.get(i).getSaleman().getCode() + "'").uniqueResult();
				// 向dbo.EventNew中插入数据
				BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10022'").uniqueResult();
				String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
						+ "("+etId.longValue()+",'合同到期提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有合同:"+ cmList.get(i).getContractName()+ "("+ formatter.format(cmList.get(i).getEndTime())+ ")即将到期" + "\"}','E','0')";

				
				eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
			}
			logger.info(formatter2.format(new Date())+"合同到期提示执行结束!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("合同解析信息发生异常!!!");
		}
	}
}
