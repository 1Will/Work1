package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.pojo.ProjectInfoPlan;
import main.service.EventService;
import main.util.DateAlert;

public class ProjectPlanTimerTask extends BaseTimerTask {
	private final static int sday = 1;// ��ʼʱ����ǰ1��֪ͨ
	private final static int eday = 1;// ����ʱ����ǰ1��֪ͨ

	@Override
	public void run() {
		logger.info(formatter2.format(new Date())+": ��ʼִ����Ŀ�ƻ�ɨ�����ѣ�");
		eventService = (EventService) context.getBean("eventService");
		List<ProjectInfoPlan> spjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), sday, "startDate");
		List<ProjectInfoPlan> epjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), eday, "endDate");
		try {
			// ��ӿ�ʼʱ������
			if (spjPlans != null) {
				logger.info("��ʼ������Ŀ�ƻ���ʼʱ�����ѣ�");
				for (int i = 0; i < spjPlans.size(); i++) {
					// ��ȡ������id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ spjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// ��dbo.t_work_warnning�в�������
					String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
							+ "values (0,'��Ŀ�ƻ���ʼ����','����Ŀ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")������ʼ','"
							+ formatter.format(new Date()) + "',0," + creatorId.intValue() + ",null)";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
			// ��ӽ���ʱ������
			if (spjPlans != null) {
				logger.info("��ʼ������Ŀ�ƻ�����ʱ������*****");
				for (int i = 0; i < epjPlans.size(); i++) {
					// ��ȡ������id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ epjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// ��dbo.t_work_warnning�в�������
					String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
							+ "values (0,'��Ŀ�ƻ���������','����Ŀ�ƻ�:"+ epjPlans.get(i).getName()+ "("+ formatter.format(epjPlans.get(i).getEndDate())+ ")��������','"
							+ formatter.format(new Date()) + "',0," + creatorId.intValue() + ",null)";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(formatter2.format(new Date())+": ��Ŀ�ƻ�ɨ�����ѽ�����");
	}

}
