/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.runmaintenance.repair.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.repair.RepairItemDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairRuleManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairItemManager;

/**
 * <p>Title: DefaultRepairItemManager
 * <p>Description: 预防性维修计划维修详细业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultRepairItemManager.java 11172 2008-03-03 01:47:39Z zbzhang $
 * @see com.yongjun.tdms.service.runmaintenance.repair.RepairItemManager
 */
public class DefaultRepairItemManager implements RepairItemManager{
	private final RepairItemDao repairItemDao;
	//private final PreRepairPlanDetailManager preRepairPlanDetailManager; 
	private final UserManager userManager;
	private final PreRepairRuleManager preRepairRuleManager;

	public DefaultRepairItemManager(RepairItemDao repairItemDao,
			UserManager userManager,
			PreRepairRuleManager preRepairRuleManager) {
		this.repairItemDao = repairItemDao;
		this.userManager = userManager;
		this.preRepairRuleManager = preRepairRuleManager;
	}

	public RepairItem loadRepairItem(Long repairItemId) {
		return this.repairItemDao.loadRepairItem(repairItemId);
	}

	public void storeRepairItem(RepairItem repairItem) {
		setRepairItemProcDefaultValue(repairItem);              //设置默认实施的值
		this.repairItemDao.storeRepairItem(repairItem);		
		
		//updatePreRepairlPlanDetail(repairItem);
	}
	private void updatePreRepairlPlanDetail(RepairItem repairItem) {
		PreRepairPlanDetail detail = repairItem.getPreRepairDetail();
		if (null == detail) {
			return ;
		}
		//updatePreRepairPlanDetailPositionAndContent(detail);
		
	}

	public void deleteAllRepairItem(Collection<RepairItem> RepairItems) {
		this.repairItemDao.deleteAllRepairItem(RepairItems);
	}

	public List<RepairItem> loadAllRepairItems(Long[] itemIds) {
		return this.repairItemDao.loadAllRepairItems(itemIds);
	}
	
	public void resetRepairItem(PreRepairPlanDetail detail) {
		for (RepairItem item : detail.getRepairItems()) {
			item.setComment(null);
			this.storeRepairItem(item);
		}
	}

//	public void deleteAllRepairItem(PreRepairPlanDetail detail, Collection<RepairItem> repairItems) {
//		this.deleteAllRepairItem(repairItems);
//		detail.getRepairItems().removeAll(repairItems);
//		updatePreRepairlPlanDetail(detail);
//		
//	}
//	
//	private void updatePreRepairlPlanDetail(PreRepairPlanDetail detail) {
//		if (null == detail.getRepairItems()) {
//			detail.setPosition(null);
//			detail.setContent(null);
//			preRepairPlanDetailManager.storePreRepairPlanDetail(detail);
//			return ;
//		}
//		updatePreRepairPlanDetailPositionAndContent(detail);
//	}
//	/**
//	 * 更新明细的维修部位，维修内容字段
//	 * @param detail 预防性维修明细
//	 */
//	private void updatePreRepairPlanDetailPositionAndContent(PreRepairPlanDetail detail) {
//		StringBuffer pos = new StringBuffer();
//		StringBuffer content = new StringBuffer();
//		for (RepairItem item : detail.getRepairItems()) {
//			if (StringUtils.isNotEmpty(item.getPosition())) {
//				pos.append(item.getPosition()).append(",");
//			}
//			if (StringUtils.isNotEmpty(item.getContent())) {
//				content.append(item.getContent()).append(",");
//			}
//		}
//		
//		pos.setCharAt(pos.lastIndexOf(","), ' ');
//		content.setCharAt(content.lastIndexOf(","), ' ');
//		
//		detail.setPosition(pos.toString());
//		detail.setContent(content.toString());
//		/**
//		 * 更新明细的维修部位，维修内容字段
//		 */
//		preRepairPlanDetailManager.storePreRepairPlanDetail(detail);	
//	}

