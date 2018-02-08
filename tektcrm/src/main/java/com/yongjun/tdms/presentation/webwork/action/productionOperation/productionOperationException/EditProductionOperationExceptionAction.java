package com.yongjun.tdms.presentation.webwork.action.productionOperation.productionOperationException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.event.EventNew;
import com.yongjun.tdms.model.base.event.EventType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.base.event.EventTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;
import com.yongjun.tdms.service.productionOperation.productionOperationException.ProductionOperationExceptionManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class EditProductionOperationExceptionAction extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final ProductionOperationDetailManager productionOperationDetailManager;
	private final ProductionOperationExceptionManager productionOperationExceptionManager;
	private final CodeValueManager codeValueManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final UserManager userManager;
	private ProductionOperationDetail productionOperationDetail;
	private ProductionOperationException productionOperationException;
	private final EventNewManager eventNewManager;
	private final EventTypeManager eventTypeManager;

	public EditProductionOperationExceptionAction(ProductionOperationDetailManager productionOperationDetailManager,ProductionOperationExceptionManager productionOperationExceptionManager
			,CodeValueManager codeValueManager,ProjectInfoPlanManager projectInfoPlanManage,PersonnelFilesManager personnelFilesManager,UserManager userManager
			,EventNewManager eventNewManager,EventTypeManager eventTypeManager) {
		this.productionOperationDetailManager = productionOperationDetailManager;
		this.productionOperationExceptionManager=productionOperationExceptionManager;
		this.codeValueManager=codeValueManager;
		this.projectInfoPlanManager=projectInfoPlanManage;
		this.personnelFilesManager=personnelFilesManager;
		this.userManager=userManager;
		this.eventNewManager=eventNewManager;
		this.eventTypeManager=eventTypeManager;
		
	}


	public void prepare() throws Exception {
			if (hasId("productionOperationDetail.id")) {
				this.productionOperationDetail = this.productionOperationDetailManager.loadProductionOperationDetail(getId("productionOperationDetail.id"));
			} 
			
			if (hasId("productionOperationException.id")) {
				this.productionOperationException = this.productionOperationExceptionManager.loadProductionOperationException(getId("productionOperationException.id"));
//				this.productionOperation =this.productionOperationException.getProductionOperation();
			}else {
				this.productionOperationException = new ProductionOperationException();
			}
	}


	public String save() throws Exception {
		boolean isNew = this.productionOperationException.isNew();
		if(hasId("productionOperationDetail.id")){
			this.productionOperationException.setProductionOperationDetail(this.productionOperationDetail);
		}
		
		if(hasId("projectInfoPlan.id")){
			this.productionOperationException.setProjectInfoPlan(this.projectInfoPlanManager.loadProjectInfoPlan(getId("projectInfoPlan.id")));
		}
		
		if(hasId("findPersion.id")){
			this.productionOperationException.setFindPersion(this.personnelFilesManager.loadPersonnel(getId("findPersion.id")));
		}
		if(hasId("solvePersion.id")){
			this.productionOperationException.setSolvePersion(this.personnelFilesManager.loadPersonnel(getId("solvePersion.id")));
		}
		if(hasId("statu.id")){
			this.productionOperationException.setStatu(this.codeValueManager.loadCodeValue(getId("statu.id")));
		}
		
		
		String isSaved = this.request.getParameter("isSaved");
		this.productionOperationException.setIsSaved(isSaved);
		this.productionOperationExceptionManager.storeProductionOperationException(this.productionOperationException);
		try {
			if (isNew) {
				addActionMessage(getText("产品计划异常保存成功", Arrays.asList(new Object[] { this.productionOperationException.getName() })));
			}else {
				if(isSaved!=null&&isSaved.equals("1")){
					addActionMessage(getText("产品计划异常提交成功", Arrays.asList(new Object[] { this.productionOperationException.getName() })));
				 //
					saveEventNew();
					
				}else{
					addActionMessage(getText("产品计划异常修改成功", Arrays.asList(new Object[] { this.productionOperationException.getName() })));
					
				}
			}
			
		} catch (Exception e) {
			addActionMessage(getText("productionOperation.add.error", Arrays.asList(new Object[] { this.productionOperationException.getName() })));
			e.printStackTrace();
			return "error";
		}

		return "success";
	}
	
	





	public ProductionOperationDetail getProductionOperationDetail() {
		return productionOperationDetail;
	}


	public void setProductionOperationDetail(ProductionOperationDetail productionOperationDetail) {
		this.productionOperationDetail = productionOperationDetail;
	}


	public ProductionOperationException getProductionOperationException() {
		return productionOperationException;
	}


	public void setProductionOperationException(ProductionOperationException productionOperationException) {
		this.productionOperationException = productionOperationException;
	}
	//获取所有单位
	public List<CodeValue> getAllStatu(){
		List<CodeValue> codeValues =null;
		try {
			String [] key={"code","name"};
			String [] value ={"218","异常状态"};
			List <CodeValue>oneList = this.codeValueManager.loadByKeyArray(key, value);
			
			if(oneList!=null&&oneList.size()>0){
				codeValues = this.codeValueManager.loadByKey("parentCV.id", oneList.get(0).getId());
				
				
			}else {
				codeValues =new ArrayList<CodeValue>();
			}
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codeValues;
	}
	public PersonnelFiles getPersion(){
		List<PersonnelFiles> oneFiles=null;
		try {
			oneFiles = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(oneFiles!=null&&oneFiles.size()>0){
			return oneFiles.get(0);
		}else{
		return null;
		}
		
	}
	
	public void saveEventNew(){
		
		
		try {
			//g根据异常的状态触发不同的事件。
			//如果是新提出的异常，则通知给异常节点的负责人
			if( this.productionOperationException.getStatu()!=null&& this.productionOperationException.getStatu().getCode().equals("21801")){
				EventType eventType = null;
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setUserId(this.userManager.getUser().getId()+"");
				
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10039");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventType不存在！");
				}
				
				Map<String, String> map = new HashMap();
				if(this.productionOperationException.getProjectInfoPlan()!=null){
				PersonnelFiles personnelFiles = this.productionOperationException.getProjectInfoPlan().getPersonnelFiles();
				if(personnelFiles!=null){
				List <User> users = this.userManager.loadByKey("code", personnelFiles.getCode());
				if(users!=null&&users.size()>0){
				map.put("users", users.get(0).getId()+"");
				}
				}
				}
				map.put("productionOperationExceptionId", this.productionOperationException.getId() + "");
				map.put("content",
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ","
								+ this.productionOperationException.getFindPersion().getName() + "提交了生产计划异常:"
								+ this.productionOperationException.getName());
				map.put("url", "productionOperation/editProductionOperationException.html?productionOperationException.id=" + this.productionOperationException.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				
				event.setEventType(eventType);
				event.setName(eventType.getName());
				eventNewManager.storeEventNew(event);
				
				//如果是解决中的异常，则通知给异常提出人
			}else if(this.productionOperationException.getStatu()!=null&& this.productionOperationException.getStatu().getCode().equals("21802")){
				
				EventType eventType = null;
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setUserId(this.userManager.getUser().getId() + "");
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10040");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventType不存在！");
				}
				
				Map<String, String> map = new HashMap();
//				if(this.productionOperationException.getProjectInfoPlan()!=null){
//				PersonnelFiles personnelFiles = this.productionOperationException.getProjectInfoPlan().getPersonnelFiles();
//				if(personnelFiles!=null){
//				List <User> users = this.userManager.loadByKey("code", personnelFiles.getCode());
//				if(users!=null&&users.size()>0){
//				map.put("users", users.get(0).getId()+"");
//				}
//				}
//				}
				PersonnelFiles findPersion = this.productionOperationException.getFindPersion();
				
				if(findPersion!=null){
					List <User> users = this.userManager.loadByKey("code", findPersion.getCode());
					if(users!=null&&users.size()>0){
						map.put("users", users.get(0).getId()+"");
					}
					}
				map.put("productionOperationExceptionId", this.productionOperationException.getId() + "");
				map.put("content",
						new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ ","
								+ this.userManager.getUser().getName() + "更新了生产计划异常:"
								+ this.productionOperationException.getName()+"为解决中");
				map.put("url", "productionOperation/editProductionOperationException.html?productionOperationException.id=" + this.productionOperationException.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				
				event.setEventType(eventType);
				event.setName(eventType.getName());
				eventNewManager.storeEventNew(event);
				
				//如果是已解决的异常，则通知给异常提出人
			}else if(this.productionOperationException.getStatu()!=null&& this.productionOperationException.getStatu().getCode().equals("21803")) {
				
				EventType eventType = null;
				EventNew event = new EventNew();
				event.setEffectflag("E");
				event.setUserId(this.userManager.getUser().getId() + "");
				List<EventType> eventTypes = this.eventTypeManager.loadByKey("code", "10041");
				if (eventTypes != null && eventTypes.size() > 0) {
					eventType = eventTypes.get(0);
				} else {
					logger.info("eventType不存在！");
				}
				
				Map<String, String> map = new HashMap();
				PersonnelFiles findPersion = this.productionOperationException.getFindPersion();
				
				if(findPersion!=null){
					List <User> users = this.userManager.loadByKey("code", findPersion.getCode());
					if(users!=null&&users.size()>0){
						map.put("users", users.get(0).getId()+"");
					}
					}
				map.put("productionOperationExceptionId", this.productionOperationException.getId() + "");
				map.put("content",
						new SimpleDateFormat("yyyy-MM-dd").format(new Date())+ ","
								+ this.userManager.getUser().getName() + "更新了生产计划异常:"
								+ this.productionOperationException.getName()+"为已解决");
				map.put("url", "productionOperation/editProductionOperationException.html?productionOperationException.id=" + this.productionOperationException.getId());
				String moreinfo = JSONObject.fromObject(map).toString();
				event.setMoreinfo(moreinfo);
				
				event.setEventType(eventType);
				event.setName(eventType.getName());
				eventNewManager.storeEventNew(event);
				
			}
			
			
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
