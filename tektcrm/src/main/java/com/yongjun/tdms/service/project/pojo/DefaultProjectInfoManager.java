package com.yongjun.tdms.service.project.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.aspectj.apache.bcel.classfile.Code;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.project.ProjectInfoDao;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultProjectInfoManager extends BaseManager implements ProjectInfoManager {
	private ProjectInfoDao projectInfoDao;
	private final YongJunSequenceManager yongJunSequenceManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;

	public DefaultProjectInfoManager(ProjectInfoDao projectInfoDao, YongJunSequenceManager yongJunSequenceManager, 
			UserManager userManager,ProjectInfoPersonnelsManager projectInfoPersonnelsManager,ProjectInfoPlanManager projectInfoPlanManager,CodeValueManager codeValueManager) {
		this.projectInfoDao = projectInfoDao;
		this.yongJunSequenceManager=yongJunSequenceManager;
		this.userManager = userManager;
		this.projectInfoPersonnelsManager=projectInfoPersonnelsManager;
		this.projectInfoPlanManager=projectInfoPlanManager;
		this.codeValueManager=codeValueManager;
		
	}

	public void deleteAllProjectInfo(Collection<ProjectInfo> ProjectInfos) {
		this.projectInfoDao.deleteAllProjectInfo(ProjectInfos);
	}

	public void deleteProjectInfo(ProjectInfo ProjectInfo) {
		this.projectInfoDao.deleteProjectInfo(ProjectInfo);
	}

	public List<ProjectInfo> loadAllProjectInfo(Long[] ProjectInfoIds) {
		return this.projectInfoDao.loadAllProjectInfo(ProjectInfoIds);
	}

	public List<ProjectInfo> loadAllProjectInfos() {
		return this.projectInfoDao.loadAllProjectInfos();
	}

	public ProjectInfo loadProjectInfo(Long ProjectInfoId) {
		return this.projectInfoDao.loadProjectInfo(ProjectInfoId);
	}

	public void storeProjectInfo(ProjectInfo ProjectInfo) {
		if (ProjectInfo.isNew()) {
			String toCode="MP";
			if(ProjectInfo.getCustomer().getBusinessType().getCode().equals("21001")){
				 toCode="JP";
			}
			String code1 =(String)this.yongJunSequenceManager.generateeCodeTypeReplacFormtter(YongJunSequenceConstant.CODE_PROJECT,toCode);
			ProjectInfo.setCode(code1);
		}
		this.projectInfoDao.storeProjectInfo(ProjectInfo);
	}

	public void setprojectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}

	public void disabledProjectInfos(List<ProjectInfo> ProjectInfos) {
		for (ProjectInfo bv : ProjectInfos) {
			bv.setDisabled(true);
			this.projectInfoDao.storeProjectInfo(bv);
		}
	}

	public void enableProjectInfos(List<ProjectInfo> ProjectInfos) {
		for (ProjectInfo bv : ProjectInfos) {
			bv.setDisabled(false);
			this.projectInfoDao.storeProjectInfo(bv);
		}
	}

	public List<ProjectInfo> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.projectInfoDao.loadByKey(keyName, keyValue);
	}

	public List<ProjectInfo> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.projectInfoDao.loadByKeyArray(keyNames, keyValues);
	}

	public ProjectInfoDao getProjectInfoDao() {
		return projectInfoDao;
	}

	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}

	// public List<ProjectInfo> loadProjectByDate(String date){
	public int loadProjectByDate(String userId, String date) {
		User user = userManager.loadUser(Long.parseLong(userId));
		List<ProjectInfo> plist = this.projectInfoDao.loadByDate(date, user.getName());
		int size = plist.size();
		return size;
	}
	public List<String> loadProjectInfoByCustomerId(String customerId) throws DaoException {
		// TODO Auto-generated method stub
		List<ProjectInfo> projectInfos = this.projectInfoDao.loadByKey("customer", customerId);
		List<String> list = new ArrayList<String>();
		if(projectInfos!=null){
			if(projectInfos.size() == 1){
				ProjectInfo projectInfo = (ProjectInfo)projectInfos.get(0);
				list.add(projectInfo.getId()+"");//项目id
				list.add(projectInfo.getName());//项目名称
				list.add(projectInfo.getContact().getId()+"");//联系人id
				list.add(projectInfo.getContact().getName());//联系人姓名
				list.add(projectInfo.getCustomer().getMobilePhone());//联系电话
				
			}
		}
		return list;
	}

	public void saveProjectPerAndPlan(long id_1, long id_2) throws DaoException  {
		ProjectInfo projectInfo=projectInfoDao.loadProjectInfo(id_2);//获取当前项目
		//根据项目id获取项目所要复制联系人列表
		List<ProjectInfoPersonnels> projectInfoPersonnels=projectInfoPersonnelsManager.loadByKey("projectInfo", id_1);
		//根据项目id获取所要复制的项目总计划
		List<ProjectInfoPlan>projectInfoPlans=projectInfoPlanManager.loadByKey("projectInfo", id_1);
		//根据项目id获取项目当前项目联系人列表
		List<ProjectInfoPersonnels> projectInfoPersonnels_now=projectInfoPersonnelsManager.loadByKey("projectInfo", id_2);
		//根据项目id获取项目当前项目项目总计划
		List<ProjectInfoPlan>projectInfoPlans_now=projectInfoPlanManager.loadByKey("projectInfo", id_2);
		//判断所要复制的联系人是否存在，如存在则移除
		if(projectInfoPersonnels_now!=null&&projectInfoPersonnels_now.size()>0
				&& projectInfoPersonnels!=null && projectInfoPersonnels.size()>0){
			Iterator<ProjectInfoPersonnels> it = projectInfoPersonnels.iterator();
			while(it.hasNext()) {
				String name=it.next().getProPerson().getName();
				for(ProjectInfoPersonnels p:projectInfoPersonnels_now){
					if(name.equals(p.getProPerson().getName())){
						it.remove();
					}
				}
			}
		}
		//判断所要复制的总计划是否存在，如存在则移除
		if(projectInfoPlans_now!=null&&projectInfoPlans_now.size()>0
				&& projectInfoPlans!=null && projectInfoPlans.size()>0){
			Iterator<ProjectInfoPlan> it = projectInfoPlans.iterator();
			while(it.hasNext()) {
				String name=it.next().getName();
				for(ProjectInfoPlan p:projectInfoPlans_now){
					if(name.equals(p.getName())){
						it.remove();
					}
				}
			}
		}
		//复制项目联系人
		if(projectInfoPersonnels!=null && projectInfoPersonnels.size()>0){
			for(ProjectInfoPersonnels p:projectInfoPersonnels){
				ProjectInfoPersonnels personnels=new ProjectInfoPersonnels();
				personnels.setProjectInfo(projectInfo);
				personnels.setProPerson(p.getProPerson());
				personnels.setBusinessType(p.getBusinessType());
				personnels.setOutline(p.getOutline());
				projectInfoPersonnelsManager.storeProjectInfoPersonnels(personnels);
			}
		}
		//复制项目总计划
		if(projectInfoPlans!=null&& projectInfoPlans.size()>0){
			for(ProjectInfoPlan p:projectInfoPlans){
				ProjectInfoPlan plan=new ProjectInfoPlan();
				plan.setProjectInfo(projectInfo);
				plan.setName(p.getName());
//				plan.setStartDate(p.getStartDate());
//				plan.setEndDate(p.getEndDate());
//				plan.setStartFactDate(p.getStartFactDate());
//				plan.setEndFactDate(p.getEndFactDate());
				plan.setPersonnelFiles(p.getPersonnelFiles());
				plan.setAssist(p.getAssist());
				plan.setAssistIds(p.getAssistIds());
//				plan.setOutline(p.getOutline());
				plan.setPercentt(p.getPercentt());
				plan.setPriority(p.getPriority());
				plan.setPlanState(p.getPlanState());
//				plan.setIsSaved(p.getIsSaved());
				plan.setOrderNumber(p.getOrderNumber());
//				plan.setProjectInfoPlan_2(p.getProjectInfoPlan_2());
				projectInfoPlanManager.storeProjectInfoPlan(plan);
			}
		}
		
	}

	/**
	 *fromProductionOperationDetail 源资源对象
	 *toProductionOperationDetail  目标资源对象计划
	 */
	public void saveProductionOperationDetailPlan(long fromProductionOperationDetail, long toProductionOperationDetail)
			throws DaoException {
		//源资源对象计划
		List<ProjectInfoPlan>fromPlans=projectInfoPlanManager.loadByKey("productionOperationDetail", fromProductionOperationDetail);
		List<CodeValue> planSta =  this.codeValueManager.loadByKey("code", "21101");
		
		if(fromPlans!=null&&fromPlans.size()>0){
			for(ProjectInfoPlan p:fromPlans){
				ProjectInfoPlan plan = new ProjectInfoPlan();
				ProductionOperationDetail  productionOperationDetail = new ProductionOperationDetail();
				productionOperationDetail.setId(toProductionOperationDetail);
				plan.setProductionOperationDetail(productionOperationDetail);
				List<CodeValue>  one = this.codeValueManager.loadByKey("name", p.getName());
				if(one!=null&&one.size()>0){
					plan.setProductionPoint(one.get(0));
				}
				plan.setName(p.getName());
				plan.setPersonnelFiles(p.getPersonnelFiles());
				plan.setAssist(p.getAssist());
				plan.setAssistIds(p.getAssistIds());
				plan.setPriority(p.getPriority());
				plan.setPercentt(0);
				if(planSta!=null&&planSta.size()>0){//默认待执行
				plan.setPlanState(planSta.get(0));
				}
				plan.setIsSaved("0");
				//查询当前排序号
				HashMap map = new HashMap();
				map.put("productionOperationDetailId", toProductionOperationDetail);
				int orderNumber= projectInfoPlanManager.loadMaxOrderNumber(map);
				plan.setOrderNumber(orderNumber);
				projectInfoPlanManager.storeProjectInfoPlan(plan);
				
			}
		}
		
	}

}
