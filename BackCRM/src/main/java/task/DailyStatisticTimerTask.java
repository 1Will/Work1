package main.java.task;

import java.util.Date;
import java.util.List;

import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;

@SuppressWarnings({"unused","rawtypes"})
public class DailyStatisticTimerTask extends BaseTimerTask {
	private   Date dailyDate;//������
	private long dailyNum;//�ձ���
	private long dailyReplyNum;//�ձ��ظ���
	private long customerNum;//�����ͻ�
	private double customerIntegrity;//�ͻ�������
	private long contactArchivesNum;//������ϵ�� 
	private double contactArchivesIntegrity;//��ϵ��������
	private long backVisitNum;//�طô���
	private long backVisitReplyNum;//�طûظ���
	private long projectNum;//�½���Ŀ��
	private long projectPlanNum;//��Ŀ�ƻ�����
	private long projectPlanChangeNum;//��Ŀ�ƻ��ı���;
	private long projectPlanFinishNum;//��Ŀ�ƻ��������
	private long contractManagementNum;//��ͬ���� 
	private double contractManagementMoney;//��ͬ���
	private long contractManagementPlanNum;//��ͬ�ƻ�������
	private long contractManagementPlanChangeNum;//��ͬ�ƻ��ı���;
	private long contractManagementPlanFinishNum;//��ͬ�ƻ��������
	private long financialManagementNum;//�½��տ����
	private double financialManagementMoney;//�տ���
	private long billingRecordNum;//�½���Ʊ����
	private double billingRecordMoney;//��Ʊ���
	private long businessTravelNum;//��������
	private long leaveNum;//�������
	private long overtimeNum;//�Ӱ�����
	
