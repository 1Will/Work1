/*     */ package com.yongjun.tdms.service.activitiFlow.pojo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.dao.codevalue.CodeValueDao;
/*     */ 
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.activitiFlow.HistoryTaskinstDao;
import com.yongjun.tdms.dao.activitiFlow.RunPointDao;
import com.yongjun.tdms.dao.activitiFlow.RunTaskDao;
import com.yongjun.tdms.dao.workflow.point.PointDao;
import com.yongjun.tdms.model.activitiFlow.ActiviFlow;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.model.activitiFlow.RunTask;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.model.workflow.Point;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;

import net.sf.json.JSONObject;

/*     */ 
/*     */ public class DefaultActivitFlowManager extends BaseManager
/*     */   implements ActivitFlowManager
/*     */ {
	
	private RunTaskDao runTaskDao;
	private RunPointDao runPointDao;
	private HistoryTaskinstDao historyTaskinstDao;
	private PointDao pointDao;
	private CodeValueDao codeValueDao;
	private EventTypeManager eventTypeManager;
	private EventNewManager eventNewManager;
	private UserManager userManager;
	

			public DefaultActivitFlowManager(RunTaskDao runTaskDao, RunPointDao runPointDao,
			HistoryTaskinstDao historyTaskinstDao,PointDao pointDao,CodeValueDao codeValueDao,EventTypeManager eventTypeManager,EventNewManager eventNewManager
			,UserManager userManager) {
		this.runTaskDao = runTaskDao;
		this.runPointDao = runPointDao;
		this.historyTaskinstDao = historyTaskinstDao;
		this.pointDao=pointDao;
		this.codeValueDao=codeValueDao;
		this.eventTypeManager=eventTypeManager;
		this.eventNewManager=eventNewManager;
		this.userManager=userManager;
	}



			public void storeAtiviti(ActiviFlow activiFlow) throws DaoException {
				/**
				 * 第一步  根据运行任务id查询当前任务 
				 */
//			long 	runTaskId =Long.parseLong(map.get("runTaskId")+"");
				long runTaskId = activiFlow.getRunTaskId();
				
				RunTask runTask = this.runTaskDao.loadRunTask(runTaskId);
				// 2 查询审批节点 同时更新审批节点 状态，审核意见及审核时间
//				long 	runPointId =Long.parseLong(map.get("runPointId")+"");
				long 	runPointId=activiFlow.getRunPointId();
				RunPoint runPoint = this.runPointDao.loadRunPoint(runPointId);
				runPoint.setRemark(activiFlow.getRemark());
				runPoint.setResult(activiFlow.getResult());
				runPoint.setInspectTime(new Date());
				

				 //根据业务id 和审批人的code获取这个人的历史审批节点  
      			 // 然后 更新这个历史审批节点的完成时间和用时 （秒）
      			 String[] key={"bussnessId","assignee.code","myNum","flow.id"};
      			 String[] value={runTask.getBussnessId()+"",runTask.getAssignee().getCode(),runTask.getMyNum()+"",runTask.getFlow().getId()+""};
      			 List<HistoryTaskinst> historyTaskinsts = this.historyTaskinstDao.loadByKeyArray(key, value);
      			 if(historyTaskinsts!=null&&historyTaskinsts.size()>0){
      				 HistoryTaskinst LastHistoryTaskinst = historyTaskinsts.get(0);
      				 Date nowDate = new Date();
      				 long duration = nowDate.getTime()-LastHistoryTaskinst.getStartTime().getTime();
      				LastHistoryTaskinst.setEndTime(nowDate);
      				LastHistoryTaskinst.setDuration(duration/1000);
      			    this.historyTaskinstDao.storeHistoryTaskinst(LastHistoryTaskinst);
      			    
      			   runPoint.setDuration(duration/1000);
      			 }
      			 
      			 this.runPointDao.storeRunPoint(runPoint);
				//如果当前人审批结果为拒绝 则不往下执行 直接打回 该业务不通过
				if(activiFlow.getResult()!=null&&activiFlow.getResult().getCode().equals("21403")){
					CodeValue codeValue = null;//当前业务的审核状态
					List<CodeValue> bussnessCodeList =this.codeValueDao.loadByKey("code", "02002");//当前业务为不通过状态
		  			 if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
		  				codeValue =bussnessCodeList.get(0);
		  			
					
				    StartActiviti startActiviti = new StartActiviti();
				    		startActiviti.setBussnessId(runTask.getBussnessId());
				    		startActiviti.setBussnessType(runTask.getFlow().getFlowCode().getCode());
				    		startActiviti.setBussnessCode(codeValue);
				    		this.runTaskDao.storeBussnessState(startActiviti);
		  			 }
		  			 
		  			 
		  			 ///
		  			 //插入 拒绝事件
		  			storeFailActivitiEvent(runTask);
		  			 
		  			 ////
		  			 
		  			 
		  			 
		  			 
		  			 
					// 如果当前人审批结果为转交  完成审批 同时增加一条审批节点 并吧增加这条节点设为待审核状态
		  			 // 同时 在原来的基础上 当前预节点以后所有节点的顺序号myNum都加1
				}else if(activiFlow.getResult()!=null&&activiFlow.getResult().getCode().equals("21405")){
					
        			//拼接 查询删除的预节点以后的所有审批预节点
        			 String hql ="from RunPoint r where r.myNum >"+runPoint.getMyNum();
        			    List<RunPoint> nextRunPoints = this.runPointDao.loadAllByHql(hql);
        			    if(nextRunPoints!=null&&nextRunPoints.size()>0){
        			    for(RunPoint nextRunPoint : nextRunPoints){
        			    	//将查询结果遍历循环，同时将每个结果的顺序号减少1 保证顺序号是按照连续有效数字的
        			    	nextRunPoint.setMyNum(nextRunPoint.getMyNum()+1);//
        			    	  this.runPointDao.storeRunPoint(nextRunPoint);
        			    }
        			    }
        			    //j将当前任务转给接受人 增加一条预节点
    					RunPoint newRunPoint = new RunPoint();
    					newRunPoint.setBussnessId(runPoint.getBussnessId());
    					newRunPoint.setComments(runPoint.getComments());
    					newRunPoint.setCreatedTime(new Date());
    					newRunPoint.setDisabled(false);
    					newRunPoint.setFlow(runPoint.getFlow());
    					if(activiFlow.getTransfer()!=null){
    					newRunPoint.setInspectPser(activiFlow.getTransfer());
    					}
    					newRunPoint.setLinkHref(runPoint.getLinkHref());
    					newRunPoint.setName(runPoint.getName());
    					newRunPoint.setMyNum(runPoint.getMyNum()+1);
    					 List<CodeValue> list =this.codeValueDao.loadByKey("code", "21402");//预节点待审核状态
            			 if(list!=null&&list.size()>0){
            				 newRunPoint.setResult(list.get(0));
            			 }
            			 this.runPointDao.storeRunPoint(newRunPoint);
        			    RunTask nextRunTask = new RunTask();
    	       			nextRunTask.setBussnessId(runTask.getBussnessId());
    	       			nextRunTask.setName(runTask.getName());
    	       			nextRunTask.setFlow(runTask.getFlow());
    	       			nextRunTask.setSubmitPer(runTask.getSubmitPer());
    	       			nextRunTask.setSubmitTime(runTask.getSubmitTime());
    	       			nextRunTask.setCreateTime(new Date());
    	       			nextRunTask.setAssignee(newRunPoint.getInspectPser());
    	       			nextRunTask.setMyNum(newRunPoint.getMyNum());
    	       			nextRunTask.setContent(runTask.getContent());
    	       			nextRunTask.setLinkHref(newRunPoint.getLinkHref());
    	       			 this.runTaskDao.storeRunTask(nextRunTask);//分配下一个人审批任务
    	       			 
    	       		//插入事件  通知下一位审批
	        			 storeNextActivitiEvent(nextRunTask);
    	       			
    	       			 //同时新增i一条记录 把下一个历史审批节点
    	       			 HistoryTaskinst historyTaskinst = new HistoryTaskinst();
    	       			 historyTaskinst.setBussnessId(nextRunTask.getBussnessId());
    	       			 historyTaskinst.setName(nextRunTask.getName());
    	       			 historyTaskinst.setFlow(nextRunTask.getFlow());
    	       			 historyTaskinst.setSubmitPer(nextRunTask.getSubmitPer());
    	       			 historyTaskinst.setStartTime(new Date());
    	       			 historyTaskinst.setAssignee(nextRunTask.getAssignee());
    	       			historyTaskinst.setContent(runTask.getContent());
    	       			historyTaskinst.setMyNum(nextRunTask.getMyNum());
    	       			historyTaskinst.setLinkHref(newRunPoint.getLinkHref());
    	       			 this.historyTaskinstDao.storeHistoryTaskinst(historyTaskinst);
    						
        			 
					
				}
				
				
				else {////如果当前人审批结果为同意， 则 往下执行
					
					//3 将当前任务节点的下一审批节点顺序号获取，查询下一任务审批节点
					 String[] keys={"bussnessId","myNum","flow.id"};
					 int myNum =runPoint.getMyNum()+1; 
	      			 String[] values ={runTask.getBussnessId()+"",myNum+"",runTask.getFlow().getId()+""};
					
					List<RunPoint>runPoints=this.runPointDao.loadByKeyArray(keys, values);
					// 4 如果存在下一审批节点 则获取 分配任务
					if(runPoints!=null&&runPoints.size()>0){
						RunPoint nextRunPoint =runPoints.get(0);
						 List<CodeValue> list =this.codeValueDao.loadByKey("code", "21402");//预节点待审核状态
	        			 if(list!=null&&list.size()>0){
	        				 nextRunPoint.setResult(list.get(0));
	        			 }
	        			 this.runPointDao.storeRunPoint(nextRunPoint);
						
	       			 RunTask nextRunTask = new RunTask();
	       			nextRunTask.setBussnessId(runTask.getBussnessId());
	       			nextRunTask.setName(runTask.getName());
	       			nextRunTask.setFlow(runTask.getFlow());
	       			nextRunTask.setSubmitPer(runTask.getSubmitPer());
	       			nextRunTask.setSubmitTime(runTask.getSubmitTime());
	       			nextRunTask.setCreateTime(new Date());
	       			nextRunTask.setAssignee(nextRunPoint.getInspectPser());
	       			nextRunTask.setMyNum(nextRunPoint.getMyNum());
	       			nextRunTask.setContent(runTask.getContent());
	       			nextRunTask.setLinkHref(nextRunPoint.getLinkHref());
	       			 this.runTaskDao.storeRunTask(nextRunTask);//分配下一个人审批任务
	       			 
	       		//插入事件  通知下一位审批
        			 storeNextActivitiEvent(nextRunTask);
	       			 
	       			
	       			 //同时新增i一条记录 把下一个历史审批节点
	       			 HistoryTaskinst historyTaskinst = new HistoryTaskinst();
	       			 historyTaskinst.setBussnessId(nextRunTask.getBussnessId());
	       			 historyTaskinst.setName(nextRunTask.getName());
	       			 historyTaskinst.setFlow(nextRunTask.getFlow());
	       			 historyTaskinst.setSubmitPer(nextRunTask.getSubmitPer());
	       			 historyTaskinst.setStartTime(new Date());
	       			 historyTaskinst.setAssignee(nextRunTask.getAssignee());
	       			historyTaskinst.setContent(runTask.getContent());
	       			historyTaskinst.setMyNum(nextRunTask.getMyNum());
	       			historyTaskinst.setLinkHref(nextRunPoint.getLinkHref());
	       			 this.historyTaskinstDao.storeHistoryTaskinst(historyTaskinst);
						
						
					}else {//如果没有下一任务审批人 则直接返回通过
						
						CodeValue codeValue = null;//当前业务的审核状态
						List<CodeValue> bussnessCodeList =this.codeValueDao.loadByKey("code", "02001");//预节点待审核状态
			  			 if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
			  				codeValue =bussnessCodeList.get(0);
			  			
						
					    StartActiviti startActiviti = new StartActiviti();
					    		startActiviti.setBussnessId(runTask.getBussnessId());
					    		startActiviti.setBussnessType(runTask.getFlow().getFlowCode().getCode());
					    		startActiviti.setBussnessCode(codeValue);
					    		this.runTaskDao.storeBussnessState(startActiviti);
			  			 }
			  			 //查询事件
			  			 storeSuccessActivitiEvent(runTask);
						
					}
					
				}
				
				this.runTaskDao.deleteRunTask(runTask);//将当前审批人的审批任务删除
				
				
				
			}



			public void startAtiviti(StartActiviti sa) throws Exception{
			         Flow flow =sa.getFlow();
			         
			         /**
			          *  如果存流程类型则往下执行
			          */
			         if(flow!=null){
			        	 List<Point> points = this.pointDao.loadByKey("flow.id", flow.getId());
			        	 /**
			        	  *   private long bussnessId;//绑定业务  可能是请假id 报销id 等等
		  private int myNum;//流程任务序号
		  private PersonnelFiles inspectPser;//审核人
		  private Flow flow;//流程类型
		  private String inspectResult;//0新建1待审核2驳回3同意4等待
		  private Date inspectTime;//审核时间
		  private String remark;//审核意见*/
			        	 
			        	 /**
			        	  * 第一步  如果是没有运行流程节点表 则将该业务对应的流程的时间节点获取 并保存到正运行的请假单实例表中
			        	  */
			        	 List<RunPoint> oldRunPoints = this.runPointDao.loadByKey("bussnessId", sa.getBussnessId());
			        	 
			        	 if(oldRunPoints==null||oldRunPoints.size()<1){
			        		 newRunPointWithPoint(sa, flow, points,-1);
			        	 }else{
			        		/**
			        		 * 当前用户有审批人，为二次提交
			        		 */
			        		//1、判断当前业务的状态（根据当前 的runpoints的状态：有一个为拒绝即当前业务为不通过状态）
			        		 boolean isRefuse = false;
			        		 for (RunPoint rPoint : oldRunPoints) {
								if ("21403".equals(rPoint.getResult().getCode())) {
									isRefuse = true;
									break;
								}
							}
		        			 
				        	 if(isRefuse && (sa.getIsSaved()!=null&&sa.getIsSaved().equals("2"))){
				        		//2、删除新建状态的runpoint
				        		 List<RunPoint> deleteList = new ArrayList<RunPoint>();
				        		 for(RunPoint rp:oldRunPoints){
				        			if ("21401".equals(rp.getResult().getCode())) {
				        				deleteList.add(rp);
									}
				        		 }
					        	 runPointDao.deleteAllPoints(deleteList);
					        	//3、增加自己的重新提交节点
					        	 RunPoint double_submit_runPoint = new RunPoint();
					        	 double_submit_runPoint.setBussnessId(sa.getBussnessId());
					        	 double_submit_runPoint.setCreatedTime(new Date());
					        	 double_submit_runPoint.setName("重新提交");
					        	 double_submit_runPoint.setInspectPser(sa.getApplyPerson());
					        	 double_submit_runPoint.setInspectTime(new Date());
					        	 double_submit_runPoint.setFlow(flow);
					        	 List<CodeValue> list =this.codeValueDao.loadByKey("code", "21406");//重新提交状态
			        			 if(list!=null&&list.size()>0){
			        				 double_submit_runPoint.setResult(list.get(0));
			        			 }
			        			 double_submit_runPoint.setMyNum(oldRunPoints.size()-deleteList.size()+1);
			        			 runPointDao.storeRunPoint(double_submit_runPoint);
					        	//4、增加point流程对应的runpoint
			        			 int curRunpointcout = oldRunPoints.size()-deleteList.size()+1;//现有节点数量
			        			 newRunPointWithPoint(sa, flow, points,curRunpointcout);
				        	 }
			        	 }
			        	 /**
			        	  * 第二步 如果用户提交了，无法继续修改  同时按照开启流程第一条任务节点。
			        	  */
			        	 if(sa.getIsSaved()!=null && !sa.getIsSaved().equals("0")){
			        		 //如果是重新提交
			        		 String myNum_str = "";
			        		 if(sa.getIsSaved().equals("2")){
			        			 String[] key = {"code","name"};
			        			 String[] value = {"21406","重新提交"};
			        			 List<CodeValue> codeValues = codeValueDao.loadByKeyArray(key,value);
			        			 CodeValue codeValue = new CodeValue();
			        			 if(codeValues!=null && codeValues.size()>0){
			        				 codeValue = codeValues.get(0);
			        			 }
			        			 //获取当前业务下所有状态为重新提交的人
			        			 String[] key_runPoint = {"bussnessId","result.id","flow.id"};
			        			 Object[] value_runPoint= {sa.getBussnessId(),codeValue.getId(),sa.getFlow().getId()};
			        			 List<RunPoint> runPoints = runPointDao.loadByKeyArray(key_runPoint, value_runPoint);
			        			 if(runPoints != null && runPoints.size() > 0){
			        				 if(runPoints.size() == 1){
			        					 myNum_str = runPoints.get(0).getMyNum()+1+"";
			        				 }else {
			        					 //获取重新提交的人的最大的myNum
			        					 int withMax = runPoints.get(0).getMyNum();
			        					 for (RunPoint rp : runPoints) {
											if(rp.getMyNum() > withMax){
												withMax = rp.getMyNum();
											}
										 }
			        					 myNum_str = withMax+1+"";//最后一个状态为重新提交的记录的下一条数据
									}
			        			 }
			        		 }else{
			        			 myNum_str = "1";
			        		 }
			        		 
			        		 
			        		 String [] key={"myNum","bussnessId"}; 
			    	         String [] value={myNum_str,sa.getBussnessId()+""}; 
			        		 List<RunPoint> runPoints = this.runPointDao.loadByKeyArray(key, value);
			        		 if(runPoints!=null&&runPoints.size()>0){
			        			 RunPoint runPoint = runPoints.get(0);
			        			 List<CodeValue> list =this.codeValueDao.loadByKey("code", "21402");//预节点待审核状态
			        			 if(list!=null&&list.size()>0){
			        				 runPoint.setResult(list.get(0));
			        			 }
			        			 this.runPointDao.storeRunPoint(runPoint);
			        			 
			        			 /***
			        			  *  private long bussnessId;//绑定业务  可能是请假id 报销id 等等
		             private String name;//任务名称
					private Flow flow;//所属流程
					private PersonnelFiles submitPer;//提交人
					private Date submitTime;//提交时间
					private Date createTime;//创建时间
					private PersonnelFiles assignee;//任务办理人
			        			  */
			        			 
			        			 /**
			        			  * 第三步  为第一个任务节点的人分配任务 
			        			  */
			        			 RunTask runTask = new RunTask();
			        			 
			        			 runTask.setBussnessId(sa.getBussnessId());
			        			 runTask.setName(sa.getName());
			        			 runTask.setFlow(flow);
			        			 runTask.setSubmitPer(sa.getApplyPerson());
			        			 runTask.setSubmitTime(new Date());
			        			 runTask.setCreateTime(new Date());
			        			 runTask.setAssignee(runPoint.getInspectPser());
			        			 runTask.setMyNum(runPoint.getMyNum());
			        			 runTask.setLinkHref(sa.getLinkHref());
//			        			 
//			        			 StringBuffer sb = new StringBuffer();
//			        			 sb.append("部门为："+lb.getApplyPerson().getDept().getName()+"的"+lb.getApplyPerson().getName()+"在时间"+DateUtil.getDate(lb.getCreateDate(), "yyyy-MM-dd HH:mm:ss")+"时提出了类型为："+runTask.getFlow().getName())
//			        			 .append("的流程<br/>")
//			        			 .append("具体内容是：从"+DateUtil.getDate(lb.getStartTime(), "yyyy-MM-dd HH:mm:ss")+"到"+DateUtil.getDate(lb.getEndTime(), "yyyy-MM-dd HH:mm:ss"))
//			        			 .append("期间内请假,共计时(工时)："+lb.getManHour()+"<br/>")
//			        			 .append("请假原因为："+lb.getFailReason());
//			        			 runTask.setContent(sb.toString());
			        			 runTask.setContent(sa.getContent());
			        			 
			        			 this.runTaskDao.storeRunTask(runTask);
			        			 
			        			 
			        			 //插入事件  通知下一位审批
			        			 storeNextActivitiEvent(runTask);
			        				
			        			 
			        			 
			        			 
			        			 /***
			        			  *  private long bussnessId;//绑定业务  可能是请假id 报销id 等等
		            private String name;//任务名称
					private Flow flow;//所属流程
					private PersonnelFiles submitPer;//提交人
					private Date startTime;//提交时间 
					private Date endTime;//创建时间
					private long duration;//用时
					private PersonnelFiles assignee;//任务办理人
			        			  */
			        			 /**
			        			  * 第四步 保存到历史任务表中
			        			  */
			        			 HistoryTaskinst historyTaskinst = new HistoryTaskinst();
			        			 historyTaskinst.setBussnessId(sa.getBussnessId());
			        			 historyTaskinst.setName(sa.getName());
			        			 historyTaskinst.setFlow(flow);
			        			 historyTaskinst.setSubmitPer(sa.getApplyPerson());
			        			 historyTaskinst.setStartTime(new Date());
			        			 historyTaskinst.setAssignee(runPoint.getInspectPser());
			        			 historyTaskinst.setContent(sa.getContent());
			        			 historyTaskinst.setMyNum(runPoint.getMyNum());
			        			 historyTaskinst.setLinkHref(sa.getLinkHref());
			        			 this.historyTaskinstDao.storeHistoryTaskinst(historyTaskinst);
			        			 
			        		 }
			        		 
			        	 }
			        	 
			         }
				
				
			}
