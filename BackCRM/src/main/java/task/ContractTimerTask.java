package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.ContractManagement;
import main.service.EventService;
import main.util.DateAlert;

public class ContractTimerTask extends BaseTimerTask {
	public ContractTimerTask(ApplicationContext context) {
		super(context);
	}
	private final static int day = 1;// ��ǰ1��֪ͨ

	public void run() {
		try {
			logger.info(formatter2.format(new Date())+": ��ʼִ�к�ͬ����ɨ�����ѣ�");
			eventService = (EventService) context.getBean("eventService");
			// List<EventNew> events = eventService.getEvent();
			// ������д��Ҫִ�е�����
			List<ContractManagement> cmList = DateAlert.getObjByYearDay(new ContractManagement(), day, "endTime");
			for (int i = 0; i < cmList.size(); i++) {
				// ��ȡ������id
				logger.info("�� "+i+" ����ͬ���ѿ�ʼ��ӣ�");
				BigDecimal creatorId = (BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.t_users where CODE ='" + cmList.get(i).getSaleman().getCode() + "'").uniqueResult();
				// ��dbo.EventNew�в�������
				BigDecimal etId =(BigDecimal) eventService.getSuperSession().createSQLQuery("select id from dbo.EventType where code ='10022'").uniqueResult();
				String sql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
						+ "("+etId.longValue()+",'��ͬ��������','{\"users\":\"" + creatorId.intValue() + "\",\"content\":\""+ "�к�ͬ:"+ cmList.get(i).getContractName()+ "("+ formatter.format(cmList.get(i).getEndTime())+ ")��������" + "\"}','E','0')";

				
				eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
			}
			logger.info(formatter2.format(new Date())+"��ͬ������ʾִ�н���!!!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("��ͬ������Ϣ�����쳣!!!");
		}
	}
}
