package main.java.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import main.pojo.CodeValue;
import main.service.CodeValueService;
import main.service.EventService;

import org.hibernate.transform.Transformers;
import org.springframework.context.ApplicationContext;

public class NewTaskTask extends BaseTimerTask {
	public  CodeValueService codeValueService;
	public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public NewTaskTask(ApplicationContext context) {
		super(context);
	}

	@SuppressWarnings({"unchecked","rawtypes"})
	public void run() {
		logger.info(formatter2.format(new Date()) + "开始扫描日程!");
		try {
			eventService = (EventService) context.getBean("eventService");
			codeValueService=(CodeValueService) context.getBean("codeValueService");
			CodeValue dzx = codeValueService.getCodeValueByCode("30601");//待执行
			CodeValue yzh = codeValueService.getCodeValueByCode("30602");//已滞后
			CodeValue ywc = codeValueService.getCodeValueByCode("30603");//已完成
			CodeValue yjs = codeValueService.getCodeValueByCode("30604");//已结束
			
			String date = format.format(new Date());
			//待执行，发通知部分
			String sql = "select convert(varchar(255),n.Id) as Id,n.name as name,n.startTime as startTime,n.endTime as endTime,convert(varchar(255),n.time) as time,convert(varchar(255),n.userDo) as userDo,convert(varchar(255),n.userCopy) as userCopy from t_newTask n where n.isSaved = '1' and n.state ="+dzx.getId();
			List<Map> taskLists =(List<Map>) eventService.getSuperSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			if(taskLists != null && taskLists.size() >0){
				BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10043'").uniqueResult();
				for (int i = 0; i < taskLists.size(); i++) {
					String time = (String) taskLists.get(i).get("time");
					if(time != null && !"".equals(time)){
						String[] temp = time.split(",");
						for (int j = 0; j < temp.length; j++) {
							Date d =new Date(((Date)taskLists.get(i).get("startTime")).getTime()-Long.parseLong(temp[j])*60000);
							if(date.equals(format.format(d))){
								String userDo = (String) taskLists.get(i).get("userDo");
								String userCopy = (String) taskLists.get(i).get("userCopy");
								String dosql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
										+ "("+etId.longValue()+",'日程提醒通知','{\"users\":\"" + userDo + "\",\"newTaskId\":\""+(String) taskLists.get(i).get("Id") + "\",\"content\":\""+ format.format((Date)taskLists.get(i).get("startTime")) + "，有日程(" + (String) taskLists.get(i).get("name") + ")待完成\"}','E','0')";
								String copysql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
										+ "("+etId.longValue()+",'日程抄送通知','{\"users\":\"" + userCopy + "\",\"newTaskId\":\""+(String) taskLists.get(i).get("Id")+"\",\"content\":\""+ format.format((Date)taskLists.get(i).get("startTime")) + "，有日程(" + (String) taskLists.get(i).get("name") + ")抄送通知\"}','E','0')";
								eventService.getSuperSession().createSQLQuery(dosql).executeUpdate();
								eventService.getSuperSession().createSQLQuery(copysql).executeUpdate();
							}
						}
					}
				}
			}
			//待执行
			String dzxSql = "update t_newTask set state = "+dzx.getId() +" where  isSaved = '1' and state = "+yzh.getId()+" and startTime >= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(dzxSql).executeUpdate();
			//已完成变为已结束
			String yjsSql = "update t_newTask set state = "+yjs.getId() +" where  isSaved = '1' and state = "+ywc.getId()+" and endTime <= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(yjsSql).executeUpdate();
			//待执行变为已滞后
			String yzhSql = "update t_newTask set state = "+yzh.getId() +" where  isSaved = '1' and state = "+dzx.getId()+" and endTime <= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(yzhSql).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(formatter2.format(new Date()) + "日程扫描出现错误!");
		}
		logger.info(formatter2.format(new Date()) + "日程扫描结束!");
	}
}