	public DailyStatisticTimerTask(ApplicationContext context) {
		super(context);
	}

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": ��ʼִ��ÿ��ͳ�Ƴ���");
			eventService = (EventService) context.getBean("eventService");//
			//��ѯ������Ч�����µ����е�id
			String personnelSql ="select id from t_personnelFiles where DISABLED = 0 ";
			List perIdList  = eventService.getSuperSession().createSQLQuery(personnelSql).list();
			//�жϻ�ȡ�����µ���id�ļ����ǲ���Ϊ�գ� �����Ϊ�ձ������ϡ�
			if(perIdList!=null&&perIdList.size()>0){
			for(int i=0;i<perIdList.size();i++){
				//��ÿ��ѭ�������µ���idȥ��ѯ���н�������˵Ĳ������
				Long perId = Long.parseLong(perIdList.get(i)+"");
				String date=DateAlert.getEarlyYearDate(-1);
//				for(int j=1;j<82;j++){
//					date=DateAlert.getEarlyYearDate(0-j);// new Date();//��ȡ��ǰʱ��de ǰһ��
				
				//��ѯ�ձ��� 
				String dailyNumSql ="select COUNT(*) from t_daily where PERSON_ID  = "+perId+"  and convert(varchar,CURRENTDATE,120) like '%"+date+"%'";
				dailyNum =((Integer)eventService.getSuperSession().createSQLQuery(dailyNumSql).uniqueResult()).longValue();
				//��ѯ�ձ��ظ���  
				String dailyReplyNumSql ="select COUNT(*) from t_reply r  ,t_users u,t_personnelFiles p where u.CODE = p.CODE and r.USERID = u.ID and p.id = "+perId+" and convert(varchar,REPLYDATE,120) like '%"+date+"%'  and r.DAILYID is not null ";
				dailyReplyNum =((Integer)eventService.getSuperSession().createSQLQuery(dailyReplyNumSql).uniqueResult()).longValue();
				//��ѯ�½��ͻ���������ҵ��Ա�ġ� û�д�����id
				String customerNumSql ="select COUNT(*) from t_customerInfo where BUSINESSMAN_ID  = "+perId+"  and convert(varchar,CREATED_TIME,120) like '%"+date+"%'";
				customerNum =((Integer)eventService.getSuperSession().createSQLQuery(customerNumSql).uniqueResult()).longValue();
				//��ѯ�½��ͻ������ȣ�����ҵ��Ա�ġ� û�д�����id
				String customerIntegritySql ="select  isnull(AVG(CUSTOMER_INFO_INTEGRITY),0) from t_customerInfo where BUSINESSMAN_ID  = "+perId+"  and convert(varchar,CREATED_TIME,120) like '%"+date+"%'";
				customerIntegrity =(Double)eventService.getSuperSession().createSQLQuery(customerIntegritySql).uniqueResult();
				//c��ѯ�½���ϵ������������ϵ�������ͻ���ҵ��Ա��id
				String contactArchivesNumSql ="select COUNT(*) from t_contactArchives ca,t_customerInfo cu where cu.Id = ca.CUSTOMER_ID and cu.BUSINESSMAN_ID ="+perId+" and convert(varchar,ca.CREATED_TIME,120) like '%"+date+"%' ";
				contactArchivesNum=((Integer)eventService.getSuperSession().createSQLQuery(contactArchivesNumSql).uniqueResult()).longValue();
				//�½���ϵ�˵�������
				String contactArchivesIntegritySql ="select isnull(AVG(ca.CUSTOMER_INFO_INTEGRITY),0) from t_contactArchives ca,t_customerInfo cu where cu.Id = ca.CUSTOMER_ID and cu.BUSINESSMAN_ID = "+perId+" and convert(varchar,ca.CREATED_TIME,120) like '%"+date+"%' ";
				contactArchivesIntegrity=(Double)eventService.getSuperSession().createSQLQuery(contactArchivesIntegritySql).uniqueResult();
				//�½��ط�   ���ݻط���
				String backVisitNumSql="select COUNT(*) from t_backvisit where convert(varchar,VISIT_DATE,120) like '%"+date+"%' and VISITOR_ID = "+perId;
				backVisitNum=((Integer)eventService.getSuperSession().createSQLQuery(backVisitNumSql).uniqueResult()).longValue();
				//�طûظ��� ���ݻط��˵�id
				String backVisitReplyNumSql ="select COUNT(*) from t_reply r  ,t_users u,t_personnelFiles p where u.CODE = p.CODE and r.USERID = u.ID and p.Id = "+perId+" and convert(varchar,REPLYDATE,120) like '%"+date+"%'  and r.BACKVISITID is not null ";
				backVisitReplyNum =((Integer)eventService.getSuperSession().createSQLQuery(backVisitReplyNumSql).uniqueResult()).longValue();
				//�½���Ŀ��  ����f������
				String projectNumSql="select COUNT(*) from t_projectinfo pro where project_Controller_id = "+perId+" and convert(varchar,pro.CREATED_TIME,120) like'%"+date+"%'";
				projectNum =((Integer)eventService.getSuperSession().createSQLQuery(projectNumSql).uniqueResult()).longValue();
				//��Ŀ�ƻ�
				String projectPlanNumSql ="select COUNT(*) from t_projectInfoPlan where convert(varchar,CREATED_TIME,120) like '%"+date+"%'  and projectInfo_id is not null  and DISABLED = 0 and personnelFiles_id = "+perId;
				projectPlanNum =((Integer)eventService.getSuperSession().createSQLQuery(projectPlanNumSql).uniqueResult()).longValue();
				//��ͬ��
				String contractManagementNumSql="select COUNT(*) from t_contractManagement where SALEMAN_ID ="+perId+" and  convert(varchar,CIEMDINGHTIME,120) like '%"+date+"%' and DISABLED = 0";
				contractManagementNum=((Integer)eventService.getSuperSession().createSQLQuery(contractManagementNumSql).uniqueResult()).longValue();
				
				//��ͬ�ƻ�
				String contractManagementPlanNumSql ="select COUNT(*) from t_projectInfoPlan where convert(varchar,CREATED_TIME,120) like '%"+date+"%'  and contractManagement_id is not null  and DISABLED = 0 and personnelFiles_id = "+perId;
				contractManagementPlanNum =((Integer)eventService.getSuperSession().createSQLQuery(contractManagementPlanNumSql).uniqueResult()).longValue();
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
				//������
				String businessTravelNumSql="select COUNT(*) from t_ontheroadbill where APPLY_PERSON_ID="+perId+" and  convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0";
				businessTravelNum=((Integer)eventService.getSuperSession().createSQLQuery(businessTravelNumSql).uniqueResult()).longValue();
				
				//�����
				String leaveNumSql="select COUNT(*) from t_leavebill where APPLY_PERSON_ID="+perId+" and  convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0";
				leaveNum=((Integer)eventService.getSuperSession().createSQLQuery(leaveNumSql).uniqueResult()).longValue();
				
				//�Ӱ���
				String overtimeNumSql="select COUNT(*) from t_overtimebill where APPLY_PERSON_ID="+perId+" and  convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0";
				overtimeNum=((Integer)eventService.getSuperSession().createSQLQuery(overtimeNumSql).uniqueResult()).longValue();
				//����ͳ�Ʊ�
				StringBuffer inserSql=new StringBuffer();
				inserSql.append(" insert into t_dailyStatistic (")
				.append("version,dailyDate,dailyNum,dailyReplyNum,customerNum,customerIntegrity,personnelFiles_id,contactArchivesNum,contactArchivesIntegrity,backVisitNum")
				.append(",backVisitReplyNum,projectNum,projectPlanNum,contractManagementNum,contractManagementPlanNum,contractManagementMoney")
				.append(",financialManagementNum,financialManagementMoney,billingRecordNum,billingRecordMoney,businessTravelNum,leaveNum,overtimeNum")
				.append(")")
				.append(" values(0,cast('"+date+"' as datetime),"+dailyNum+","+dailyReplyNum+","+customerNum+","+customerIntegrity+","+perId+","+contactArchivesNum)
				.append(","+contactArchivesIntegrity+","+backVisitNum+","+backVisitReplyNum+","+projectNum+","+projectPlanNum+","+contractManagementNum+","+contractManagementPlanNum)
				.append(","+contractManagementMoney+","+financialManagementNum+","+financialManagementMoney+","+billingRecordNum+","+billingRecordMoney)
				.append(","+businessTravelNum+","+leaveNum+","+overtimeNum)
				.append(")");
				
				//insert into t_dailyStatistic (version,dailyDate,dailyNum,dailyReplyNum,customerNum,customerIntegrity,personnelFiles_id,contactArchivesNum,contactArchivesIntegrity,backVisitNum,backVisitReplyNum,projectNum,projectPlanNum,contractManagementNum,contractManagementPlanNum,contractManagementMoney,financialManagementNum,financialManagementMoney,billingRecordNum,billingRecordMoney,businessTravelNum,leaveNum,overtimeNum) values(0,cast('2017-11-13' as datetime),0,0,0,0.0,263,0,0.0,0,0,0,0,0,0,0.0,0,0.0,0,0.0,0,0,0)
				eventService.getSuperSession().createSQLQuery(inserSql.toString()).executeUpdate();
				int index = i+1;//��¼��ǰ�ڼ���
				logger.info(formatter2.format(new Date())+"�ɹ�����һ����¼!!,��ǰ��"+index+"������"+perIdList.size()+"����¼");
				}
//			}
			}
			
			
			logger.info(formatter2.format(new Date())+"ÿ��ͳ�Ƴ���ִ�н���!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ÿ��ͳ�Ƴ������쳣!!!");
		}
	}
}
