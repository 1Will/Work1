package main.java.task;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.service.EventService;

public class SpaceCheckerTimerTask extends BaseTimerTask {
	public SpaceCheckerTimerTask(ApplicationContext context) {
		super(context);
	}

	public void run() {
		logger.info(formatter2.format(new Date()) + "��ʼ�����̿ռ䣡");
		eventService = (EventService) context.getBean("eventService");
		DecimalFormat df = new DecimalFormat("#.0");
		File[] roots = File.listRoots();
		try {
			String adminsql = "SELECT ug.USER_ID FROM t_group_user as ug,t_groups as g where g.id=ug.GROUP_ID and g.CODE='GROUP_0001'";
			@SuppressWarnings("unchecked")
			List<Long> list = eventService.getSuperSession().createSQLQuery(adminsql).list();
			for (File file : roots) {
				Long total = file.getTotalSpace();// ������
				Long free = file.getFreeSpace();// ��������
				Long used = total - free;// ʣ������
				double usable = (double) used * 100 / (double) total;// ���ðٷֱ�
				logger.info(file.getPath() + "Ӳ������" + df.format(usable) + "%");
				String wxsql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
						+ "(5,'###','{\"users\":\"" + list.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""+ file.getPath() + "Ӳ������" + df.format(usable) + "%\"}','E','0')";
				if (usable >= 80 && usable < 90) {
					String title = "���̳�������";
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
				if (usable >= 90 && usable < 95) {
					String title = "���̸߶ȱ���";
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
				if (usable >= 95) {
					String title = "�������ر���";
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("���̿ռ����д�");
		}
		logger.info(formatter2.format(new Date()) + "���̿ռ��������");
	}
}
