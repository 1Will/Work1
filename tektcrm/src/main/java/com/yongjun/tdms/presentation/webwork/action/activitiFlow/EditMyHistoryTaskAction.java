/*     */ package com.yongjun.tdms.presentation.webwork.action.activitiFlow;
import java.util.ArrayList;
import java.util.List;

/*     */ 
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.activitiFlow.HistoryTaskinst;
import com.yongjun.tdms.model.activitiFlow.RunPoint;
import com.yongjun.tdms.service.activitiFlow.HistoryTaskinstManager;
import com.yongjun.tdms.service.activitiFlow.RunPointManager;
/*     */ 
/*     */ public class EditMyHistoryTaskAction extends PrepareAction
/*     */ {
/*     */   private static final long serialVersionUID = 732668911930478662L;
/*     */   private HistoryTaskinstManager historyTaskinstManager;
/*     */   private HistoryTaskinst historyTaskinst;
            private String departmentName;
			private final CodeValueManager codeValueManager;
			private final RunPointManager runPointManager;
			private RunPoint runPoint;
/*     */ 
/*     */   public EditMyHistoryTaskAction(HistoryTaskinstManager historyTaskinstManager,CodeValueManager codeValueManager
		,RunPointManager runPointManager)
/*     */   {
/*  51 */     this.historyTaskinstManager = historyTaskinstManager;
              this.codeValueManager = codeValueManager;
              this.runPointManager=runPointManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	
/*  59 */     if (null == this.historyTaskinst)
/*     */     {
/*  61 */       if (hasId("historyTaskinst.id"))
/*     */       {
/*  63 */         this.historyTaskinst = this.historyTaskinstManager.loadHistoryTaskinst(getId("historyTaskinst.id"));
				  departmentName = historyTaskinst.getSubmitPer().getDept().getName();
				
				   //根据审批业务id 和审批人code 获取审批预节点
		           String[] key={"bussnessId","inspectPser.code","myNum"};
	     		   String[] value={historyTaskinst.getBussnessId()+"",historyTaskinst.getAssignee().getCode(),historyTaskinst.getMyNum()+""};
		           List<RunPoint> runPoints = this.runPointManager.loadByKeyArray(key, value);
		           if(runPoints!=null && runPoints.size() > 0){
		        	   runPoint =runPoints.get(0);
		           }
/*     */       } else {
/*  69 */         this.historyTaskinst = new HistoryTaskinst();
/*     */       }
/*     */     } else {
/*  73 */       this.historyTaskinst = new HistoryTaskinst();
/*     */     }
/*     */   }
/*     */ 
/*     */   public String save()
/*     */   {
/*     */ 
/*     */ 
/* 160 */     return "success";
/*     */   }
			public List<CodeValue> getAllResultTypes() {
				try {
					List codes = new ArrayList();
					List one = this.codeValueManager.loadByKey("code", "214");
					if ((null != one) && (one.size() > 0)) {
						List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
						if ((null != list) && (list.size() > 0)) {
							for(int i=0;i<list.size();i++){
								CodeValue codeValue =(CodeValue) list.get(i);
								if(!codeValue.getName().equals("新建")&&!codeValue.getName().equals("审核中") && !codeValue.getName().equals("重新提交")){
									codes.add(codeValue);
								}
							}
						}
			
					}
			
					return codes;
				} catch (DaoException e) {
					e.printStackTrace();
					return new ArrayList();
				}
			}
/*     */ 
/*     */ 
/*     */   public HistoryTaskinst getHistoryTaskinst()
/*     */   {
/* 182 */     return this.historyTaskinst;
/*     */   }
/*     */ 
/*     */   public void setHistoryTaskinst(HistoryTaskinst historyTaskinst)
/*     */   {
/* 190 */     this.historyTaskinst = historyTaskinst;
/*     */   }
/*     */ 
			public String getDepartmentName() {
				return departmentName;
			}
			public void setDepartmentName(String departmentName) {
				this.departmentName = departmentName;
			}
			public RunPoint getRunPoint() {
				return runPoint;
			}
			public void setRunPoint(RunPoint runPoint) {
				this.runPoint = runPoint;
			}


/*     */ }
