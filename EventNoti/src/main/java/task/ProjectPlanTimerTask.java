package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.pojo.ProjectInfoPlan;
import main.service.EventService;
import main.util.DateAlert;

public class ProjectPlanTimerTask extends BaseTimerTask {
	private final static int sday = 1;// 开始时间提前1天通知
	private final static int eday = 1;// 结束时间提前1天通知

	@Override
	public void run() {
		logger.info(formatter2.format(new Date())+": 开始执行项目计划扫描提醒！");
		eventService = (EventService) context.getBean("eventService");
		List<ProjectInfoPlan> spjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), sday, "startDate");
		List<ProjectInfoPlan> epjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), eday, "endDate");
		try {
			// 添加开始时间提醒
			if (spjPlans != null) {
				logger.info("开始增加项目计划开始时间提醒！");
				for (int i = 0; i < spjPlans.size(); i++) {
					// 获取销售人id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ spjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// 向dbo.t_work_warnning中插入数据
					String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
							+ "values (0,'项目计划开始提醒','有项目计划:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")即将开始','"
							+ formatter.format(new Date()) + "',0," + creatorId.intValue() + ",null)";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
			// 添加结束时间提醒
			if (spjPlans != null) {
				logger.info("开始增加项目计划结束时间提醒*****");
				for (int i = 0; i < epjPlans.size(); i++) {
					// 获取销售人id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ epjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// 向dbo.t_work_warnning中插入数据
					String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
							+ "values (0,'项目计划结束提醒','有项目计划:"+ epjPlans.get(i).getName()+ "("+ formatter.format(epjPlans.get(i).getEndDate())+ ")即将结束','"
							+ formatter.format(new Date()) + "',0," + creatorId.intValue() + ",null)";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(formatter2.format(new Date())+": 项目计划扫描提醒结束！");
	}

}
