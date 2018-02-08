package main.java.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import main.pojo.ProductionOperationDetail;
import main.service.EventService;

import org.springframework.context.ApplicationContext;

public class ProductionOperationTimerTask extends BaseTimerTask {

	public ProductionOperationTimerTask(ApplicationContext context) {
		super(context);
	}
	
	@SuppressWarnings("unchecked")
	public void run() {
		logger.info(formatter2.format(new Date())+": 开始生产经营扫描！");
		eventService = (EventService) context.getBean("eventService");
		List<ProductionOperationDetail> pos = eventService.getSuperSession().createQuery("from ProductionOperationDetail").list(); 
		if(pos != null && pos.size()>0){
			for (int i = 0; i < pos.size(); i++) {
				//未完成，异常
				BigDecimal state = null;
				Long notDo = (Long) eventService.getSuperSession().createQuery("select COUNT(pp.id) from ProjectInfoPlan pp where pp.percentt<100 and pp.endDate >"+formatter.format(new Date())+" and pp.productionOperationDetail.id = " +pos.get(i).getId()).uniqueResult();
				if(notDo>0){
					state =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='21602'").uniqueResult();
				}else {
					//总数量
					Long all = (Long) eventService.getSuperSession().createQuery("select COUNT(pp.id) from ProjectInfoPlan pp where pp.productionOperationDetail.id = " +pos.get(i).getId()).uniqueResult();
					//完成的
					Long finish = (Long) eventService.getSuperSession().createQuery("select COUNT(pp.id) from ProjectInfoPlan pp where pp.percentt=100 and pp.productionOperationDetail.id = " +pos.get(i).getId()).uniqueResult();
					if(all >0 && finish == all){
						state =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='21603'").uniqueResult();
					}else {
						state =(BigDecimal) eventService.getSuperSession().createSQLQuery("SELECT id FROM dbo.t_codevalue where code ='21601'").uniqueResult();
					}
				}
				eventService.getSuperSession().createSQLQuery("update t_ProductionOperationDetail set proStatu = "+state.longValue()+"where id = "+pos.get(i).getId()).executeUpdate();
			}
		}
		logger.info(formatter2.format(new Date())+": 生产经营扫描结束！");
	}
}
