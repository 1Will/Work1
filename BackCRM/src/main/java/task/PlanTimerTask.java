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
				logger.info(formatter2.format(new Date()) + "��ʼ������¹����ƻ�����!");
				eventService = (EventService) context.getBean("eventService");
				codeValueService=(CodeValueService) context.getBean("codeValueService");
				CodeValue codeZh = codeValueService.getCodeValueByCode("21103");
				CodeValue codeDzx = codeValueService.getCodeValueByCode("21101");
				CodeValue codeZxz = codeValueService.getCodeValueByCode("21102");
				EventType eventType = eventService.getEventTypeByCode("10027");//��ȡ����ƻ��ͺ���  �¼�����
				//�����Ŀ�ܼƻ�����ͬ�Ĺ����ƻ����������Ԥ�����ʱ��δ��ɣ���ϵͳ�Զ�����Ϊ�ͺ�״̬��
				logger.info(formatter2.format(new Date()) + "��ʼ����Ԥ��ʱ��δ��ɵ�����");
				//�ͺ�һֱ����
				String sqlProPlan  = "select  pp.id  pid ,convert(varchar(255),pf.code ) as pcode ,convert(varchar(255),pp.name ) as pname FROM  t_projectInfoPlan  as pp left join  t_personnelFiles  as pf ON  pp.personnelFiles_id = pf.Id WHERE pp.end_Date < getdate() AND planState IN ("+codeDzx.getId()+","+codeZxz.getId()+","+codeZh.getId()+") ";
				 List<Map> listsObjects =(List<Map>) eventService.getSuperSession().createSQLQuery(sqlProPlan).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				if(listsObjects!=null&&listsObjects.size()>0){
					for(int i=0;i<listsObjects.size();i++){
						int indx = i+1;
						HashMap mapIndex = (HashMap) listsObjects.get(i);
						EventNew event = new EventNew();
						event.setEffectflag("E");
						event.setEventType(eventType);
						event.setName("����ƻ��ͺ���");
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
						map.put("name", formatter.format(new Date())+","+mapIndex.get("pname")+"δ��Ԥ�����ʱ����ɣ��Ѿ��ͺ�");
						map.put("url", "projectInfo/editProPlan.html?projectInfoPlan.id="+mapIndex.get("pid"));
						String moreinfo = JSONObject.fromObject(map).toString();
						event.setMoreinfo(moreinfo);
						logger.info(formatter2.format(new Date()) + "����һ���¼�������ƻ�Ϊ��"+mapIndex.get("pname")+" ,�Ѿ��ͺ� ");
						eventService.saveEvent(event);
						logger.info(formatter2.format(new Date()) + "��������ƻ�Ϊ��"+mapIndex.get("pname")+" ��״̬Ϊ�ͺ� ");
						String sql ="UPDATE t_projectInfoPlan  SET planState = "+codeZh.getId()+"  WHERE  id =  "+mapIndex.get("pid");
						eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
						logger.info(formatter2.format(new Date()) + "��ǰ�Ѿ������� "+indx+"������(��"+listsObjects.size()+")");
					}
				}
				String sql ="UPDATE t_projectInfoPlan  SET planState = "+codeZh.getId()+"  WHERE end_Date <  getdate() AND planState IN ("+codeDzx.getId()+","+codeZxz.getId()+") ";
				
				int updateNum =eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
				logger.info(formatter2.format(new Date())+"��������"+listsObjects.size()+"������");

				logger.info(formatter2.format(new Date()) + "���¹����ƻ����������!");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("�������ݱ����쳣 !");
			}

	}
}
