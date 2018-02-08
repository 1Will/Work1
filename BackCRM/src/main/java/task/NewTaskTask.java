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
		logger.info(formatter2.format(new Date()) + "��ʼɨ���ճ�!");
		try {
			eventService = (EventService) context.getBean("eventService");
			codeValueService=(CodeValueService) context.getBean("codeValueService");
			CodeValue dzx = codeValueService.getCodeValueByCode("30601");//��ִ��
			CodeValue yzh = codeValueService.getCodeValueByCode("30602");//���ͺ�
			CodeValue ywc = codeValueService.getCodeValueByCode("30603");//�����
			CodeValue yjs = codeValueService.getCodeValueByCode("30604");//�ѽ���
			
			String date = format.format(new Date());
			//��ִ�У���֪ͨ����
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
										+ "("+etId.longValue()+",'�ճ�����֪ͨ','{\"users\":\"" + userDo + "\",\"newTaskId\":\""+(String) taskLists.get(i).get("Id") + "\",\"content\":\""+ format.format((Date)taskLists.get(i).get("startTime")) + "�����ճ�(" + (String) taskLists.get(i).get("name") + ")�����\"}','E','0')";
								String copysql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
										+ "("+etId.longValue()+",'�ճ̳���֪ͨ','{\"users\":\"" + userCopy + "\",\"newTaskId\":\""+(String) taskLists.get(i).get("Id")+"\",\"content\":\""+ format.format((Date)taskLists.get(i).get("startTime")) + "�����ճ�(" + (String) taskLists.get(i).get("name") + ")����֪ͨ\"}','E','0')";
								eventService.getSuperSession().createSQLQuery(dosql).executeUpdate();
								eventService.getSuperSession().createSQLQuery(copysql).executeUpdate();
							}
						}
					}
				}
			}
			//��ִ��
			String dzxSql = "update t_newTask set state = "+dzx.getId() +" where  isSaved = '1' and state = "+yzh.getId()+" and startTime >= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(dzxSql).executeUpdate();
			//����ɱ�Ϊ�ѽ���
			String yjsSql = "update t_newTask set state = "+yjs.getId() +" where  isSaved = '1' and state = "+ywc.getId()+" and endTime <= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(yjsSql).executeUpdate();
			//��ִ�б�Ϊ���ͺ�
			String yzhSql = "update t_newTask set state = "+yzh.getId() +" where  isSaved = '1' and state = "+dzx.getId()+" and endTime <= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(yzhSql).executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(formatter2.format(new Date()) + "�ճ�ɨ����ִ���!");
		}
		logger.info(formatter2.format(new Date()) + "�ճ�ɨ�����!");
	}
}
