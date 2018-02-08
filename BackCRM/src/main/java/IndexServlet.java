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
	public EventService eventService;// 数据库操作接口
	public Timer timer;// 定时器
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
		logger.info("开始执行IndexServlet的init方法");
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
					logger.info("==================EventNew============执行次数为：" + a);
				}
			}
		}.start();
	}

	// 定时任务执行方法
	public void autoWorkOff() {
		logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "开始执行事件检查");
		session = eventService.getSuperSession();
		insertWarnning = new InsertWarnning(session);
		List<EventNew> events = eventService.getEvent();
		List<Long> eIds = new ArrayList<Long>();
		for (int i = 0; i < events.size(); i++) {
			eIds.add(events.get(i).getId());
		}
		if (eIds.size() > 0) {
			logger.info("执行dealing,eids=" + eIds.toString());
			eventService.dealing(eIds);
		}
		try {

			session.beginTransaction();
			for (EventNew event : events) {
				logger.info("事件名称是：" + event.getName());
				String type = event.getEventType().getCode();
		
				// 回访通知
				if (type.equals("10001")) {
					insertWarnning.insert(event);
					updateData.updateBackvisitData(event);
					handBase.publishNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加日报扫描处理
				if (type.equals("10002")) {
					insertWarnning.insert(event);
					updateData.updateDailyData(event); // 处理事件时，同步更新日报数据 0811
					handBase.publishDailyNotification(event);
					logger.info("处理完一条事件");
				}
				// 项目提交扫描处理
				if (type.equals("10003")) {
					insertWarnning.insert(event);
					updateData.updateProjectInfoData(event);
					changStateService.setStateByProject(event);
					handBase.publishProjectNotification(event);
					logger.info("处理完一条事件");
				}
				// 磁盘检查通知
				if (type.equals("10004")) {
					insertWarnning.insert(event);
					handBase.spaceCheckerNotification(event);
					logger.info("处理完一条事件");
				}
				// 日报检查通知
				if (type.equals("10005")) {
					insertWarnning.insert(event);
					handBase.dailyCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 合同事件 通知
				if (type.equals("10006")) {
					insertWarnning.insert(event);
					updateData.updateContractManagementData(event);
					changStateService.setStateByContract(event);
					handBase.publishContractNotification(event);
					logger.info("处理完一条事件");
				}
				// 收款事件 通知
				if (type.equals("10007")) {
					insertWarnning.insert(event);
					updateData.updateFinancialManagementData(event);
					changStateService.setStateByFinancial(event);
					handBase.publishFinancialNotification(event);
					logger.info("处理完一条事件");
				}

				// 新增客户 通知     发布天鹅版本事，事件停掉
				if (type.equals("10008")) {
					insertWarnning.insert(event);
					handBase.publishCustomerInfoNotification(event);
					logger.info("处理完一条事件");
				}
				// 新增联系人 通知      发布天鹅版本事，事件停掉
				if (type.equals("10009")) {
					insertWarnning.insert(event);
					handBase.publishContactArchivesNotification(event);
					logger.info("处理完一条事件");
				}
				// 新增产品 通知
				if (type.equals("10010")) {
					insertWarnning.insert(event);
					handBase.publishProductsNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加周计划 通知
				if (type.equals("10011")) {
					insertWarnning.insert(event);
					updateData.updateWeeklyData(event); // 同步更新周计划数据
					handBase.publishWeekPlanNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加工作计划 通知 项目工作计划 合同工作计划
				if (type.equals("10012")) {
					insertWarnning.insert(event);
					handBase.publishProjectInfoPlanNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加开票事件 通知
				if (type.equals("10013")) {
					insertWarnning.insert(event);
					updateData.updateBillingRecordData(event);
					handBase.publishBillingRecordNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加付款事件 通知
				if (type.equals("10014")) {
					insertWarnning.insert(event);
					handBase.publishPaymentOrderNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加报销单提交事件 通知
				if (type.equals("10015")) {
					insertWarnning.insert(event);
					handBase.publishExpenseFormNotification(event);
					logger.info("处理完一条事件");
				}

				// 同时处理多个模板消息
				// 添加 生日提醒 事件 通知
				if (type.equals("10016")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 拜访提醒 事件 通知
				if (type.equals("10017")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 项目计划开始提醒 事件 通知
				if (type.equals("10018")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 项目计划结束提醒 事件 通知
				if (type.equals("10019")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 合同计划开始提醒 事件 通知
				if (type.equals("10020")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 合同计划结束提醒事件 通知
				if (type.equals("10021")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 合同到期提醒 事件 通知
				if (type.equals("10022")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加工作计划状态更改 通知 项目工作计划 合同工作计划
				if (type.equals("10023")) {
					insertWarnning.insert(event);
					handBase.publishGGZTProjectInfoPlanNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 项目周计划提交 事件 通知
				if (type.equals("10024")) {
					insertWarnning.insert(event);
					handBase.publishProjectWeekPlanNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 加班单提交 事件 通知
				if (type.equals("10025")) {
					insertWarnning.insert(event);
					handBase.publishOverTimeBillNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 公出单提交 事件 通知
				if (type.equals("10026")) {
					insertWarnning.insert(event);
					handBase.publishOnTheRoadBillNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加 任务计划滞后反馈 事件 通知
				if (type.equals("10027")) {
					insertWarnning.insert(event);
					handBase.systemWorkPlanLagNotification(event);
					logger.info("处理完一条事件");
				}

				// 测试 添加 新回复评价 日报、客户、周计划、回访回复        事件 通知
				if (type.equals("10028")) {
					insertWarnning.insert(event);
					handBase.publishReplyNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 早会提醒 事件 通知
				if (type.equals("10029")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// Timer检查
				if (type.equals("10030")) {
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}

				// 添加 请假提交提醒 事件 通知
				if (type.equals("10031")) {
					insertWarnning.insert(event);
					handBase.publishQingjiaSubmitNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加 请假审批结果提醒 事件 通知
				if (type.equals("10032")) {
					insertWarnning.insert(event);
					handBase.publishQingjiaResultNotification(event);
					logger.info("处理完一条事件");
				}
				// 添加 待审批通知 事件通知
				if (type.equals("10033")) {
					insertWarnning.insert(event);
					handBase.publishRunTaskPendingNotification(event);
					logger.info("处理完一条事件");
				}
				// 审批拒绝通知 
				if (type.equals("10034")) {
					insertWarnning.insert(event);
					// 调用审批结果通知
					handBase.publishRunTaskResultNotification(event);
					logger.info("处理完一条事件");
				}
				
				if (type.equals("10035")) {
					insertWarnning.insert(event);
					// 调用审批结果通知
					handBase.publishRunTaskResultNotification(event);
					logger.info("处理完一条事件");
				}
				
				
				// 添加 项目经营计划开始提醒 事件 通知
				if (type.equals("10036")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				
				// 添加 项目经营计划结束提醒事件 通知
				if (type.equals("10037")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				
				// 资格证书到期提醒
				if (type.equals("10038")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				// 2018-01-23
				// 节点异常提醒事件
				if (type.equals("10039")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				// 节点异常开始处理提醒事件
				if (type.equals("10040")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				// 节点异常解决提醒事件
				if (type.equals("10041")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				// 外出考勤提醒
				if (type.equals("10042")) {
					insertWarnning.insert(event);
					handBase.systemRemindCheckNotification(event);
					logger.info("处理完一条事件");
				}
				
				
				
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			if (eIds.size() > 0) {
				logger.info("执行flush,eids=" + eIds.toString());
				eventService.flushing(eIds);
			}
			session.close();
		}
	}
}