	public void storeRepairItem(String allRepairItemId, String allProcExecPeople, 
			String allRepairItemProcCompleteDate, String allRepairItemComment) {
		String [] repairItemId = null;
		String [] procExecPeople = null;
		String [] procCompleteDate = null;
		String [] comment = null;
		int repairItemNum = 0;      //计算维修详细的个数
		if (null != allRepairItemId) {
			repairItemId = allRepairItemId.split(",");
		}
		if (null != allProcExecPeople) {
			procExecPeople = allProcExecPeople.split(",");
		}
		if (null != allRepairItemProcCompleteDate) {
			procCompleteDate = allRepairItemProcCompleteDate.split(",");
		}
		if (null != allRepairItemComment) {
			comment = allRepairItemComment.split(",");
		}
		
		while ((null != repairItemId) && (repairItemNum<repairItemId.length)) {
			RepairItem repairItem = this.repairItemDao.loadRepairItem(Long.valueOf(repairItemId[repairItemNum]));
			
			if (null != procExecPeople ) {       //设置维修详细中的实际执行人
				for (int i=0; i<procExecPeople.length; i=i+2) {     
					if (procExecPeople[i].equals(repairItemId[repairItemNum])) {
						//repairItem.setProcExecPeople(this.userManager.loadUser(Long.valueOf(procExecPeople[i+1])));
					    repairItem.setProcExecPeopleString(procExecPeople[i+1]);
						break;
					} else {
						repairItem.setProcExecPeople(null);
					}
				}
			} else {
				repairItem.setProcExecPeople(null);
			}
			
			if (null != procCompleteDate) {             //设置维修详细中的实际完成时间
				for (int i=0; i<procCompleteDate.length; i=i+2) {
					if (procCompleteDate[i].equals(repairItemId[repairItemNum])) {
						repairItem.setProcCompleteDate(DateUtil.toDate(procCompleteDate[i+1], "yyyy-MM-dd"));
						//如果该维修明细是从预防性维修标准中来的，则置预防性维修标准中的上次维修日期为实际完成日期
						if (null != repairItem.getPreRepairRule()) {
							PreRepairRule rule = repairItem.getPreRepairRule();
							rule.setLastRepairDate(repairItem.getProcCompleteDate());
							this.preRepairRuleManager.storePreRepairRule(rule);
						}
						break;
					} else {
						repairItem.setProcCompleteDate(null);
					}
				}
			} else {
				repairItem.setProcCompleteDate(null);
			}
			
			if (null != comment) {                   //设置维修详细中的备注
				for (int i=0; i<comment.length; i=i+2) {
					if (comment[i].equals(repairItemId[repairItemNum])) {
						repairItem.setComment(comment[i+1]);
						break;
					} else {
						repairItem.setComment(null);
					}
				}
			} else {
				repairItem.setComment(null);
			}
			repairItemNum++;
			this.repairItemDao.storeRepairItem(repairItem);
		}
		
	}

	public void resetRepairItem(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail) {
		for (RepairItem oldRepairItem : oldDetail.getRepairItems()) {
			RepairItem newRepairItem = new RepairItem();                  //新的维修详细
			newRepairItem.setPreRepairDetail(newDetail);                  //设置新维修详细的关联的维修计划明细
			copyRepairItem(newRepairItem, oldRepairItem);                 //拷贝老的维修详细到新的维修详细
			this.repairItemDao.storeRepairItem(newRepairItem);
			newDetail.getRepairItems().add(newRepairItem);
		}		
	}
	
     //	拷贝老的维修详细到新的维修详细
	private void copyRepairItem(RepairItem newRepairItem, RepairItem oldRepairItem) {
		newRepairItem.setPosition(oldRepairItem.getPosition());                    //拷贝维修部为
		newRepairItem.setContent(oldRepairItem.getContent());                      //拷贝工作内容
		newRepairItem.setAimRequire(oldRepairItem.getAimRequire());                //拷贝目标要求
		newRepairItem.setPlanCompleteDate(oldRepairItem.getPlanCompleteDate());    //拷贝计划完成时间
		newRepairItem.setExecPeople(oldRepairItem.getExecPeople());                //拷贝计划执行人
		setRepairItemProcDefaultValue(newRepairItem);                              //设置维修详细中实际的默认值
	}
	
	//设置维修详细中实际的默认值
	private void setRepairItemProcDefaultValue(RepairItem repairItem) {
		if (null == repairItem.getProcExecPeople()) {
			repairItem.setProcExecPeople(repairItem.getExecPeople());             //设置实际执行人	
		}
		if (null == repairItem.getProcCompleteDate()) {
			repairItem.setProcCompleteDate(repairItem.getPlanCompleteDate());     //设置实际完成时间	
		}
		
		
	}
	
}
