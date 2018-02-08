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
	private final static int sday = 1;// ��ʼʱ����ǰ1��֪ͨ
	private final static int eday = 1;// ����ʱ����ǰ1��֪ͨ

	@Override
	public void run() {
		logger.info(formatter2.format(new Date())+": ��ʼִ����Ŀ/��ͬ/������Ӫ�ƻ� ɨ�����ѣ�");
		eventService = (EventService) context.getBean("eventService");
		List<ProjectInfoPlan> spjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), sday, "startDate");
		List<ProjectInfoPlan> epjPlans = DateAlert.getObjByYearDay(new ProjectInfoPlan(), eday, "endDate");
		try {
			// ��ӿ�ʼʱ������
			if (spjPlans != null) {
				for (int i = 0; i < spjPlans.size(); i++) {
					// ��ȡ������id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ spjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// ��dbo.EventNew�в�������
					BigDecimal etId =null;
					String sql = null;
					if(spjPlans.get(i).getProjectInfo()!=null){
						logger.info("������Ŀ�ƻ���ʼʱ�����ѣ�");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10018'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'��Ŀ�ƻ���ʼ����','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "����Ŀ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")������ʼ" + "\"}','E','0')";
					}else if(spjPlans.get(i).getProductionOperationDetail()!=null){
						logger.info("����������Ӫ�ƻ���ʼʱ�����ѣ�");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10036'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'������Ӫ�ƻ���ʼ����','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "��������Ӫ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")������ʼ" + "\"}','E','0')";
					}else{
						logger.info("�����ͬ�ƻ���ʼʱ�����ѣ�");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10020'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId+",'��ͬ�ƻ���ʼ����','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "�к�ͬ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")������ʼ" + "\"}','E','0')";
					}
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
			// ��ӽ���ʱ������
			if (spjPlans != null) {
				for (int i = 0; i < epjPlans.size(); i++) {
					// ��ȡ������id
					BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery(
					"select id from dbo.t_users where CODE ='"+ epjPlans.get(i).getPersonnelFiles().getCode() + "'").uniqueResult();
					// ��dbo.EventNew�в�������
					String sql = null;
					BigDecimal etId =null;
					if(spjPlans.get(i).getProjectInfo()!=null){
						logger.info("������Ŀ�ƻ�����ʱ�����ѣ�");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10019'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId.longValue()+",'��Ŀ�ƻ���������','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "����Ŀ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")��������" + "\"}','E','0')";
					}else if(spjPlans.get(i).getProductionOperationDetail()!=null){
						logger.info("����������Ӫ�ƻ�����ʱ�����ѣ�");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10037'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId.longValue()+",'������Ӫ�ƻ���������','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "��������Ӫ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")��������" + "\"}','E','0')";
					}else{
						logger.info("�����ͬ�ƻ�����ʱ�����ѣ�");
						etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10021'").uniqueResult();
						sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
								+ "("+etId.longValue()+",'��ͬ�ƻ���������','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "�к�ͬ�ƻ�:"+ spjPlans.get(i).getName()+ "("+ formatter.format(spjPlans.get(i).getStartDate())+ ")��������" + "\"}','E','0')";
					}
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(formatter2.format(new Date())+": ��Ŀ/��ͬ/������Ӫ�ƻ�ɨ�����ѽ�����");
	}
}
