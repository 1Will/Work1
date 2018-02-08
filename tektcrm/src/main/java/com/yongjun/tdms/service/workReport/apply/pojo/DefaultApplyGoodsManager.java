package com.yongjun.tdms.service.workReport.apply.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workReport.apply.ApplyGoodsDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
import com.yongjun.tdms.model.workReport.apply.ApplyGoods;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
import com.yongjun.tdms.service.workReport.apply.ApplyGoodsManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultApplyGoodsManager extends BaseManager  implements ApplyGoodsManager{
    private final ApplyGoodsDao applyGoodsDao;
    private final YongJunSequenceManager yongJunSequenceManager;
    private final ActivitFlowManager activitFlowManager;
    private final CodeValueDao codeValueDao;
	public DefaultApplyGoodsManager(ApplyGoodsDao applyGoodsDao,YongJunSequenceManager yongJunSequenceManager,ActivitFlowManager activitFlowManager,CodeValueDao codeValueDao) {
		this.applyGoodsDao = applyGoodsDao;
		this.yongJunSequenceManager=yongJunSequenceManager;
		this.activitFlowManager=activitFlowManager;
		this.codeValueDao=codeValueDao;
	}

	public void storeApplyGoods(ApplyGoods applyGoods) {
		 if (applyGoods.isNew()) {
			 applyGoods.setCode((String) this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_APPLYGOODs));
			}
		 /**
			 * 第一步 保存请假单
			 */
		       
		       if((applyGoods.getIsSaved()!=null&&applyGoods.getIsSaved().equals("1")) || applyGoods.getIsSaved().equals("2")){
		    	   
		    	   List<CodeValue> bussnessCodeList ;
				try {
					bussnessCodeList = this.codeValueDao.loadByKey("code", "02003");
					//预节点待审核状态
					   if(bussnessCodeList!=null&&bussnessCodeList.size()>0){//将当前业务状态更新我审核中
						   applyGoods.setStatus(bussnessCodeList.get(0));
					   }
				} catch (DaoException e) {
					e.printStackTrace();
				}
		    	  
		       }
		       this.applyGoodsDao.storeApplyGoods(applyGoods);
		       
		       /**
		        * 第二步 开启流程  会根据保存还是提交状态做不同的处理 
		        * 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批 
		        */
		       StartActiviti sActiviti = new StartActiviti();
		       sActiviti.setApplyPerson(applyGoods.getApplyPerson());
		       sActiviti.setBussnessId(applyGoods.getId());
		       sActiviti.setIsSaved(applyGoods.getIsSaved());
		       sActiviti.setContent(applyGoods.toString());
		       sActiviti.setCreatedTime(applyGoods.getCreatedTime());
		       sActiviti.setFlow(applyGoods.getFlow());
		       sActiviti.setName("用车申请("+applyGoods.getCode()+")");
		       sActiviti.setLinkHref("/apply/editApplyGoods.html?activitiFLow=activitiFLow&applyGoods.id="+applyGoods.getId());
		       //根据反射获取类名 
		       sActiviti.setBussnessType(applyGoods.getClass().getSimpleName());
		       
		       try {
				this.activitFlowManager.startAtiviti(sActiviti);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void deleteApplyGoods(ApplyGoods applyGoods) {
		this.applyGoodsDao.deleteApplyGoods(applyGoods);
	}

	public void deleteApplyGoods(Collection<ApplyGoods> paramCollection) {
		this.applyGoodsDao.deleteApplyGoods(paramCollection);
	}

	public List<ApplyGoods> loadAllApplyGoods(Long[] paramArrayOfLong) {
		return this.applyGoodsDao.loadAllApplyGoods(paramArrayOfLong);
	}

	public ApplyGoods loadApplyGoods(Long paramLong) {
		return this.applyGoodsDao.loadApplyGoods(paramLong);
	}

	public List<ApplyGoods> loadAllApplyGoods() {
		return this.applyGoodsDao.loadAllApplyGoods();
	}

	public List<ApplyGoods> loadByKey(String paramString, Object paramObject) throws DaoException {
		return this.applyGoodsDao.loadByKey(paramString, paramObject);
	}

	public List<ApplyGoods> loadByKeyArray(String[] paramArrayOfString, Object[] paramArrayOfObject)
			throws DaoException {
		return this.applyGoodsDao.loadByKeyArray(paramArrayOfString, paramArrayOfObject);
	}

	public void disabledAllApplyGoods(List<ApplyGoods> goods) {
		for(ApplyGoods good :goods){
			good.setDisabled(true);
			this.applyGoodsDao.storeApplyGoods(good);
		}
	}

	public void enabledAllApplyGoods(List<ApplyGoods> goods) {
		for(ApplyGoods good :goods){
			good.setDisabled(false);
			this.applyGoodsDao.storeApplyGoods(good);
		}
		
	}

}
