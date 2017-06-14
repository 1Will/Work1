package main.java.task;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import main.service.EventService;

public class SpaceCheckerTimerTask extends BaseTimerTask {
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
				//���뵽crm��δ����Ϣ
				String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
						+ "values (0,'###','"+ file.getPath()+ "Ӳ������"+ df.format(usable)+ "%','"+ formatter.format(new Date()) + "',0,##,null)";
				//���뵽EventNew���ݿ⣬�ȴ�΢��ɨ��
				String wxsql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG) VALUES "
						+ "(5,'###','{\"users\":\"" + list.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""+ file.getPath() + "Ӳ������" + df.format(usable) + "%\"}','E')";
				if (usable >= 80 && usable < 90) {
					String title = "���̳�������";
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							eventService.getSuperSession()
									.createSQLQuery(sql.replace("###", title).replace("##", list.get(i) + ""))
									.executeUpdate();
						}
					}
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
				if (usable >= 90 && usable < 95) {
					String title = "���̸߶ȱ���";
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							eventService.getSuperSession()
									.createSQLQuery(sql.replace("###", title).replace("##", list.get(i) + ""))
									.executeUpdate();
						}
					}
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
				if (usable >= 95) {
					String title = "�������ر���";
					if (list != null) {
						for (int i = 0; i < list.size(); i++) {
							eventService.getSuperSession()
									.createSQLQuery(sql.replace("###", title).replace("##", list.get(i) + ""))
									.executeUpdate();
						}
					}
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
