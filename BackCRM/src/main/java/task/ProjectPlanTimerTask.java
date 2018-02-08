package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.ProjectInfoPlan;
import main.service.EventService;
import main.util.DateAlert;

public class ProjectPlanTimerTask extends BaseTimerTask {
	public ProjectPlanTimerTask(ApplicationContext context) {
		super(context);
	}
	private final static int sday = 1;// 开始时间提前1天通知
	private final static int eday = 1;// 结束时间提前1天通知

	@Override
	public void run() {
		logger.info(formatter2.format(new Date())+": 开始执行项目/合同/生产经营计划 扫描提醒！");
		eventService = (EventService) context.getBean("eventService");
		List<ProjectInfoPlan> spjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), sday, "startDate");
		List<ProjectInfoPlan> epjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), eday, "endDate");
		try {
			// 添加开始时间提醒
			if (spjPlans != null) {
				for (int i = 0; i < spjPlans.size(); i++) {
					// 获取销售人id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ spjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// 向dbo.EventNew中插入数据
					BigDecimal etId =null;
					String sql = null;
					if(spjPlans.get(i).getProjectInfo()!=null){
						logger.info("插入项目计划开始时间提醒！");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10018'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'项目计划开始提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有项目计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将开始" + "\"}','E','0')";
					}else if(spjPlans.get(i).getProductionOperationDetail()!=null){
						logger.info("插入生产经营计划开始时间提醒！");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10036'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'生产经营计划开始提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有生产经营计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将开始" + "\"}','E','0')";
					}else{
						logger.info("插入合同计划开始时间提醒！");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10020'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'合同计划开始提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有合同计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将开始" + "\"}','E','0')";
					}
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
			// 添加结束时间提醒
			if (spjPlans != null) {
				for (int i = 0; i < epjPlans.size(); i++) {
					// 获取销售人id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ epjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// 向dbo.EventNew中插入数据
					String sql = null;
					BigDecimal etId =null;
					if(spjPlans.get(i).getProjectInfo()!=null){
						logger.info("插入项目计划结束时间提醒！");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10019'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId.longValue()+",'项目计划结束提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有项目计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将结束" + "\"}','E','0')";
					}else if(spjPlans.get(i).getProductionOperationDetail()!=null){
						logger.info("插入生产经营计划结束时间提醒！");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10037'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId.longValue()+",'生产经营计划结束提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有生产经营计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将结束" + "\"}','E','0')";
					}else{
						logger.info("插入合同计划结束时间提醒！");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10021'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId.longValue()+",'合同计划结束提醒','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "有合同计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将结束" + "\"}','E','0')";
					}
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(formatter2.format(new Date())+": 项目/合同/生产经营计划扫描提醒结束！");
	}
}
