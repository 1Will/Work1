package main.java.task;

import java.util.Date;
import java.util.List;

import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;

@SuppressWarnings({"unused","rawtypes"})
public class DailyStatisticTimerTask extends BaseTimerTask {
	private   Date dailyDate;//年月日
	private long dailyNum;//日报数
	private long dailyReplyNum;//日报回复数
	private long customerNum;//新增客户
	private double customerIntegrity;//客户完整度
	private long contactArchivesNum;//新增联系人 
	private double contactArchivesIntegrity;//联系人完整度
	private long backVisitNum;//回访次数
	private long backVisitReplyNum;//回访回复数
	private long projectNum;//新建项目数
	private long projectPlanNum;//项目计划数；
	private long projectPlanChangeNum;//项目计划改变数;
	private long projectPlanFinishNum;//项目计划完成数；
	private long contractManagementNum;//合同数量 
	private double contractManagementMoney;//合同金额
	private long contractManagementPlanNum;//合同计划数量。
	private long contractManagementPlanChangeNum;//合同计划改变数;
	private long contractManagementPlanFinishNum;//合同计划完成数；
	private long financialManagementNum;//新建收款单数量
	private double financialManagementMoney;//收款金额
	private long billingRecordNum;//新建开票数量
	private double billingRecordMoney;//开票金额
	private long businessTravelNum;//出差数量
	private long leaveNum;//请假数量
	private long overtimeNum;//加班数量
	
