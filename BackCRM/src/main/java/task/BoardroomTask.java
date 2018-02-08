package main.java.task;

import java.util.Date;
import java.util.List;

import main.pojo.CodeValue;
import main.service.CodeValueService;
import main.service.EventService;

import org.springframework.context.ApplicationContext;

public class BoardroomTask extends BaseTimerTask {
	public  CodeValueService codeValueService;
	public BoardroomTask(ApplicationContext context) {
		super(context);
	}

	@SuppressWarnings("unchecked")
	public void run() {
		logger.info(formatter2.format(new Date()) + "开始扫描更改会议室状态!");
		try {
			eventService = (EventService) context.getBean("eventService");
			codeValueService=(CodeValueService) context.getBean("codeValueService");
			//会议室预订
			CodeValue dsy = codeValueService.getCodeValueByCode("30201");//待使用
			CodeValue zzsy = codeValueService.getCodeValueByCode("30202");//正在使用
			CodeValue ysy = codeValueService.getCodeValueByCode("30203");//已使用
			CodeValue yqx = codeValueService.getCodeValueByCode("30204");//已取消
			String date = formatter2.format(new Date());
			String dsySql = "update t_bookBoardroom set state = "+dsy.getId() +" where isSaved = '1' and state <> "+yqx.getId()+" and startDate > '"+ date +"'";
			String zzsySql = "update t_bookBoardroom set state = "+zzsy.getId() +" where  isSaved = '1' and state <> "+yqx.getId()+" and startDate <= '"+ date +"' and endDate > '"+date+"'";
			String ysySql = "update t_bookBoardroom set state = "+ysy.getId() +" where  isSaved = '1' and state <> "+yqx.getId()+" and endDate <= '"+date+"'";
			eventService.getSuperSession().createSQLQuery(dsySql).executeUpdate();
			eventService.getSuperSession().createSQLQuery(zzsySql).executeUpdate();
			eventService.getSuperSession().createSQLQuery(ysySql).executeUpdate();
			//会议室
			CodeValue kx = codeValueService.getCodeValueByCode("30101");//空闲
			CodeValue zy = codeValueService.getCodeValueByCode("30102");//在用
			List<Long> bookList = eventService.getSuperSession().createSQLQuery("select boardroom from t_bookBoardroom where  isSaved = '1' and  state ="+zzsy.getId()).list();
			String kxSql = "update t_boardroom set state = "+kx.getId();
			String zySql = "update t_boardroom set state = "+zy.getId() +" where id = ";
			if(bookList != null && bookList.size()>0){
				eventService.getSuperSession().createSQLQuery(kxSql).executeUpdate();
				for (int i = 0; i < bookList.size(); i++) {
					eventService.getSuperSession().createSQLQuery(zySql+bookList.get(i)).executeUpdate();
				}
			}else {
				eventService.getSuperSession().createSQLQuery(kxSql).executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(formatter2.format(new Date()) + "更改会议室状态结束!");
	}
}
