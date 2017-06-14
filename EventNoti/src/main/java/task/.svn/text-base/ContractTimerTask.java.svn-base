package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.pojo.ContractManagement;
import main.service.EventService;
import main.util.DateAlert;

public class ContractTimerTask extends BaseTimerTask {
	private final static int day = 1;// 提前1天通知

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": 开始执行合同扫描提醒！");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			// 在这里写你要执行的内容
			List<ContractManagement> cmList = DateAlert.getObjByYearDay(new ContractManagement(), day, "endTime");
			for (int i = 0; i < cmList.size(); i++) {
				// 获取销售人id
				logger.info("第 "+i+" 个合同提醒开始添加！");
				BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where CODE ='" + cmList.get(i).getSaleman().getCode() + "'").uniqueResult();
				// 向dbo.t_work_warnning中插入数据
				String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
						+ "values (0,'合同到期提醒','有合同:"+ cmList.get(i).getContractName()+ "("+ formatter.format(cmList.get(i).getEndTime())+ ")即将到期','"+ formatter2.format(new Date())+ "',0," + creatorId.intValue() + ",null)";
				eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
			}
			logger.info(formatter2.format(new Date())+"合同提示执行结束!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("合同解析信息发生异常!!!");
		}
	}

}
