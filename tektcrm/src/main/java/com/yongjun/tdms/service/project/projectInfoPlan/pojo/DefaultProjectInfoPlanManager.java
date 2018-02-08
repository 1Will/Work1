/*    */ package com.yongjun.tdms.service.project.projectInfoPlan.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.project.projectInfoPlan.ProjectInfoPlanDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

/*    */ import java.util.Collection;
import java.util.HashMap;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProjectInfoPlanManager extends BaseManager
/*    */   implements ProjectInfoPlanManager
/*    */ {
/*    */   private ProjectInfoPlanDao projectInfoPlanDao;
           private ProductionOperationDetailManager productionOperationDetailManager; 
           private ProductListManager productListManager;
			public DefaultProjectInfoPlanManager(ProjectInfoPlanDao ProjectInfoPlanDao,ProductionOperationDetailManager productionOperationDetailManager,ProductListManager productListManager){
			this.projectInfoPlanDao = ProjectInfoPlanDao;
			this.productionOperationDetailManager=productionOperationDetailManager;
			this.productListManager=productListManager;
			}
/*    */  public void deleteAllProjectInfoPlan(Collection<ProjectInfoPlan> ProjectInfoPlan)
/*    */   {
	        if(ProjectInfoPlan!=null&&ProjectInfoPlan.size()>0){
	        for(ProjectInfoPlan plan:ProjectInfoPlan){
	        	//获取当前要删除的计划任务的排序号
	        	 int orderNumber  =0;
	        	 if(plan.getOrderNumber()!=null){
	        		 orderNumber= plan.getOrderNumber();
	        	 }
	        	 //删除当前任务
	        	 this.projectInfoPlanDao.deleteProjectInfoPlan(plan);
	        	 //根据查询语句获取删除的计划之后的所有计划 
	        	 StringBuffer sb = new 	StringBuffer();
	        	 sb.append("from ProjectInfoPlan p where p.orderNumber >"+orderNumber);
	        	 if(plan.getProjectInfo()!=null){
	        		 sb.append(" and p.projectInfo.id="+plan.getProjectInfo().getId());
	        	 }else if(plan.getContractManagement()!=null){
	        		 sb.append(" and p.contractManagement.id="+plan.getContractManagement().getId());
	        	 }else if(plan.getProductionOperationDetail()!=null){
	        		 sb.append(" and p.productionOperationDetail.id="+plan.getProductionOperationDetail().getId());
	        	 }
	        	 List<ProjectInfoPlan> list = this.projectInfoPlanDao.loadAllProjectInfoPlansByHql(sb.toString());
	        	 if(list!=null&&list.size()>0){
	        		 for(ProjectInfoPlan p:list){
	        			 //把查询到的计划 排序号逐个减去1  保证序号是连续的
	        			 p.setOrderNumber(p.getOrderNumber()-1);
	        			 this.projectInfoPlanDao.storeProjectInfoPlan(p);
	        		 }	 
	        	 }
	        }
	        }
///* 28 */     this.projectInfoPlanDao.deleteAllProjectInfoPlan(ProjectInfoPlan);
/*    */   }
/*    */ 
/*    */   public void deleteProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
/* 32 */     this.projectInfoPlanDao.deleteProjectInfoPlan(ProjectInfoPlan);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlan(Long[] ProjectInfoPlanIds) {
/* 36 */     return this.projectInfoPlanDao.loadAllProjectInfoPlan(ProjectInfoPlanIds);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadAllProjectInfoPlans() {
/* 40 */     return this.projectInfoPlanDao.loadAllProjectInfoPlans();
/*    */   }
/*    */ 
/*    */   public ProjectInfoPlan loadProjectInfoPlan(Long ProjectInfoPlanId) {
/* 44 */     return this.projectInfoPlanDao.loadProjectInfoPlan(ProjectInfoPlanId);
/*    */   }
/*    */ 
/*    */   public void storeProjectInfoPlan(ProjectInfoPlan ProjectInfoPlan) {
	List<ProjectInfoPlan> totalInfoPlans=null;
	        try {//查询当前产品计划下的工作计划记录 
	        	// 用于后面的判断，如果没有记录 则将当前产品计划状态更新为第一条工作计划的名称，如果有 则不动产品计划状态
	        	if(ProjectInfoPlan.getProductionOperationDetail()!=null){
	        	totalInfoPlans = this.projectInfoPlanDao.loadByKey("productionOperationDetail.id", ProjectInfoPlan.getProductionOperationDetail().getId());
	        	}
			
	        } catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
/* 48 */     this.projectInfoPlanDao.storeProjectInfoPlan(ProjectInfoPlan);
            
			if(ProjectInfoPlan.getProductionOperationDetail()!=null){
				if(totalInfoPlans==null||totalInfoPlans.size()<1){
					ProductionOperationDetail p = ProjectInfoPlan.getProductionOperationDetail();
					p.setProStatuString("当前状态："+ProjectInfoPlan.getName()+"   负责人:"+ProjectInfoPlan.getPersonnelFiles().getName());
					//p判断这条产品计划是否绑定到合同的产品明细 如果是 将绑定的合同产品状态更新为当前的生产状态
					
					if(p.getProductList()!=null){
						ProductList pl = p.getProductList();
						pl.setProStatuString(p.getProStatuString());
						this.productListManager.storeProductList(pl);
						
					}
					
				}
				
				   try {
				
				 if(ProjectInfoPlan.getPercentt()==100){//如果当前任务完成  进度为百分比
					 
					//任务的排序计划  将当前的任务计划排序号 加1  查询下一个任务计划
		             String[] key={"orderNumber","productionOperationDetail.id"};  
		             int orberNum = ProjectInfoPlan.getOrderNumber()+1;
		             String[] value={orberNum+"",ProjectInfoPlan.getProductionOperationDetail().getId()+""}; 
		             List<ProjectInfoPlan> plans=null;
		          
		            	 plans= this.projectInfoPlanDao.loadByKeyArray(key, value);
		            	 
		            	 
						 
						ProductionOperationDetail p = ProjectInfoPlan.getProductionOperationDetail();
						if(plans!=null&&plans.size()>0){//如果存在记录 曾将当前产品计划状态更新为下一工作计划的名称
						p.setProStatuString("当前状态："+plans.get(0).getName()+"   负责人:"+plans.get(0).getPersonnelFiles().getName());
						this.productionOperationDetailManager.storeProductionOperationDetail(p);
						
						}else {
							p.setProStatuString("已完结");//如果不存在 说明是最后一个任务了，则更新为已完结
							this.productionOperationDetailManager.storeProductionOperationDetail(p);
						}
					
						//p判断这条产品计划是否绑定到合同的产品明细 如果是 将绑定的合同产品状态更新为当前的生产状态
						
						if(p.getProductList()!=null){
							ProductList pl = p.getProductList();
							pl.setProStatuString(p.getProStatuString());
							this.productListManager.storeProductList(pl);
							
						}
					 
				 }
				 } catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
			}
/*    */   }

/*    */   public void disabledProjectInfoPlans(List<ProjectInfoPlan> ProjectInfoPlans) {
/* 56 */     for (ProjectInfoPlan bv : ProjectInfoPlans) {
/* 57 */       bv.setDisabled(true);
/* 58 */       this.projectInfoPlanDao.storeProjectInfoPlan(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enableProjectInfoPlans(List<ProjectInfoPlan> ProjectInfoPlans) {
/* 63 */     for (ProjectInfoPlan bv : ProjectInfoPlans) {
/* 64 */       bv.setDisabled(false);
/* 65 */       this.projectInfoPlanDao.storeProjectInfoPlan(bv);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadByKey(String keyName, Object keyValue) throws DaoException {
/* 70 */     return this.projectInfoPlanDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */ 
/*    */   public List<ProjectInfoPlan> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
/* 74 */     return this.projectInfoPlanDao.loadByKeyArray(keyNames, keyValues);
/*    */   }
/*    */ 	
/*    */   
/*    */
		public List loadForMyTeam(HashMap  map) {
	// TODO Auto-generated method stub
			return this.projectInfoPlanDao.loadForMyTeam(map);
		}
		public Integer loadMaxOrderNumber(HashMap map) {
			return  this.projectInfoPlanDao.loadMaxOrderNumber(map);
		}
		public List loadOrderProjectInfoPlan(HashMap map) {
			return this.projectInfoPlanDao.loadOrderProjectInfoPlan(map);
		} 
		public void saveOrderNum(String id1, String id2) {
		   if((!"".equals(id1) && id1 != null) && (!"".equals(id2) && id2 != null)){
			   ProjectInfoPlan projectInfoPlan1 = projectInfoPlanDao.loadProjectInfoPlan(Long.parseLong(id1));
			   ProjectInfoPlan projectInfoPlan2 = projectInfoPlanDao.loadProjectInfoPlan(Long.parseLong(id2));
			   Integer orderNumber1 = projectInfoPlan1.getOrderNumber();
			   Integer orderNumber2 = projectInfoPlan2.getOrderNumber();
			   projectInfoPlan1.setOrderNumber(orderNumber2);
			   projectInfoPlan2.setOrderNumber(orderNumber1);
			   this.projectInfoPlanDao.storeProjectInfoPlan(projectInfoPlan1);
			   this.projectInfoPlanDao.storeProjectInfoPlan(projectInfoPlan2);
			   }
		} 
	}

