package main.java.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.BackVisit;
import main.pojo.CustomerInfo;
import main.service.BackVisitService;
import main.service.CustomerInfoService;
import main.service.EventService;
import main.util.DateAlert;

public class BackVisitIntervalTimerTask extends BaseTimerTask {
	public BackVisitIntervalTimerTask(ApplicationContext context) {
		super(context);
	}

	public static BackVisitService backVisitService;// 回访记录
	public static CustomerInfoService customerInfoService;
	// 分别为1星、2星、3星、4星、5星用户天数对应正常、初步、中度、高度、严重状态
	private final static int day[][] = {{ 35, 42, 49, 56 },{ 28, 35, 42, 49 },{ 21, 28, 35, 42 },{ 14, 21, 28, 35 },{ 7, 14, 21, 28 }};
	private final static int states[] = { 1, 2, 3, 4, 5 };// 正常、初步、中度、高度、严重状态

	@Override
	public void run() {
		logger.info(formatter2.format(new Date())+": 开始执行回访记录扫描提醒！");
		eventService = (EventService) context.getBean("eventService");
		backVisitService = (BackVisitService) context.getBean("backVisitService");
		customerInfoService = (CustomerInfoService) context.getBean("customerInfoService");
		BigDecimal zc =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='20001'").uniqueResult();
		BigDecimal cb =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='20002'").uniqueResult();
		BigDecimal zd =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='20003'").uniqueResult();
		BigDecimal gd =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='20004'").uniqueResult();
		BigDecimal yz =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='20005'").uniqueResult();
		Long maxId = customerInfoService.getMaxId();
		logger.info("客户档案最大id：" + maxId);
		for (Long i = 1L; i <= maxId; i++) {
			logger.info("客户档案ID为 " + i+" 的数据开始更改");
			CustomerInfo customerInfo = null;
			try {
				customerInfo = customerInfoService.getById(i);
			} catch (Exception e) {
				logger.info("警告：ID为" + i + "的客户不存在！跳过");
				continue;
			}
			List<BackVisit> bvList = backVisitService.getBackVisitByCustomerName(customerInfo.getCustomerName());
			if (bvList.size() > 0) {
				Date date = bvList.get(0).getVisitDate();
				Date now = new Date();
				
				for (int j = 0; j < bvList.size(); j++) {
					//下次回访提醒
					Date nextDate = bvList.get(j).getNextVisitDate();
					if(nextDate!=null){
						long spaceTime = DateAlert.betweenDays(nextDate,now);
						if(spaceTime>=0L&&spaceTime<2){
							logger.info("开始添加拜访提醒！");
							BigDecimal userId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select u.ID from dbo.t_users as u, dbo.t_personnelFiles as p where u.CODE =p.CODE and p.id ="+bvList.get(j).getVisiterid()).uniqueResult();
							BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10017'").uniqueResult();
							String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
									+ "("+etId.longValue()+",'拜访提醒','{\"users\":\"" + userId.longValue() + "\",\"content\":\""+ "有客户:"+bvList.get(j).getCustomerName()+ ","+formatter.format(bvList.get(j).getNextVisitDate()) + "需要拜访" + "\"}','E','0')";
							
							eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
							logger.info("拜访提醒添加完成！");
						}
					}
					date = (DateAlert.betweenDays(date, bvList.get(j).getVisitDate()) > 0) ? date : bvList.get(j).getVisitDate();
				}
				try {
					//去除时分秒，不是有病
					now = formatter.parse(formatter.format(now));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//修改拜访时间间隔及告警状态
				Long days = DateAlert.betweenDays(now, date);
				customerInfo.setUnconnect(days);
				// 339一星、340二星、341三星、342四星、343五星
				if(getState(customerInfo.getStep().intValue(), days.intValue())==1){
					customerInfo.setState(zc.longValue());
				}
				if(getState(customerInfo.getStep().intValue(), days.intValue())==2){
					customerInfo.setState(cb.longValue());
				}
				if(getState(customerInfo.getStep().intValue(), days.intValue())==3){
					customerInfo.setState(zd.longValue());
				}
				if(getState(customerInfo.getStep().intValue(), days.intValue())==4){
					customerInfo.setState(gd.longValue());
				}
				if(getState(customerInfo.getStep().intValue(), days.intValue())==5){
					customerInfo.setState(yz.longValue());
				}
				customerInfo.setBackVisitSum((long)bvList.size());
				customerInfo.setNearstBackvisitDate(date);
				customerInfoService.updateCustomerInfo(customerInfo);
			} else {
				customerInfo.setUnconnect(1000000L);
				customerInfoService.updateCustomerInfo(customerInfo);
			}

		}
		logger.info(formatter2.format(new Date())+": 回访记录扫描执行完成！");
	}

	public Long getState(int level, int days) {
		Long state = null;
		switch (level) {
		case 339:// 一星客户339
			state = judgeState(0, days);
			break;
		case 340:// 二星客户340
			state = judgeState(1, days);
			break;
		case 341:// 三星客户341
			state = judgeState(2, days);
			break;
		case 342:// 四星客户342
			state = judgeState(3, days);
			break;
		case 343:// 五星客户343
			state = judgeState(4, days);
			break;
		}
		return state;
	}

	public Long judgeState(int i, int days) {
		Long state = null;
		if (days <= day[i][0]) {
			state = (long) states[0];
		}
		if (day[i][0] < days && days <= day[i][1]) {
			state = (long) states[1];
		}
		if (day[i][1] < days && days <= day[i][2]) {
			state = (long) states[2];
		}
		if (day[i][2] < days && days <= day[i][3]) {
			state = (long) states[3];
		}
		if (day[i][3] < days) {
			state = (long) states[4];
		}
		return state;
	}
}
