package com.yongjun.tdms.service.workReport.apply.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.apply.ApplyCarDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
import com.yongjun.tdms.model.workReport.apply.ApplyCar;
import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
import com.yongjun.tdms.service.workReport.apply.ApplyCarManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultApplyCarManager extends BaseManager  implements ApplyCarManager
{
	private final ApplyCarDao applyCarDao;
	private final YongJunSequenceManager yongJunSequenceManager;
	private final ActivitFlowManager activitFlowManager;
    private final CodeValueDao codeValueDao;
	public DefaultApplyCarManager(ApplyCarDao applyCarDao,YongJunSequenceManager yongJunSequenceManager,ActivitFlowManager activitFlowManager,CodeValueDao codeValueDao) {
		this.applyCarDao = applyCarDao;
		this.yongJunSequenceManager=yongJunSequenceManager;
		this.activitFlowManager=activitFlowManager;
		this.codeValueDao=codeValueDao;
	}

	public void storeApplyCar(ApplyCar applyCar) {
		/**
		 * 第一步 保存请假单
		 */
	       
	       if((applyCar.getIsSaved()!=null&&applyCar.getIsSaved().equals("1")) || applyCar.getIsSaved().equals("2")){
	    	   
	    	   List<CodeValue> bussnessCodeList = null;
			try {
				bussnessCodeList = this.codeValueDao.loadByKey("code", "02003");
			} catch (DaoException e) {
				e.printStackTrace();
			}
			//预节点待审核状态
		   if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
			   applyCar.setStatus(bussnessCodeList.get(0));
		   }
	    	  
	       }
	       if (applyCar.isNew()) {
				 applyCar.setCode((String) this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_APPLYCAR));
				}
	       this.applyCarDao.storeApplyCar(applyCar);
	       
	       /**
	        * 第二步 开启流程  会根据保存还是提交状态做不同的处理 
	        * 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批 
	        */
	       StartActiviti sActiviti = new StartActiviti();
	       sActiviti.setApplyPerson(applyCar.getApplyPerson());
	       sActiviti.setBussnessId(applyCar.getId());
	       sActiviti.setIsSaved(applyCar.getIsSaved());
	       sActiviti.setContent(applyCar.toString());
	       sActiviti.setCreatedTime(applyCar.getCreatedTime());
	       sActiviti.setFlow(applyCar.getFlow());
	       sActiviti.setName("用车申请("+applyCar.getCode()+")");
	       sActiviti.setLinkHref("/apply/editApplyCar.html?activitiFLow=activitiFLow&applyCar.id="+applyCar.getId());
	       //根据反射获取类名 
	       sActiviti.setBussnessType(applyCar.getClass().getSimpleName());
	       
	       try {
			this.activitFlowManager.startAtiviti(sActiviti);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
	}

	public void deleteApplyCar(ApplyCar applyCar) {
		this.applyCarDao.deleteApplyCar(applyCar);
	}

	public void deleteApplyCar(Collection<ApplyCar> paramCollection) {
		this.applyCarDao.deleteApplyCar(paramCollection);
	}

	public List<ApplyCar> loadAllApplyCar(Long[] paramArrayOfLong) {
		return this.applyCarDao.loadAllApplyCar(paramArrayOfLong);
	}

	public ApplyCar loadApplyCar(Long paramLong) {
		return this.applyCarDao.loadApplyCar(paramLong);
	}

	public List<ApplyCar> loadAllApplyCar() {
		return this.applyCarDao.loadAllApplyCar();
	}

	public List<ApplyCar> loadByKey(String paramString, Object paramObject) throws DaoException {
		return this.applyCarDao.loadByKey(paramString, paramObject);
	}

	public List<ApplyCar> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject) throws DaoException {
		return this.applyCarDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

	public void disabledAllApplyCars(List<ApplyCar> cars) {
	 for (ApplyCar a : cars) {
		    a.setDisabled(true);
		    this.applyCarDao.storeApplyCar(a);
		 }
	}

	public void enabledAllApplyCars(List<ApplyCar> cars) {
		 for (ApplyCar a : cars) {
			    a.setDisabled(false);
			    this.applyCarDao.storeApplyCar(a);
			 }
	}

}