	public DailyStatisticTimerTask(ApplicationContext context) {
		super(context);
	}

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": 开始执行每日统计程序！");
			eventService = (EventService) context.getBean("eventService");//
			//查询所有有效的人事档案中的id
			String personnelSql ="select id from t_personnelFiles where DISABLED = 0 ";
			List perIdList  = eventService.getSuperSession().createSQLQuery(personnelSql).list();
			//判断获取的人事档案id的集合是不是为空， 如果不为空遍历集合。
			if(perIdList!=null&&perIdList.size()>0){
			for(int i=0;i<perIdList.size();i++){
				//将每次循环的人事档案id去查询所有今天这个人的操作情况
				Long perId = Long.parseLong(perIdList.get(i)+"");
				String date=DateAlert.getEarlyYearDate(-1);
//				for(int j=1;j<82;j++){
//					date=DateAlert.getEarlyYearDate(0-j);// new Date();//获取当前时间de 前一天
				
				//查询日报数 
				String dailyNumSql ="select COUNT(*) from t_daily where PERSON_ID  = "+perId+"  and convert(varchar,CURRENTDATE,120) like '%"+date+"%'";
				dailyNum =((Integer)eventService.getSuperSession().createSQLQuery(dailyNumSql).uniqueResult()).longValue();
				//查询日报回复数  
				String dailyReplyNumSql ="select COUNT(*) from t_reply r  ,t_users u,t_personnelFiles p where u.CODE = p.CODE and r.USERID = u.ID and p.id = "+perId+" and convert(varchar,REPLYDATE,120) like '%"+date+"%'  and r.DAILYID is not null ";
				dailyReplyNum =((Integer)eventService.getSuperSession().createSQLQuery(dailyReplyNumSql).uniqueResult()).longValue();
				//查询新建客户数，根据业务员的。 没有创建人id
				String customerNumSql ="select COUNT(*) from t_customerInfo where BUSINESSMAN_ID  = "+perId+"  and convert(varchar,CREATED_TIME,120) like '%"+date+"%'";
				customerNum =((Integer)eventService.getSuperSession().createSQLQuery(customerNumSql).uniqueResult()).longValue();
				//查询新建客户完整度，根据业务员的。 没有创建人id
				String customerIntegritySql ="select  isnull(AVG(CUSTOMER_INFO_INTEGRITY),0) from t_customerInfo where BUSINESSMAN_ID  = "+perId+"  and convert(varchar,CREATED_TIME,120) like '%"+date+"%'";
				customerIntegrity =(Double)eventService.getSuperSession().createSQLQuery(customerIntegritySql).uniqueResult();
				//c查询新建联系人数。根据联系人所属客户的业务员的id
				String contactArchivesNumSql ="select COUNT(*) from t_contactArchives ca,t_customerInfo cu where cu.Id = ca.CUSTOMER_ID and cu.BUSINESSMAN_ID ="+perId+" and convert(varchar,ca.CREATED_TIME,120) like '%"+date+"%' ";
				contactArchivesNum=((Integer)eventService.getSuperSession().createSQLQuery(contactArchivesNumSql).uniqueResult()).longValue();
				//新建联系人的完整度
				String contactArchivesIntegritySql ="select isnull(AVG(ca.CUSTOMER_INFO_INTEGRITY),0) from t_contactArchives ca,t_customerInfo cu where cu.Id = ca.CUSTOMER_ID and cu.BUSINESSMAN_ID = "+perId+" and convert(varchar,ca.CREATED_TIME,120) like '%"+date+"%' ";
				contactArchivesIntegrity=(Double)eventService.getSuperSession().createSQLQuery(contactArchivesIntegritySql).uniqueResult();
				//新建回访   根据回访人
				String backVisitNumSql="select COUNT(*) from t_backvisit where convert(varchar,VISIT_DATE,120) like '%"+date+"%' and VISITOR_ID = "+perId;
				backVisitNum=((Integer)eventService.getSuperSession().createSQLQuery(backVisitNumSql).uniqueResult()).longValue();
				//回访回复数 根据回访人的id
				String backVisitReplyNumSql ="select COUNT(*) from t_reply r  ,t_users u,t_personnelFiles p where u.CODE = p.CODE and r.USERID = u.ID and p.Id = "+perId+" and convert(varchar,REPLYDATE,120) like '%"+date+"%'  and r.BACKVISITID is not null ";
				backVisitReplyNum =((Integer)eventService.getSuperSession().createSQLQuery(backVisitReplyNumSql).uniqueResult()).longValue();
				//新建项目数  根据f负责人
				String projectNumSql="select COUNT(*) from t_projectinfo pro where project_Controller_id = "+perId+" and convert(varchar,pro.CREATED_TIME,120) like'%"+date+"%'";
				projectNum =((Integer)eventService.getSuperSession().createSQLQuery(projectNumSql).uniqueResult()).longValue();
				//项目计划
				String projectPlanNumSql ="select COUNT(*) from t_projectInfoPlan where convert(varchar,CREATED_TIME,120) like '%"+date+"%'  and projectInfo_id is not null  and DISABLED = 0 and personnelFiles_id = "+perId;
				projectPlanNum =((Integer)eventService.getSuperSession().createSQLQuery(projectPlanNumSql).uniqueResult()).longValue();
				//合同数
				String contractManagementNumSql="select COUNT(*) from t_contractManagement where SALEMAN_ID ="+perId+" and  convert(varchar,CIEMDINGHTIME,120) like '%"+date+"%' and DISABLED = 0";
				contractManagementNum=((Integer)eventService.getSuperSession().createSQLQuery(contractManagementNumSql).uniqueResult()).longValue();
				
				//合同计划
				String contractManagementPlanNumSql ="select COUNT(*) from t_projectInfoPlan where convert(varchar,CREATED_TIME,120) like '%"+date+"%'  and contractManagement_id is not null  and DISABLED = 0 and personnelFiles_id = "+perId;
				contractManagementPlanNum =((Integer)eventService.getSuperSession().createSQLQuery(contractManagementPlanNumSql).uniqueResult()).longValue();
				//合同金额
				String contractManagementMoneySql ="select isnull(SUM(CONTRACTMONEY),0) from t_contractManagement where SALEMAN_ID ="+perId+" and  convert(varchar,CIEMDINGHTIME,120) like '%"+date+"%' and DISABLED = 0";
				contractManagementMoney=(Double)eventService.getSuperSession().createSQLQuery(contractManagementMoneySql).uniqueResult();
				
				//收款数
				String financialManagementNumSql="select COUNT(*) from t_financial_management where PAYEE_ID ="+perId+" and  convert(varchar,COLLECTION_DATE,120) like '%"+date+"%' and DISABLED = 0";
				financialManagementNum=((Integer)eventService.getSuperSession().createSQLQuery(financialManagementNumSql).uniqueResult()).longValue();
				
				//收款金额
				String financialManagementMoneySql ="select isnull(SUM(TRUE_SUM),0) from t_financial_management where PAYEE_ID ="+perId+" and  convert(varchar,COLLECTION_DATE,120) like '%"+date+"%' and DISABLED = 0";
				financialManagementMoney=(Double)eventService.getSuperSession().createSQLQuery(financialManagementMoneySql).uniqueResult();
				
				
				//开票数
				String billingRecordNumSql="select count(*) from t_billingRecord where PAYEE_ID ="+perId+" and  convert(varchar,BILLING_TIME,120) like '%"+date+"%' and DISABLED = 0";
				billingRecordNum=((Integer)eventService.getSuperSession().createSQLQuery(billingRecordNumSql).uniqueResult()).longValue();
				
				//开票金额
				String billingRecordMoneySql ="select isnull(SUM(sum),0) from t_billingRecord b where PAYEE_ID ="+perId+" and  convert(varchar,BILLING_TIME,120) like '%"+date+"%' and DISABLED = 0";
				billingRecordMoney=(Double)eventService.getSuperSession().createSQLQuery(billingRecordMoneySql).uniqueResult();
				//出差数
				String businessTravelNumSql="select COUNT(*) from t_ontheroadbill where APPLY_PERSON_ID="+perId+" and  convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0";
				businessTravelNum=((Integer)eventService.getSuperSession().createSQLQuery(businessTravelNumSql).uniqueResult()).longValue();
				
				//请假数
				String leaveNumSql="select COUNT(*) from t_leavebill where APPLY_PERSON_ID="+perId+" and  convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0";
				leaveNum=((Integer)eventService.getSuperSession().createSQLQuery(leaveNumSql).uniqueResult()).longValue();
				
				//加班数
				String overtimeNumSql="select COUNT(*) from t_overtimebill where APPLY_PERSON_ID="+perId+" and  convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0";
				overtimeNum=((Integer)eventService.getSuperSession().createSQLQuery(overtimeNumSql).uniqueResult()).longValue();
				//插入统计表
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
				int index = i+1;//记录当前第几条
				logger.info(formatter2.format(new Date())+"成功插入一条记录!!,当前第"+index+"条，共"+perIdList.size()+"条记录");
				}
//			}
			}
			
			
			logger.info(formatter2.format(new Date())+"每日统计程序执行结束!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("每日统计程序发生异常!!!");
		}
	}
}
