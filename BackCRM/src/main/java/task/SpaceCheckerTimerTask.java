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
				String wxsql = "insert into dbo.EventNew (EVENTTYPE_ID,NAME,MOREINFO,EFFECTFLAG,DEALFLAG) VALUES "
						+ "(5,'###','{\"users\":\"" + list.toString().replaceAll("\\[|\\]", "") + "\",\"content\":\""+ file.getPath() + "硬盘已用" + df.format(usable) + "%\"}','E','0')";
				if (usable >= 80 && usable < 90) {
					String title = "磁盘初步报警";
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
				if (usable >= 90 && usable < 95) {
					String title = "磁盘高度报警";
					eventService.getSuperSession().createSQLQuery(wxsql.replace("###", title)).executeUpdate();
				}
				if (usable >= 95) {
					String title = "磁盘严重报警";
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
