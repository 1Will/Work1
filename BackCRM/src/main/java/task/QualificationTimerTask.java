package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import main.service.EventService;
import main.util.DateAlert;

import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;

public class QualificationTimerTask extends BaseTimerTask {
	
	private final static int day_a = 30;// 提前30天通知
	private final static int day_b = 60;// 提前60天通知
	private final static int day_c = 90;// 提前90天通知
	
	public QualificationTimerTask(ApplicationContext context) {
		super(context);
	}
	
	public void run() {
		logger.info(formatter2.format(new Date())+": 开始资格证书扫描！");
		eventService = (EventService) context.getBean("eventService");
		alertUser(day_a);
		alertUser(day_b);
		alertUser(day_c);
		logger.info(formatter2.format(new Date())+": 资格证书扫描结束！");
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public void alertUser(int day){
		List ids = eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_perQualification where ENDTIME <='"+DateAlert.getEarlyYearDate(day)+"'").list();
		if(ids!=null && ids.size()>0){
			BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10038'").uniqueResult();
			for (int i = 0; i < ids.size(); i++) {
				String psql = "select u.ID as uid ,convert(varchar(255),q.NAME ) as name ,q.ENDTIME as endtime ,q.dept_id as dept from t_perQualification q ,t_personnelFiles p ,t_users u where q.personnelFiles_id =p.Id and p.CODE = u.CODE and q.Id = "+ids.get(i);
				List<Map> perLists =(List<Map>) eventService.getSuperSession().createSQLQuery(psql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				if(perLists!=null && perLists.size()>0){
					BigDecimal deptUser =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT u.ID FROM t_department d,t_users u where d.LEADER = u.NAME and d.TEL = u.tel_number and d.id = "+perLists.get(0).get("dept")).uniqueResult();
					String users = perLists.get(0).get("uid").toString();
					users += deptUser == null ? "" : ","+deptUser.longValue();
					//个人证书
					String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
							+ "("+etId.longValue()+",'资格证书到期提醒','{\"users\":\"" + users + "\",\"content\":\""+ "有资格证书:"+ perLists.get(0).get("name") + "("+ formatter.format((Date)perLists.get(0).get("endtime"))+ ")即将到期" + "\"}','E','0')";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}else {
					//组织证书
					//系统管理员组
					List<Long> wxrbList = eventService.getSuperSession().createSQLQuery("SELECT ug.USER_ID FROM t_group_user as ug,t_groups as g where g.id=ug.GROUP_ID and g.CODE='GROUP_0001'").list();
					String gsql = "select convert(varchar(255),q.NAME ) as name ,q.ENDTIME as endtime ,q.dept_id as dept from t_perQualification q  where  q.Id = "+ids.get(i);
					List<Map> groupLists =(List<Map>) eventService.getSuperSession().createSQLQuery(gsql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
					BigDecimal deptUser =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT u.ID FROM t_department d,t_users u where d.LEADER = u.NAME and d.TEL = u.tel_number and d.id = "+groupLists.get(0).get("dept")).uniqueResult();
					String users = wxrbList.toString().replaceAll("\\[|\\]", "");
					users += deptUser==null?"":", "+deptUser.longValue();
					String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
							+ "("+etId.longValue()+",'资格证书到期提醒','{\"users\":\"" + users + "\",\"content\":\""+ "有资格证书:"+ groupLists.get(0).get("name") + "("+ formatter.format((Date)groupLists.get(0).get("endtime"))+ ")即将到期" + "\"}','E','0')";
					eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				}
			}
		}
	}
}
