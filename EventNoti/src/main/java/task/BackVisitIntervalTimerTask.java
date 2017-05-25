package main.java.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import main.pojo.BackVisit;
import main.pojo.CustomerInfo;
import main.service.BackVisitService;
import main.service.CustomerInfoService;
import main.service.EventService;
import main.util.DateAlert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.syntax.jedit.InputHandler.overwrite;

public class BackVisitIntervalTimerTask extends TimerTask {
	public static EventService eventService;// ���ݿ�����ӿ�
	public static BackVisitService backVisitService;// �طü�¼
	public static CustomerInfoService customerInfoService;
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	// �ֱ�Ϊ1�ǡ�2�ǡ�3�ǡ�4�ǡ�5���û�������Ӧ�������������жȡ��߶ȡ�����״̬
	private final static int day[][] = {{ 35, 42, 49, 56 },{ 28, 35, 42, 49 },{ 21, 28, 35, 42 },{ 14, 21, 28, 35 },{ 7, 14, 21, 28 }};
	private final static int states[] = { 452, 453, 454, 455, 456 };// �������������жȡ��߶ȡ�����״̬

	@Override
	public void run() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		eventService = (EventService) context.getBean("eventService");
		backVisitService = (BackVisitService) context.getBean("backVisitService");
		customerInfoService = (CustomerInfoService) context.getBean("customerInfoService");
		Long maxId = customerInfoService.getMaxId();
		System.out.println("���id��" + maxId);
		for (Long i = 1L; i <= maxId; i++) {
			System.out.println("�ܴ�����" + i);
			CustomerInfo customerInfo = null;
			try {
				customerInfo = customerInfoService.getById(i);
			} catch (Exception e) {
				System.out.println("IDΪ" + i + "�Ŀͻ������ڣ�");
				continue;
			}
			List<BackVisit> bvList = backVisitService.getBackVisitByCustomerName(customerInfo.getCustomerName());
			if (bvList.size() > 0) {
				Date date = bvList.get(0).getVisitDate();
				for (int j = 1; j < bvList.size(); j++) {
					date = (DateAlert.betweenDays(date, bvList.get(j).getVisitDate()) > 0) ? date : bvList.get(j).getVisitDate();
				}
				Date now = new Date();
				try {
					now = formatter.parse(formatter.format(now));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Long days = DateAlert.betweenDays(now, date);
				customerInfo.setUnconnect(days);
				// 339һ�ǡ�340���ǡ�341���ǡ�342���ǡ�343����
				customerInfo.setState(getState(customerInfo.getStep().intValue(), days.intValue()));
				customerInfo.setBackVisitSum((long)bvList.size());
				customerInfoService.updateCustomerInfo(customerInfo);
			} else {
				customerInfo.setUnconnect(1000000L);
				customerInfoService.updateCustomerInfo(customerInfo);
			}

		}

	}

	public Long getState(int level, int days) {
		Long state = null;
		switch (level) {
		case 339:// һ�ǿͻ�
			state = judgeState(0, days);
			break;
		case 340:// ���ǿͻ�
			state = judgeState(1, days);
			break;
		case 341:// ���ǿͻ�
			state = judgeState(2, days);
			break;
		case 342:// ���ǿͻ�
			state = judgeState(3, days);
			break;
		case 343:// ���ǿͻ�
			state = judgeState(4, days);
			break;
		}
		return state;
	}

	public Long judgeState(int i, int days) {
		Long state = null;
		if (days <= day[i][0]) {
			state = (long) states[0];
		}
		if (day[i][0] < days && days <= day[i][1]) {
			state = (long) states[1];
		}
		if (day[i][1] < days && days <= day[i][2]) {
			state = (long) states[2];
		}
		if (day[i][2] < days && days <= day[i][3]) {
			state = (long) states[3];
		}
		if (day[i][3] < days) {
			state = (long) states[4];
		}
		return state;
	}

}
