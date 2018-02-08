package main.java.task;

import java.util.Date;
import java.util.List;

import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;

public class DataUpdateTimerTask extends BaseTimerTask {
	private   String year;//年
	private   String month;//年月
	private long dailyNum;//日报数
	private long backVisitNum;//回访次数
	private long projectNum;//新建项目数
	private long weeklyNum;//新建项目数
	private long contractManagementNum;//合同数量 
	private double contractManagementMoney;//合同金额
	private long financialManagementNum;//新建收款单数量
	private double financialManagementMoney;//收款金额
	private long billingRecordNum;//新建开票数量
	private double billingRecordMoney;//开票金额
	
	public DataUpdateTimerTask(ApplicationContext context) {
		super(context);
	}
	@SuppressWarnings({"rawtypes"})
	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": 开始执行每日统计程序！");
			eventService = (EventService) context.getBean("eventService");//
			//更新我的数据及我的团队数据之前必须更新submitSum和lastSubmitMoney
			//回访
			eventService.getSuperSession().createSQLQuery("update t_backvisit set submitNum = 1;").executeUpdate();
			//日报
			eventService.getSuperSession().createSQLQuery("update t_daily set submitNum = 1;").executeUpdate();
			//项目
			eventService.getSuperSession().createSQLQuery("update t_projectinfo set submitNum = 1;").executeUpdate();
			//周计划
			eventService.getSuperSession().createSQLQuery("update t_weekly set submitNum = 1;").executeUpdate();
			//合同
			eventService.getSuperSession().createSQLQuery("update t_contractManagement set submitNum = 1,lastSubmitMoney =CONTRACTMONEY;").executeUpdate();
			//收款
			eventService.getSuperSession().createSQLQuery("update t_financial_management set submitNum = 1,lastSubmitMoney=TRUE_SUM;").executeUpdate();
			//开票
			eventService.getSuperSession().createSQLQuery("update t_billingRecord set submitNum = 1,lastSubmitMoney=SUM;").executeUpdate();
			//查询所有有效的人事档案中的id
			
			String personnelSql ="select id from t_personnelFiles where DISABLED = 0 ";
			List perIdList  = eventService.getSuperSession().createSQLQuery(personnelSql).list();
			//判断获取的人事档案id的集合是不是为空， 如果不为空遍历集合。
			if(perIdList!=null&&perIdList.size()>0){
			for(int i=0;i<perIdList.size();i++){
				//将每次循环的人事档案id去查询所有今天这个人的操作情况
				Long perId = Long.parseLong(perIdList.get(i)+"");
				List<String> dateList = DateAlert.getMonthBettween("2017-11", "2017-12");
				String date="";
				for(int j=0;j<dateList.size();j++){
					date=dateList.get(j);// new Date();//
					System.out.println("当前时间为："+date);
					year =date.substring(0,4)+"年";
					month=date.replace("-", "年")+"月";
				
				//查询日报数 
				String dailyNumSql ="select COUNT(*) from t_daily where PERSON_ID  = "+perId+"  and convert(varchar,CURRENTDATE,120) like '%"+date+"%'";
				dailyNum =((Integer)eventService.getSuperSession().createSQLQuery(dailyNumSql).uniqueResult()).longValue();

				//查询周计划数 
				String weeklyNumSql ="select COUNT(*)  from t_weekly w,t_users u,t_personnelFiles p where w.RAPPORTEUR_ID =u.ID and p.CODE = u.CODE and p.Id  = "+perId+"   and convert(varchar,STARTDATE,120) like '%"+date+"%'";
				weeklyNum =((Integer)eventService.getSuperSession().createSQLQuery(weeklyNumSql).uniqueResult()).longValue();
				
				//新建回访   根据回访人
				String backVisitNumSql="select COUNT(*) from t_backvisit where convert(varchar,VISIT_DATE,120) like '%"+date+"%' and VISITOR_ID = "+perId;
				backVisitNum=((Integer)eventService.getSuperSession().createSQLQuery(backVisitNumSql).uniqueResult()).longValue();
				//新建项目数  根据负责人
				String projectNumSql="select COUNT(*) from t_projectinfo where project_Controller_id ="+perId+" and convert(varchar,CREATED_TIME,120) like '%"+date+"%' and DISABLED = 0 ";
				projectNum =((Integer)eventService.getSuperSession().createSQLQuery(projectNumSql).uniqueResult()).longValue();
				//合同数
				String contractManagementNumSql="select COUNT(*) from t_contractManagement where SALEMAN_ID ="+perId+" and  convert(varchar,CIEMDINGHTIME,120) like '%"+date+"%' and DISABLED = 0";
				contractManagementNum=((Integer)eventService.getSuperSession().createSQLQuery(contractManagementNumSql).uniqueResult()).longValue();
				
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
				StringBuffer sb = new StringBuffer();
				sb.append("insert into t_data(VERSION,year,month,contractManagementNum,contractManagementMoney,financialManagementNum,financialManagementMoney")
				.append(",billingRecordNum,billingRecordMoney,ActualDaily,ActualWeekly,projectNum,backvisitNum,personnelFiles_id,ShouldDaily,ShouldWeekly,Monthly")
				.append(") values (")
				.append("0"+",'"+year+"','"+month+"',"+contractManagementNum+","+contractManagementMoney+","+financialManagementNum+","+financialManagementMoney)
				.append(","+billingRecordNum+","+billingRecordMoney+","+dailyNum+","+weeklyNum+","+projectNum+","+backVisitNum+","+perId+",0,0,0")
				.append(")");
				
				//insert into t_dailyStatistic (version,dailyDate,dailyNum,dailyReplyNum,customerNum,customerIntegrity,personnelFiles_id,contactArchivesNum,contactArchivesIntegrity,backVisitNum,backVisitReplyNum,projectNum,projectPlanNum,contractManagementNum,contractManagementPlanNum,contractManagementMoney,financialManagementNum,financialManagementMoney,billingRecordNum,billingRecordMoney,businessTravelNum,leaveNum,overtimeNum) values(0,cast('2017-11-13' as datetime),0,0,0,0.0,263,0,0.0,0,0,0,0,0,0,0.0,0,0.0,0,0.0,0,0,0)
				eventService.getSuperSession().createSQLQuery(sb.toString()).executeUpdate();
				int index = i+1;//记录当前第几条
				logger.info(formatter2.format(new Date())+"成功插入一条记录!!,当前第"+index+"条，共"+perIdList.size()+"条记录");
				}
			}
			}
			
			
			logger.info(formatter2.format(new Date())+"每日统计程序执行结束!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("每日统计程序发生异常!!!");
		}
	}
}
