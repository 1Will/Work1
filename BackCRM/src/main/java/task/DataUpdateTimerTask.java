package main.java.task;

import java.util.Date;
import java.util.List;

import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;

public class DataUpdateTimerTask extends BaseTimerTask {
	private   String year;//��
	private   String month;//����
	private long dailyNum;//�ձ���
	private long backVisitNum;//�طô���
	private long projectNum;//�½���Ŀ��
	private long weeklyNum;//�½���Ŀ��
	private long contractManagementNum;//��ͬ���� 
	private double contractManagementMoney;//��ͬ���
	private long financialManagementNum;//�½��տ����
	private double financialManagementMoney;//�տ���
	private long billingRecordNum;//�½���Ʊ����
	private double billingRecordMoney;//��Ʊ���
	
	public DataUpdateTimerTask(ApplicationContext context) {
		super(context);
	}
	@SuppressWarnings({"rawtypes"})
	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": ��ʼִ��ÿ��ͳ�Ƴ���");
			eventService = (EventService) context.getBean("eventService");//
			//�����ҵ����ݼ��ҵ��Ŷ�����֮ǰ�������submitSum��lastSubmitMoney
			//�ط�
			eventService.getSuperSession().createSQLQuery("update t_backvisit set submitNum = 1;").executeUpdate();
			//�ձ�
			eventService.getSuperSession().createSQLQuery("update t_daily set submitNum = 1;").executeUpdate();
			//��Ŀ
			eventService.getSuperSession().createSQLQuery("update t_projectinfo set submitNum = 1;").executeUpdate();
			//�ܼƻ�
			eventService.getSuperSession().createSQLQuery("update t_weekly set submitNum = 1;").executeUpdate();
			//��ͬ
			eventService.getSuperSession().createSQLQuery("update t_contractManagement set submitNum = 1,lastSubmitMoney =CONTRACTMONEY;").executeUpdate();
			//�տ�
			eventService.getSuperSession().createSQLQuery("update t_financial_management set submitNum = 1,lastSubmitMoney=TRUE_SUM;").executeUpdate();
			//��Ʊ
			eventService.getSuperSession().createSQLQuery("update t_billingRecord set submitNum = 1,lastSubmitMoney=SUM;").executeUpdate();
			//��ѯ������Ч�����µ����е�id
			
			String personnelSql ="select id from t_personnelFiles where DISABLED = 0 ";
			List perIdList  = eventService.getSuperSession().createSQLQuery(personnelSql).list();
			//�жϻ�ȡ�����µ���id�ļ����ǲ���Ϊ�գ� �����Ϊ�ձ������ϡ�
			if(perIdList!=null&&perIdList.size()>0){
			for(int i=0;i<perIdList.size();i++){
				//��ÿ��ѭ�������µ���idȥ��ѯ���н�������˵Ĳ������
				Long perId = Long.parseLong(perIdList.get(i)+"");
				List<String> dateList = DateAlert.getMonthBettween("2017-11", "2017-12");
				String date="";
				for(int j=0;j<dateList.size();j++){
					date=dateList.get(j);// new Date();//
					System.out.println("��ǰʱ��Ϊ��"+date);
					year =date.substring(0,4)+"��";
					month=date.replace("-", "��")+"��";
				
				//��ѯ�ձ��� 
				String dailyNumSql ="select COUNT(*) from t_daily where PERSON_ID  = "+perId+"  and convert(varchar,CURRENTDATE,120) like '%"+date+"%'";
				dailyNum =((Integer)eventService.getSuperSession().createSQLQuery(dailyNumSql).uniqueResult()).longValue();

				//��ѯ�ܼƻ��� 
				String weeklyNumSql ="select COUNT(*)  from t_weekly w,t_users u,t_personnelFiles p where w.RAPPORTEUR_ID =u.ID and p.CODE = u.CODE and p.Id  = "+perId+"   and convert(varchar,STARTDATE,120) like '%"+date+"%'";
				weeklyNum =((Integer)eventService.getSuperSession().createSQLQuery(weeklyNumSql).uniqueResult()).longValue();
				
				//�½��ط�   ���ݻط���
				String backVisitNumSql="select COUNT(*) from t_backvisit where convert(varchar,VISIT_DATE,120) like '%"+date+"%' and VISITOR_ID = "+perId;
				backVisitNum=((Integer)eventService.getSuperSession().createSQLQuery(backVisitNumSql).uniqueResult()).longValue();
				//�½���Ŀ��  ���ݸ�����
				String projectNumSql="select COUNT(*) from t_projectinfo where project_Controller_id ="+perId+" and convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0 ";
				projectNum =((Integer)eventService.getSuperSession().createSQLQuery(projectNumSql).uniqueResult()).longValue();
				//��ͬ��
				String contractManagementNumSql="select COUNT(*) from t_contractManagement where SALEMAN_ID ="+perId+" and  convert(varchar,CIEMDINGHTIME,120) like '%"+date+"%' and DISABLED = 0";
				contractManagementNum=((Integer)eventService.getSuperSession().createSQLQuery(contractManagementNumSql).uniqueResult()).longValue();
				
				//��ͬ���
				String contractManagementMoneySql ="select isnull(SUM(CONTRACTMONEY),0) from t_contractManagement where SALEMAN_ID ="+perId+" and  convert(varchar,CIEMDINGHTIME,120) like '%"+date+"%' and DISABLED = 0";
				contractManagementMoney=(Double)eventService.getSuperSession().createSQLQuery(contractManagementMoneySql).uniqueResult();
				
				//�տ���
				String financialManagementNumSql="select COUNT(*) from t_financial_management where PAYEE_ID ="+perId+" and  convert(varchar,COLLECTION_DATE,120) like '%"+date+"%' and DISABLED = 0";
				financialManagementNum=((Integer)eventService.getSuperSession().createSQLQuery(financialManagementNumSql).uniqueResult()).longValue();
				
				//�տ���
				String financialManagementMoneySql ="select isnull(SUM(TRUE_SUM),0) from t_financial_management where PAYEE_ID ="+perId+" and  convert(varchar,COLLECTION_DATE,120) like '%"+date+"%' and DISABLED = 0";
				financialManagementMoney=(Double)eventService.getSuperSession().createSQLQuery(financialManagementMoneySql).uniqueResult();
				
				
				//��Ʊ��
				String billingRecordNumSql="select count(*) from t_billingRecord where PAYEE_ID ="+perId+" and  convert(varchar,BILLING_TIME,120) like '%"+date+"%' and DISABLED = 0";
				billingRecordNum=((Integer)eventService.getSuperSession().createSQLQuery(billingRecordNumSql).uniqueResult()).longValue();
				
				//��Ʊ���
				String billingRecordMoneySql ="select isnull(SUM(sum),0) from t_billingRecord b where PAYEE_ID ="+perId+" and  convert(varchar,BILLING_TIME,120) like '%"+date+"%' and DISABLED = 0";
				billingRecordMoney=(Double)eventService.getSuperSession().createSQLQuery(billingRecordMoneySql).uniqueResult();
				StringBuffer sb = new StringBuffer();
				sb.append("insert into t_data(VERSION,year,month,contractManagementNum,contractManagementMoney,financialManagementNum,financialManagementMoney")
				.append(",billingRecordNum,billingRecordMoney,ActualDaily,ActualWeekly,projectNum,backvisitNum,personnelFiles_id,ShouldDaily,ShouldWeekly,Monthly")
				.append(") values (")
				.append("0"+",'"+year+"','"+month+"',"+contractManagementNum+","+contractManagementMoney+","+financialManagementNum+","+financialManagementMoney)
				.append(","+billingRecordNum+","+billingRecordMoney+","+dailyNum+","+weeklyNum+","+projectNum+","+backVisitNum+","+perId+",0,0,0")
				.append(")");
				
				//insert into t_dailyStatistic (version,dailyDate,dailyNum,dailyReplyNum,customerNum,customerIntegrity,personnelFiles_id,contactArchivesNum,contactArchivesIntegrity,backVisitNum,backVisitReplyNum,projectNum,projectPlanNum,contractManagementNum,contractManagementPlanNum,contractManagementMoney,financialManagementNum,financialManagementMoney,billingRecordNum,billingRecordMoney,businessTravelNum,leaveNum,overtimeNum) values(0,cast('2017-11-13' as datetime),0,0,0,0.0,263,0,0.0,0,0,0,0,0,0,0.0,0,0.0,0,0.0,0,0,0)
				eventService.getSuperSession().createSQLQuery(sb.toString()).executeUpdate();
				int index = i+1;//��¼��ǰ�ڼ���
				logger.info(formatter2.format(new Date())+"�ɹ�����һ����¼!!,��ǰ��"+index+"������"+perIdList.size()+"����¼");
				}
			}
			}
			
			
			logger.info(formatter2.format(new Date())+"ÿ��ͳ�Ƴ���ִ�н���!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ÿ��ͳ�Ƴ������쳣!!!");
		}
	}
}
