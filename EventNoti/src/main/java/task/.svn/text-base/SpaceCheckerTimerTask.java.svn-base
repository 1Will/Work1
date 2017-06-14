package main.java.task;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import main.service.EventService;

public class SpaceCheckerTimerTask extends BaseTimerTask {
	public void run() {
		logger.info(formatter2.format(new Date()) + "开始检查磁盘空间！");
		eventService = (EventService) context.getBean("eventService");
		DecimalFormat df = new DecimalFormat("#.0");
		File[] roots = File.listRoots();
		try {
			String adminsql = "SELECT ug.USER_ID FROM t_group_user as ug,t_groups as g where g.id=ug.GROUP_ID and g.CODE='GROUP_0001'";
			@SuppressWarnings("unchecked")
			List<Long> list = eventService.getSuperSession().createSQLQuery(adminsql).list();
			for (File file : roots) {
				Long total = file.getTotalSpace();// 总容量
				Long free = file.getFreeSpace();// 空闲容量
				Long used = total - free;// 剩余容量
				double usable = (double) used * 100 / (double) total;// 已用百分比
				logger.info(file.getPath() + "硬盘已用" + df.format(usable) + "%");
				//插入到crm的未读消息
				String sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID) "
						+ "values (0,'###','"+ file.getPath()+ "硬盘已用"+ df.format(usable)+ "%','"+ formatter.format(new Date()) + "',0,##,null)";
				//插入到EventNew数据库，等待微信扫描
				String wxsql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG) VALUES "
						+ "(5,'###','{\"users\":\"" + list.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""+ file.getPath() + "硬盘已用" + df.format(usable) + "%\"}','E')";
				if (usable >= 80 && usable < 90) {
					String title = "磁盘初步报警";
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
					String title = "磁盘高度报警";
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
					String title = "磁盘严重报警";
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
			logger.info("磁盘空间检查有错！");
		}
		logger.info(formatter2.format(new Date()) + "磁盘空间检查结束！");
	}
}
