package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.rule;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRuleDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointRuleManager;

/**
 * @author qs
 * @version $Id: EditCheckPointRuleDetailAction.java 6946 2007-09-07 01:11:47Z
 *          qsun $
 */
public class EditCheckPointRuleDetailAction extends PrepareAction {
	private static final long serialVersionUID = -6419768171459701248L;

	private final Log log = LogFactory.getLog(getClass());

	private CheckPointRule rule;

	protected CheckPointRuleDetail detail;

	private final CheckPointRuleManager checkPointRuleManager;

	private final CheckPointRuleDetailManager checkPointRuleDetailManager;

	//private Set values;

	int result;
	public EditCheckPointRuleDetailAction(
			CheckPointRuleManager checkPointRuleManager,
			CheckPointRuleDetailManager checkPointRuleDetailManager) {
		this.checkPointRuleManager = checkPointRuleManager;
		this.checkPointRuleDetailManager = checkPointRuleDetailManager;
	}

	public CheckPointRule getRule() {
		return rule;
	}

	public void setRule(CheckPointRule rule) {
		this.rule = rule;
	}

	public void prepare() throws Exception {
		if (this.rule == null) {
			if (this.hasId("rule.id")) {
				this.rule = this.checkPointRuleManager.loadRule(this
						.getId("rule.id"));
			}
		}

		if (this.detail == null) {
			if (this.hasId("detail.id")) {
				detail = this.checkPointRuleManager
						.loadCheckPointRuleDetail(this.getId("detail.id"));
//				System.out.println("--------------------------------------------------------");
//				System.out.println("detail.id=="+this.getId("detail.id"));
			} else {
				detail = new CheckPointRuleDetail();
//				values = rule.getRuleDetails();         //利用rule对象获得detail的Set集，从而获得detail.SerialNo的最大值。
//				Iterator array = values.iterator();
//				int length = values.toArray().length;
//				for (int i = 0; i < length; i++) {
//					detail = (CheckPointRuleDetail) array.next();
//					if (result < detail.getSerialNo().intValue()) {
//						result = detail.getSerialNo().intValue();
//					}
//					result = result + 1;
//				}
//				detail.setSerialNo((long)result);
			}
		}
		//System.out.println("result======================================"+ result);
	}

//	public String save() {
//		System.out.println("rule.id=" + this.getId("rule.id"));
//		this.rule = this.checkPointRuleManager.loadRule(this
//				.getId("rule.id"));
//		if (this.isDelete()) {
//			return this.delete();
//		}
//
//		if (log.isDebugEnabled()) {
//			log.debug("rule [no,name] is " + rule.getRuleNo() + ","
//					+ rule.getName());
//		}
//
//		boolean isNew = this.detail.isNew();
//
//		if (log.isDebugEnabled()) {
//			log.debug("device id is " + rule.getDevice().getId());
//		}
//		detail.setCheckPointRule(this.rule);
//		
//		checkPointRuleDetailManager.storeRuleDetail(detail);
////		double allFee=0,oldAllFee=0;
////		
////		if(rule.getFee()==null){
////			oldAllFee=0;
////		}else{
////			oldAllFee=rule.getFee();
////		}
////			
////		allFee = oldAllFee+detail.getFee();
////		rule.setFee(allFee);
//		
//		checkPointRuleManager.storeRule(rule);
//		if (isNew) {
//			this.addActionMessage(this.getText(
//					"checkPointRuleDetail.add.success", Arrays
//							.asList(new Object[] { rule.getName() })));
//			return NEW;
//		} else {
//			this.addActionMessage(this.getText(
//					"checkPointRuleDetail.edit.success", Arrays
//							.asList(new Object[] { rule.getName() })));
//			return SUCCESS;
//		}
//	}
//
//	public String delete() {
//		this.addActionMessage(this.getText(
//				"checkPointRuleDetail.invalid.success", Arrays
//						.asList(new Object[] { rule.getName() })));
//		return SUCCESS;
//	}

	public CheckPointRuleDetail getDetail() {
		return detail;
	}

	public void setDetail(CheckPointRuleDetail detail) {
		this.detail = detail;
	}
}
