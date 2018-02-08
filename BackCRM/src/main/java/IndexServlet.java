package main.java;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import main.pojo.EventNew;
import main.service.ChangStateService;
import main.service.EventService;
import main.service.MessageService;
import main.service.ProjectInfoService;
import main.util.HandlerBase;
import main.util.InsertWarnning;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(IndexServlet.class);
	public EventService eventService;// ���ݿ�����ӿ�
	public Timer timer;// ��ʱ��
	public Session session;
	public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	public ProjectInfoService projectInfoService;
	public MessageService messageService;
	public ChangStateService changStateService;
	public ServletContext application;
	public HandlerBase handBase;
	public UpdateData updateData;
	public int a = 0;

	public InsertWarnning insertWarnning;

	public IndexServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void init(ServletConfig config) throws ServletException {
		logger.info("��ʼִ��IndexServlet��init����");
		eventService = (EventService) context.getBean("eventService");
		messageService = (MessageService) context.getBean("messageService");
		projectInfoService = (ProjectInfoService) context.getBean("projectInfoService");
		changStateService = (ChangStateService) context.getBean("changStateService");
		application = config.getServletContext();
		updateData = new UpdateData(context);
		handBase = new HandlerBase(eventService, messageService);
		timerTask();
	}

	public void timerTask() {
		new Thread() {
			public void run() {
				while (true) {
					autoWorkOff();
					a++;
					try {
						Thread.sleep(23000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					logger.info("==================EventNew============ִ�д���Ϊ��" + a);
				}
			}
		}.start();
	}

	// ��ʱ����ִ�з���
	public void autoWorkOff() {
		logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "��ʼִ���¼����");
		session = eventService.getSuperSession();
		insertWarnning = new InsertWarnning(session);
		List<EventNew> events = eventService.getEvent();
		List<Long> eIds = new ArrayList<Long>();
		for (int i = 0; i < events.size(); i++) {
			eIds.add(events.get(i).getId());
		}
		if (eIds.size() > 0) {
			logger.info("ִ��dealing,eids=" + eIds.toString());
			eventService.dealing(eIds);
		}
		try {

			session.beginTransaction();
			for (EventNew event : events) {
				logger.info("�¼������ǣ�" + event.getName());
				String type = event.getEventType().getCode();
		
				// �ط�֪ͨ
				if (type.equals("10001")) {
					insertWarnning.insert(event);
					updateData.updateBackvisitData(event);
					handBase.publishNotification(event);
					logger.info("������һ���¼�");
				}
				// ����ձ�ɨ�账��
				if (type.equals("10002")) {
					insertWarnning.insert(event);
					updateData.updateDailyData(event); // �����¼�ʱ��ͬ�������ձ����� 0811
					handBase.publishDailyNotification(event);
					logger.info("������һ���¼�");
				}
				// ��Ŀ�ύɨ�账��
				if (type.equals("10003")) {
					insertWarnning.insert(event);
					updateData.updateProjectInfoData(event);
					changStateService.setStateByProject(event);
					handBase.publishProjectNotification(event);
					logger.info("������һ���¼�");
				}
				// ���̼��֪ͨ
				if (type.equals("10004")) {
					insertWarnning.insert(event);
					handBase.spaceCheckerNotification(event);
					logger.info("������һ���¼�");
				}
				// �ձ����֪ͨ
				if (type.equals("10005")) {
					insertWarnning.insert(event);
					handBase.dailyCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��ͬ�¼� ֪ͨ
				if (type.equals("10006")) {
					insertWarnning.insert(event);
					updateData.updateContractManagementData(event);
					changStateService.setStateByContract(event);
					handBase.publishContractNotification(event);
					logger.info("������һ���¼�");
				}
				// �տ��¼� ֪ͨ
				if (type.equals("10007")) {
					insertWarnning.insert(event);
					updateData.updateFinancialManagementData(event);
					changStateService.setStateByFinancial(event);
					handBase.publishFinancialNotification(event);
					logger.info("������һ���¼�");
				}

				// �����ͻ� ֪ͨ     �������汾�£��¼�ͣ��
				if (type.equals("10008")) {
					insertWarnning.insert(event);
					handBase.publishCustomerInfoNotification(event);
					logger.info("������һ���¼�");
				}
				// ������ϵ�� ֪ͨ      �������汾�£��¼�ͣ��
				if (type.equals("10009")) {
					insertWarnning.insert(event);
					handBase.publishContactArchivesNotification(event);
					logger.info("������һ���¼�");
				}
				// ������Ʒ ֪ͨ
				if (type.equals("10010")) {
					insertWarnning.insert(event);
					handBase.publishProductsNotification(event);
					logger.info("������һ���¼�");
				}
				// ����ܼƻ� ֪ͨ
				if (type.equals("10011")) {
					insertWarnning.insert(event);
					updateData.updateWeeklyData(event); // ͬ�������ܼƻ�����
					handBase.publishWeekPlanNotification(event);
					logger.info("������һ���¼�");
				}
				// ��ӹ����ƻ� ֪ͨ ��Ŀ�����ƻ� ��ͬ�����ƻ�
				if (type.equals("10012")) {
					insertWarnning.insert(event);
					handBase.publishProjectInfoPlanNotification(event);
					logger.info("������һ���¼�");
				}

				// ��ӿ�Ʊ�¼� ֪ͨ
				if (type.equals("10013")) {
					insertWarnning.insert(event);
					updateData.updateBillingRecordData(event);
					handBase.publishBillingRecordNotification(event);
					logger.info("������һ���¼�");
				}
				// ��Ӹ����¼� ֪ͨ
				if (type.equals("10014")) {
					insertWarnning.insert(event);
					handBase.publishPaymentOrderNotification(event);
					logger.info("������һ���¼�");
				}

				// ��ӱ������ύ�¼� ֪ͨ
				if (type.equals("10015")) {
					insertWarnning.insert(event);
					handBase.publishExpenseFormNotification(event);
					logger.info("������һ���¼�");
				}

				// ͬʱ������ģ����Ϣ
				// ��� �������� �¼� ֪ͨ
				if (type.equals("10016")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� �ݷ����� �¼� ֪ͨ
				if (type.equals("10017")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ��Ŀ�ƻ���ʼ���� �¼� ֪ͨ
				if (type.equals("10018")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ��Ŀ�ƻ��������� �¼� ֪ͨ
				if (type.equals("10019")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ��ͬ�ƻ���ʼ���� �¼� ֪ͨ
				if (type.equals("10020")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ��ͬ�ƻ����������¼� ֪ͨ
				if (type.equals("10021")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ��ͬ�������� �¼� ֪ͨ
				if (type.equals("10022")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��ӹ����ƻ�״̬���� ֪ͨ ��Ŀ�����ƻ� ��ͬ�����ƻ�
				if (type.equals("10023")) {
					insertWarnning.insert(event);
					handBase.publishGGZTProjectInfoPlanNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ��Ŀ�ܼƻ��ύ �¼� ֪ͨ
				if (type.equals("10024")) {
					insertWarnning.insert(event);
					handBase.publishProjectWeekPlanNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� �Ӱ൥�ύ �¼� ֪ͨ
				if (type.equals("10025")) {
					insertWarnning.insert(event);
					handBase.publishOverTimeBillNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� �������ύ �¼� ֪ͨ
				if (type.equals("10026")) {
					insertWarnning.insert(event);
					handBase.publishOnTheRoadBillNotification(event);
					logger.info("������һ���¼�");
				}
				// ��� ����ƻ��ͺ��� �¼� ֪ͨ
				if (type.equals("10027")) {
					insertWarnning.insert(event);
					handBase.systemWorkPlanLagNotification(event);
					logger.info("������һ���¼�");
				}

				// ���� ��� �»ظ����� �ձ����ͻ����ܼƻ����طûظ�        �¼� ֪ͨ
				if (type.equals("10028")) {
					insertWarnning.insert(event);
					handBase.publishReplyNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ������� �¼� ֪ͨ
				if (type.equals("10029")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// Timer���
				if (type.equals("10030")) {
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}

				// ��� ����ύ���� �¼� ֪ͨ
				if (type.equals("10031")) {
					insertWarnning.insert(event);
					handBase.publishQingjiaSubmitNotification(event);
					logger.info("������һ���¼�");
				}
				// ��� �������������� �¼� ֪ͨ
				if (type.equals("10032")) {
					insertWarnning.insert(event);
					handBase.publishQingjiaResultNotification(event);
					logger.info("������һ���¼�");
				}
				// ��� ������֪ͨ �¼�֪ͨ
				if (type.equals("10033")) {
					insertWarnning.insert(event);
					handBase.publishRunTaskPendingNotification(event);
					logger.info("������һ���¼�");
				}
				// �����ܾ�֪ͨ 
				if (type.equals("10034")) {
					insertWarnning.insert(event);
					// �����������֪ͨ
					handBase.publishRunTaskResultNotification(event);
					logger.info("������һ���¼�");
				}
				
				if (type.equals("10035")) {
					insertWarnning.insert(event);
					// �����������֪ͨ
					handBase.publishRunTaskResultNotification(event);
					logger.info("������һ���¼�");
				}
				
				
				// ��� ��Ŀ��Ӫ�ƻ���ʼ���� �¼� ֪ͨ
				if (type.equals("10036")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				
				// ��� ��Ŀ��Ӫ�ƻ����������¼� ֪ͨ
				if (type.equals("10037")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				
				// �ʸ�֤�鵽������
				if (type.equals("10038")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				// 2018-01-23
				// �ڵ��쳣�����¼�
				if (type.equals("10039")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				// �ڵ��쳣��ʼ���������¼�
				if (type.equals("10040")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				// �ڵ��쳣��������¼�
				if (type.equals("10041")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				// �����������
				if (type.equals("10042")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("������һ���¼�");
				}
				
				
				
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (eIds.size() > 0) {
				logger.info("ִ��flush,eids=" + eIds.toString());
				eventService.flushing(eIds);
			}
			session.close();
		}
	}
}