package com.yongjun.tdms.service.workspace.overTimeBill.pojo;

import com.yongjun.pluto.dao.codevalue.CodeValueDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.workspace.overTimeBill.OverTimeBillDao;
import com.yongjun.tdms.model.activitiFlow.StartActiviti;
import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
import com.yongjun.tdms.service.activitiFlow.ActivitFlowManager;
import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

import java.util.Collection;
import java.util.List;

public class DefaultOverTimeBillManager extends BaseManager implements OverTimeBillManager {
	private final OverTimeBillDao overTimeBillDao;
	private final YongJunSequenceManager yongJunSequenceManager;
	private final ActivitFlowManager activitFlowManager;
	private final CodeValueDao codeValueDao;

	public DefaultOverTimeBillManager(OverTimeBillDao overTimeBillDao, YongJunSequenceManager yongJunSequenceManager,
			ActivitFlowManager activitFlowManager,CodeValueDao codeValueDao) {
		this.overTimeBillDao = overTimeBillDao;
		this.yongJunSequenceManager = yongJunSequenceManager;
		this.activitFlowManager = activitFlowManager;
		this.codeValueDao = codeValueDao;
	}

	public void deleteAllOverTimeBill(Collection<OverTimeBill> otbs) {
		this.overTimeBillDao.deleteAllOverTimeBill(otbs);
	}

	public void deleteOverTimeBill(OverTimeBill otb) {
		this.overTimeBillDao.deleteOverTimeBill(otb);
	}

	public List<OverTimeBill> loadAllOverTimeBill(Long[] otbIds) {
		return this.overTimeBillDao.loadAllOverTimeBill(otbIds);
	}

	public List<OverTimeBill> loadAllOverTimeBill() {
		return this.overTimeBillDao.loadAllOverTimeBill();
	}

	public List<OverTimeBill> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.overTimeBillDao.loadByKey(keyName, keyValue);
	}

	public List<OverTimeBill> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.overTimeBillDao.loadByKeyArray(keyNames, keyValues);
	}

	public OverTimeBill loadOverTimeBill(Long otbId) {
		return this.overTimeBillDao.loadOverTimeBill(otbId);
	}

	public void storeOverTimeBill(OverTimeBill otb) throws DaoException {
		/**
		 * 第一步 保存请假单
		 */
		if (otb.isNew()) {
			otb.setCode(
					(String) this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_OVERTIMEBILL));
		}
		if ((otb.getIsSaved() != null && otb.getIsSaved().equals("1")) || otb.getIsSaved().equals("2")) {

			List<CodeValue> bussnessCodeList = this.codeValueDao.loadByKey("code", "02003");// 预节点待审核状态
			if (bussnessCodeList != null && bussnessCodeList.size() > 0) {// 将当前业务状态更新我审核中
				otb.setStatus(bussnessCodeList.get(0));
			}

		}
		this.overTimeBillDao.storeOverTimeBill(otb);

		/**
		 * 第二步 开启流程 会根据保存还是提交状态做不同的处理 如果是保存 只会增加预节点，如果是提交则会启动流程到第一位审批人 进行审批
		 */
		StartActiviti sActiviti = new StartActiviti();
		sActiviti.setApplyPerson(otb.getApplyPerson());
		sActiviti.setBussnessId(otb.getId());
		sActiviti.setIsSaved(otb.getIsSaved());
		sActiviti.setContent(otb.toString());
		sActiviti.setCreatedTime(otb.getCreatedTime());
		sActiviti.setFlow(otb.getFlow());
		sActiviti.setName("加班单("+otb.getCode()+")");
		sActiviti.setLinkHref("/overTimeBill/editOverTimeBillAction.html?activitiFLow=activitiFLow&overTimeBill.id=" + otb.getId());
		// 根据反射获取类名
		sActiviti.setBussnessType(otb.getClass().getSimpleName());

		try {
			this.activitFlowManager.startAtiviti(sActiviti);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disabledAllOverTimeBills(List<OverTimeBill> otbs) {
		for (OverTimeBill o : otbs) {
			o.setDisabled(true);
			this.overTimeBillDao.storeOverTimeBill(o);
		}
	}

	public void enabledAllOverTimeBills(List<OverTimeBill> otbs) {
		for (OverTimeBill o : otbs) {
			o.setDisabled(false);
			this.overTimeBillDao.storeOverTimeBill(o);
		}
	}

	public String getMaxPFCode(String code, Long orgId) {
		return this.overTimeBillDao.getMaxPFCode(code, orgId);
	}
}