//			public void storeBussnessState(String bussnessType ,CodeValue codeValue,long bussnessId) {
//				Class clazz=null;
//				Object obj=null;
//				try {
//					clazz = Class.forName(bussnessType);
//					//这里的类名是全名。。有包的话要加上包名  
//					   obj = clazz.newInstance();  //创建对象
//					   Field[] fields = clazz.getDeclaredFields(); //获取所有属性 
//					   //写数据  
//					   for(Field f : fields) {  
//						   if(f.getName().contains("status")){//获取状态属性
//					     PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);  
//					     Method wM = pd.getWriteMethod();//获得写方法  
//					     wM.invoke(obj, codeValue);//赋值  
//						   }
//						  
//					  }
//					   Method method = ReflectUtil.getDeclaredMethod(obj, "setId", Long.class);
//					   if(method!=null){
//						   method.invoke(obj, bussnessId);
//					   }
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				 
//				 this.runTaskDao.storeBussnessState(obj);//将当前业务单变更为审核中状态
//				
//			}


			/**
			 * 根据流程（point）默认审批人，创建具体流程（runpoint）审批人
			 * @param sa
			 * @param flow
			 * @param points
			 * @param object 
			 * @throws DaoException
			 */
			private void newRunPointWithPoint(StartActiviti sa, Flow flow, List<Point> points, Integer myNum) throws DaoException {
				Integer thisMyNum = (myNum != -1?myNum+1:myNum);
				for(Point point:points){
					 RunPoint runPoint = new RunPoint();
					 runPoint.setBussnessId(sa.getBussnessId());
					 runPoint.setCreatedTime(new Date());
					 runPoint.setName(point.getName());
					 runPoint.setFlow(flow);
					 runPoint.setLinkHref(sa.getLinkHref());
					 if (point.getPersonnelFiles()==null) {//判断带过来的流程节点 有没有审批人，如果没有 则按照以下两种方案进行
						 //如果该人存在上级领导人 那么 就把上级领导人带过来，
						 if(sa.getApplyPerson().getSuperiorLeader()!=null){
						 runPoint.setInspectPser(sa.getApplyPerson().getSuperiorLeader());
						 //如果该人不存在上级领导人 那么就把自己的作为审批人带进去
						 }else {
							 runPoint.setInspectPser(sa.getApplyPerson());
						}
						 //经过判断 带过来流程节点有审批人 怎么直接按照流程审批人带过来
					}else {
						
						 runPoint.setInspectPser(point.getPersonnelFiles());
					}
					
					 List<CodeValue> list =this.codeValueDao.loadByKey("code", "21401");//新建状态
					 if(list!=null&&list.size()>0){
						 runPoint.setResult(list.get(0));
					 }
					 if(thisMyNum != -1){
						 runPoint.setMyNum(thisMyNum);
						 thisMyNum++;
					 }else{
						 runPoint.setMyNum(point.getMyNum()); 
					 }
					 runPoint.setCreatedTime(new Date());
					 this.runPointDao.storeRunPoint(runPoint);
					 
				 }
			}


			//保存AtivitiList
			public void storeAtivitiList(ActiviFlow activiFlow) throws DaoException {
				// TODO Auto-generated method stub
				List<ActiviFlow> activiFlows = activiFlow.getActiviFlows();
				if(activiFlows != null && activiFlows.size()>0){
					for (ActiviFlow actFlow : activiFlows) {
						this.storeAtiviti(actFlow);
					}
				}
			}
			
			//通知下一位审批
			public void storeNextActivitiEvent(RunTask runTask){
				
				try {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10033");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventTypes不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					List<User> listApplyUsers =userManager.loadByKey("code", runTask.getSubmitPer().getCode());
					if(listApplyUsers!=null&&listApplyUsers.size()>0){
						event.setUserId(listApplyUsers.get(0).getId() + "");
					}
					
					Map<String, String> map = new HashMap<String, String>();
					List<User> listInspectUsers =userManager.loadByKey("code",runTask.getAssignee().getCode() );
					if(listInspectUsers!=null&&listInspectUsers.size()>0){
						map.put("users", listInspectUsers.get(0).getId()+"");
					}
					
					
					map.put("runTaskId", runTask.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(runTask.getCreatedTime())+","+runTask.getSubmitPer().getName()+"提交了任务为"+runTask.getName());
					map.put("url",runTask.getLinkHref());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					
				} catch (DaoException e) {
					// TODO: handle exception
				}
				 
				
				
			}
			
			//审批拒绝通知提交人
			public void storeFailActivitiEvent(RunTask runTask){
				
				try {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10034");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventTypes不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					List<User> listAssigneeUsers =userManager.loadByKey("code", runTask.getAssignee().getCode());
					if(listAssigneeUsers!=null&&listAssigneeUsers.size()>0){
						event.setUserId(listAssigneeUsers.get(0).getId() + "");
					}
					
					Map<String, String> map = new HashMap<String, String>();
					List<User> listSubmitUsers =userManager.loadByKey("code",runTask.getSubmitPer().getCode() );
					if(listSubmitUsers!=null&&listSubmitUsers.size()>0){
						map.put("users", listSubmitUsers.get(0).getId()+"");
					}
					
					
					map.put("runTaskId", runTask.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+","+runTask.getAssignee().getName()+"拒绝了您提交的类型为"+runTask.getFlow().getName()+"的申请");
					map.put("url",runTask.getLinkHref());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					
				} catch (DaoException e) {
					// TODO: handle exception
				}
				 
				
				
			}
			//最终审批通过通知提交人及抄送人
			public void storeSuccessActivitiEvent(RunTask runTask){
				
				try {
					EventType eventType = null;
					List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10035");
					if (eventTypes != null && eventTypes.size() > 0) {
						eventType = eventTypes.get(0);
					} else {
						logger.info("eventTypes不存在！");
					}
					EventNew event = new EventNew();
					event.setEffectflag("E");
					event.setEventType(eventType);
					event.setName(eventType.getName());
					List<User> listApplyUsers =userManager.loadByKey("code", runTask.getAssignee().getCode());
					if(listApplyUsers!=null&&listApplyUsers.size()>0){
						event.setUserId(listApplyUsers.get(0).getId() + "");
					}
					
					Map<String, String> map = new HashMap<String, String>();
					List<User> listInspectUsers =userManager.loadByKey("code",runTask.getSubmitPer().getCode() );

					String users ="";
					if(listInspectUsers!=null&&listInspectUsers.size()>0){
						users= listInspectUsers.get(0).getId()+"";
					}
					StartActiviti startActiviti = new StartActiviti();
					startActiviti.setBussnessId(runTask.getBussnessId());
					startActiviti.setFlow(runTask.getFlow());
					List<String> codeList = this.runTaskDao.loadCodySendPerson(startActiviti);
					if (codeList!=null&&codeList.size()>0) {
						for (int i = 0; i < codeList.size(); i++) {
							List<User> tempUsers = this.userManager.loadByKey("code", codeList.get(i));
							if(tempUsers!=null&&tempUsers.size()>0){
								if(users.equals("")){
									users=tempUsers.get(0).getId()+"";
								}else {
									users+=","+tempUsers.get(0).getId();
								}
							}
						}
					}
					
					map.put("users",users);
					map.put("runTaskId", runTask.getId() + "");
					map.put("name", new SimpleDateFormat("yyyy-MM-dd").format(new Date())+","+runTask.getAssignee().getName()+"同意了您提交的类型为"+runTask.getFlow().getName()+"的申请");
					map.put("url",runTask.getLinkHref());
					String moreinfo = JSONObject.fromObject(map).toString();
					event.setMoreinfo(moreinfo);
					eventNewManager.storeEventNew(event);
					
				} catch (DaoException e) {
					// TODO: handle exception
				}
				 
				
				
			}


}

