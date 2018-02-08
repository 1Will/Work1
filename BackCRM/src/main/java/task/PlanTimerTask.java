package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.pojo.CodeValue;
import main.pojo.EventNew;
import main.pojo.EventType;
import main.service.CodeValueService;
import main.service.EventService;
import net.sf.json.JSONObject;

import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;

public class PlanTimerTask extends BaseTimerTask {
	public  CodeValueService codeValueService;
	public PlanTimerTask(ApplicationContext context) {
		super(context);
	}

	@SuppressWarnings({"unchecked","rawtypes","unused"})
	public void run() {
			try {
				logger.info(formatter2.format(new Date()) + "开始进入更新工作计划程序!");
				eventService = (EventService) context.getBean("eventService");
				codeValueService=(CodeValueService) context.getBean("codeValueService");
				CodeValue codeZh = codeValueService.getCodeValueByCode("21103");
				CodeValue codeDzx = codeValueService.getCodeValueByCode("21101");
				CodeValue codeZxz = codeValueService.getCodeValueByCode("21102");
				EventType eventType = eventService.getEventTypeByCode("10027");//获取任务计划滞后反馈  事件类型
				//检查项目总计划及合同的工作计划，如果到了预计完成时间未完成，则系统自动更新为滞后状态。
				logger.info(formatter2.format(new Date()) + "开始检索预计时间未完成的数据");
				//滞后一直提醒
				String sqlProPlan  = "select  pp.id  pid ,convert(varchar(255),pf.code ) as pcode ,convert(varchar(255),pp.name ) as pname FROM  t_projectInfoPlan  as pp left join  t_personnelFiles  as pf ON  pp.personnelFiles_id = pf.Id WHERE pp.end_Date < getdate() AND planState IN ("+codeDzx.getId()+","+codeZxz.getId()+","+codeZh.getId()+") ";
				 List<Map> listsObjects =(List<Map>) eventService.getSuperSession().createSQLQuery(sqlProPlan).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				if(listsObjects!=null&&listsObjects.size()>0){
					for(int i=0;i<listsObjects.size();i++){
						int indx = i+1;
						HashMap mapIndex = (HashMap) listsObjects.get(i);
						EventNew event = new EventNew();
						event.setEffectflag("E");
						event.setEventType(eventType);
						event.setName("任务计划滞后反馈");
						String codeSQL = "select u.ID from dbo.t_users as u where u.code='"+mapIndex.get("pcode")+"'";
						List oneList = eventService.getSuperSession().createSQLQuery(codeSQL).list();
						BigDecimal userId=null;
						if(oneList!=null&&oneList.size()>0){
							userId =(BigDecimal)oneList.get(0);
						}
						event.setUserId(userId.longValue() + "");
						Map<String, String> map = new HashMap();
						
						map.put("users", userId+"");
						map.put("projectInfoPlanId", mapIndex.get("pid")+"");
						map.put("name", formatter.format(new Date())+","+mapIndex.get("pname")+"未在预计完成时间完成，已经滞后");
						map.put("url", "projectInfo/editProPlan.html?projectInfoPlan.id="+mapIndex.get("pid"));
						String moreinfo = JSONObject.fromObject(map).toString();
						event.setMoreinfo(moreinfo);
						logger.info(formatter2.format(new Date()) + "增加一条事件，任务计划为："+mapIndex.get("pname")+" ,已经滞后 ");
						eventService.saveEvent(event);
						logger.info(formatter2.format(new Date()) + "更新任务计划为："+mapIndex.get("pname")+" 的状态为滞后 ");
						String sql ="UPDATE t_projectInfoPlan  SET planState = "+codeZh.getId()+"  WHERE  id =  "+mapIndex.get("pid");
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
						logger.info(formatter2.format(new Date()) + "当前已经处理了 "+indx+"条数据(共"+listsObjects.size()+")");
					}
				}
				String sql ="UPDATE t_projectInfoPlan  SET planState = "+codeZh.getId()+"  WHERE end_Date <  getdate() AND planState IN ("+codeDzx.getId()+","+codeZxz.getId()+") ";
				
				int updateNum =eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				logger.info(formatter2.format(new Date())+"共更新了"+listsObjects.size()+"条数据");

				logger.info(formatter2.format(new Date()) + "更新工作计划程序处理结束!");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("插入数据表发生异常 !");
			}

	}
}
