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
package com.yongjun.tdms.service.year.device.runmaintainPlan.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.PivotalSpareDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PivotalSpare;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.PivotalSpareManager;

/**
 * <p>Title: DefaultPivotalSpareManager
 * <p>Description: 运维计划的关键备件业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.year.device.runmaintainPlan.PivotalSpareManager
 * @version $Id:$
 */
public class DefaultPivotalSpareManager extends CalculateFeeManager implements PivotalSpareManager {

	private final PivotalSpareDao pivotalSpareDao;
	private final SpareDao spareDao;
	
	public DefaultPivotalSpareManager(PivotalSpareDao pivotalSpareDao,
			 SpareDao spareDao) {
		this.pivotalSpareDao = pivotalSpareDao;
		this.spareDao = spareDao;
	}
	public PivotalSpare loadPivotalSpare(Long pivotalSpareId) {
		return this.pivotalSpareDao.loadPivotalSpare(pivotalSpareId);
	}

	public List<PivotalSpare> loadAllPivotalSpares(Long[] pivotalSpareIds) {
		return this.pivotalSpareDao.loadAllPivotalSpares(pivotalSpareIds);
	}

	public List<PivotalSpare> loadAllPivotalSpares() {
		return this.pivotalSpareDao.loadAllPivotalSpares();
	}

	public void storePivotalSpare(PivotalSpare pivotalSpare) {
		this.pivotalSpareDao.storePivotalSpare(pivotalSpare);
	}

	public void deletePivotalSpare(PivotalSpare pivotalSpare) {
		this.pivotalSpareDao.deletePivotalSpare(pivotalSpare);
	}

	public void deleteAllPivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail, Collection<PivotalSpare> pivotalSpares) {
		this.pivotalSpareDao.deleteAllPivotalSpares(pivotalSpares);
		for (PivotalSpare pivotalSpare : pivotalSpares) {
			runmaintainPlanDetail.getPivotalSpares().remove(pivotalSpare);
		}
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculatePivotalSpareFee(runmaintainPlanDetail);
	}
	
	public void storePivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail, String newAddSpareIds) {
		String [] addSpareId  = null;
		if (null != newAddSpareIds) {
			//用","分割备件ID字符串
			addSpareId = newAddSpareIds.split(",");
		}
		//创建新的关键备件
		createPivotalSpares(runmaintainPlanDetail, addSpareId);
	}
	//创建新的关键备件
	private void createPivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail,String [] addSpareId) {
		for (int i=0; i<addSpareId.length; i++) {
			PivotalSpare pivotalSpare = new PivotalSpare();
			//设置关键备件关联的备件
			pivotalSpare.setSpare(this.spareDao.loasSpare(Long.valueOf(addSpareId[i])));
			//设置关联的运维计划明细
			pivotalSpare.setRunmaintainPlanDetail(runmaintainPlanDetail);
			this.pivotalSpareDao.storePivotalSpare(pivotalSpare);
		}
	}
	public void storePivotalSpares(RunmaintainPlanDetail runmaintainPlanDetail, String allPivotalSpareIds, String allUsedNums, String allComments) {
		String [] pivotalSpareId = null;       //存放关键备件的id
		String [] usedNum = null;              //存放使用数量
		String [] comment = null;              //存放备注
		if (null != allPivotalSpareIds) {
			pivotalSpareId = allPivotalSpareIds.split(",");
		}
		if (null != allUsedNums) {
			usedNum = allUsedNums.split(",");
		}
		if (null != allComments) {
			comment = allComments.split(",");
		}
		//更新关键备件的使用数量和备注
		updatePivotalSpares(pivotalSpareId, usedNum, comment);
		//更新与其相关联的运维计划以及运维计划明细的费用
		this.calculatePivotalSpareFee(runmaintainPlanDetail);
	}
	
	//更新关键备件的使用数量和备注
	private void updatePivotalSpares(String [] pivotalSpareId, String [] usedNum, String [] comment) {
		int count = 0;              //计算关键备件的个数
		
		while (count < pivotalSpareId.length) {
			PivotalSpare pivotalSpare = this.pivotalSpareDao.loadPivotalSpare(Long.valueOf(pivotalSpareId[count]));
			if (null != usedNum) {          //设置使用数量
				for (int i=0; i<usedNum.length; i=i+2) {
					if (usedNum[i].equals(pivotalSpareId[count])) {
						pivotalSpare.setUsedNum(Integer.valueOf(usedNum[i+1]));
						//计算备件的总价
						calculatePivotalSpareAllPrice(pivotalSpare);
						break;
					} else {
						pivotalSpare.setUsedNum(null);
					}
				}
			} else {
				pivotalSpare.setUsedNum(null);
			}
			if (null != comment) {         //设置备注
				for (int i=0; i<comment.length; i=i+2) {
					if (comment[i].equals(pivotalSpareId[count])) {
						pivotalSpare.setComment(comment[i+1]);
						break;
					} else {
						pivotalSpare.setComment(null);
					}
				}
			} else {
				pivotalSpare.setComment(null);
			}
			this.pivotalSpareDao.storePivotalSpare(pivotalSpare);
			count++;
		}
	}
	
	//计算备件的总价(备件单价*使用数量)
	private void calculatePivotalSpareAllPrice(PivotalSpare pivotalSpare) {
		if (null != pivotalSpare.getSpare()) {
			pivotalSpare.setAllFee(pivotalSpare.getSpare().getUnitPrice().doubleValue()*pivotalSpare.getUsedNum().doubleValue());
		}
	}
	

}
