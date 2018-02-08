package main.java.task;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import main.pojo.PersonnelFiles;
import main.pojo.UsersInfo;
import main.service.ContactArchivesService;
import main.service.EventService;
import main.service.PersonnelFilesService;

public class DataTimerTask extends BaseTimerTask {
	public DataTimerTask(ApplicationContext context) {
		super(context);
	}

	public static ContactArchivesService contactArchivesService;// 联系人数据库操作接口

	@SuppressWarnings("unchecked")
	public void run() {
		Calendar ca = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		ca.add(Calendar.DATE, 1);
		if (ca.get(Calendar.MONTH) != now.get(Calendar.MONTH)) {
			String year = ca.get(Calendar.YEAR) + "年";
			String month = ca.get(Calendar.YEAR) + "年" + String.format("%02d", ca.get(Calendar.MONTH) + 1) + "月";
//			logger.info(year);
//			logger.info(month);
			try {
				logger.info(formatter2.format(new Date()) + "开始插入数据表!");
				eventService = (EventService) context.getBean("eventService");
				PersonnelFilesService pFService = (PersonnelFilesService) context.getBean("personnelFilesService");
				List<UsersInfo> userList = eventService.getSuperSession()
						.createQuery("from UsersInfo u where u.enabled =1 and u.code <> ''").list();
				for (int i = 0; i < userList.size(); i++) {
					List<PersonnelFiles> pfs = pFService.findPersonnelFilesByCode(userList.get(i).getCode());
					if (pfs != null && pfs.size() >= 0) {
						BigDecimal id = (BigDecimal) eventService
								.getSuperSession()
								.createSQLQuery(
										"select id from dbo.t_data where month ='" + month
												+ "' and personnelFiles_id =" + pfs.get(0).getId()).uniqueResult();
						if ("".equals(id) || id == null) {
							String sql = "insert into dbo.t_data (VERSION,year,month,contractManagementNum,contractManagementMoney,financialManagementNum,financialManagementMoney,billingRecordNum,billingRecordMoney,ShouldDaily,ActualDaily,ShouldWeekly,ActualWeekly,Monthly,projectNum,backvisitNum,personnelFiles_id) values (0,'"
									+ year + "','" + month + "',0,0,0,0,0,0,0,0,0,0,0,0,0," + pfs.get(0).getId() + ")";
							eventService.getSuperSession().createSQLQuery(sql).executeUpdate();
						}
					}
				}

				logger.info(formatter2.format(new Date()) + "插入数据表结束!");
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("插入数据表发生异常 !");
			}
		}else {
			logger.info("数据表插入时间未到，停止执行 !");
		}

	}
}
