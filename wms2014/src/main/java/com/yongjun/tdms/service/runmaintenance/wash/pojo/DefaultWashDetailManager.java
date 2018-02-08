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
package com.yongjun.tdms.service.runmaintenance.wash.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.runmaintenance.wash.WashDetailDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.product.ProductManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashDetailManager;
/**
 * <p>Title: DefaultWashDetailManager
 * <p>Description: 清洗明细业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.runmaintenance.wash.WashDetailManager
 */
public class DefaultWashDetailManager implements WashDetailManager {
	private final WashDetailDao washDetailDao;
	private final DeviceCardManager toolingCardManager;
	private final UserManager userManager;
	private final ProductManager productManager;
	
	public DefaultWashDetailManager(WashDetailDao washDetailDao,
			DeviceCardManager toolingCardManager,
			UserManager userManager,
			ProductManager productManager) {
		this.washDetailDao = washDetailDao;
		this.toolingCardManager = toolingCardManager;
		this.userManager = userManager;
		this.productManager = productManager;
	}
	
	public WashDetail loadWashDetail(Long washDetailId) {
		return this.washDetailDao.loadWashDetail(washDetailId);
	}

	public List<WashDetail> loadAllWashDetails(Long[] washDetailIds) {
		return this.washDetailDao.loadAllWashDetails(washDetailIds);
	}

	public List<WashDetail> loadAllWashDetails() {
		return this.washDetailDao.loadAllWashDetails();
	}

	public void storeWashDetail(WashDetail detail) {
		this.washDetailDao.storeWashDetail(detail);
	}

	public void deleteWashDetail(WashDetail detail) {
		this.washDetailDao.deleteWashDetail(detail);
	}

	public void delteAllWashDetail(Collection<WashDetail> details) {
		this.washDetailDao.delteAllWashDetail(details);
	}

	public void storeWashDetail(Wash washPlan, String toolingIds) {
		String [] toolingId = null;
		if (null != toolingIds) {
			toolingId = toolingIds.split(",");
		}
		for (int i=0; i<toolingId.length; i++) {
			WashDetail washPlanDetail = new WashDetail();
			DeviceCard tooling = this.toolingCardManager.loadDevice(Long.valueOf(toolingId[i]));
			washPlanDetail.setTooling(tooling);
			washPlanDetail.setProductModel(tooling.getProduct());
			washPlanDetail.setPlan(washPlan);
			for (Wash washProc : washPlan.getWashProc()) {
				washPlanDetail.setProc(washProc);
			}
			washPlanDetail.setWashResult(WashDetailResult.UNFINISHED);
			this.washDetailDao.storeWashDetail(washPlanDetail);
		}
		
	}

	public void storeWashDetail(String allWashDetailId, String allPlanWashDate, 
			String allDutyPeople, String allSupervisePeople, 
			String allProductModel, String allComment) {
		String [] washDetailId = null;
		String [] planWashDate = null;
		String [] dutyPeople = null;
		String [] supervisePeople = null;
		String [] productModel = null;
		String [] comment = null;
		
		if (null != allWashDetailId) {
			washDetailId = allWashDetailId.split(",");
		}
		if (null != allPlanWashDate) {
			planWashDate = allPlanWashDate.split(",");
		}
		if (null != allDutyPeople) {
			dutyPeople = allDutyPeople.split(",");
		}
		if (null != allSupervisePeople) {
			supervisePeople = allSupervisePeople.split(",");
		}
		if (null != allProductModel) {
			productModel = allProductModel.split(",");
		}
		if (null != allComment) {
			comment = allComment.split(",");
		}
		updateWashPlanDetailContent(washDetailId, planWashDate,
				dutyPeople, supervisePeople,
				productModel, comment);
		
	}
	private void updateWashPlanDetailContent(String [] washDetailId, String [] planWashDate,
			String [] dutyPeople, String [] supervisePeople,
			String [] productModel, String [] comment) {
		int detailNum = 0;
		
		while (detailNum <washDetailId.length) {
			WashDetail planDetail = this.washDetailDao.loadWashDetail(Long.valueOf(washDetailId[detailNum]));
			
			if (null != planWashDate) {                  //更新计划清洗日期
				for (int i=0; i<planWashDate.length; i=i+2) {
					if (planWashDate[i].equals(washDetailId[detailNum])) {
						planDetail.setPlanWashDate(DateUtil.toDate(planWashDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						planDetail.setPlanWashDate(null);
					}
				}
			} else {
				planDetail.setPlanWashDate(null);
			}
			
			if (null != dutyPeople) {                   //更新负责人
				for (int i=0; i<dutyPeople.length; i=i+2) {
					if (dutyPeople[i].equals(washDetailId[detailNum])) {
						planDetail.setDutyPeople(this.userManager.loadUser(Long.valueOf(dutyPeople[i+1])));
						break;
					} else {
						planDetail.setDutyPeople(null);
					}
				}
			} else {
				planDetail.setDutyPeople(null);
			}
			
			if (null != supervisePeople) {                   //更新监督人
				for (int i=0; i<supervisePeople.length; i=i+2) {
					if (supervisePeople[i].equals(washDetailId[detailNum])) {
						planDetail.setSupervisePeople(this.userManager.loadUser(Long.valueOf(supervisePeople[i+1])));
					    break;
					} else {
						planDetail.setSupervisePeople(null);
					}
				}
			} else {
				planDetail.setSupervisePeople(null);
			}
//			if (null != productModel) {                   //更新产品型号
//				for (int i=0; i<productModel.length; i=i+2) {
//					if (productModel[i].equals(washDetailId[detailNum])) {
//						planDetail.setProductModel((this.productManager.loadProduct(Long.valueOf(productModel[i+1]))));
//					    break;
//					} else {
//						planDetail.setProductModel(null);
//					}
//				}
//			} else {
//				planDetail.setProductModel(null);
//			}
			if (null != comment) {                   //更新备注
				for (int i=0; i<comment.length; i=i+2) {
					if (comment[i].equals(washDetailId[detailNum])) {
						planDetail.setComment(comment[i+1]);
						break;
					} else {
						planDetail.setComment(null);
					}
				}
			} else {
				planDetail.setComment(null);
			}
			planDetail.setProcWashDate(planDetail.getPlanWashDate());
			this.washDetailDao.storeWashDetail(planDetail);
			detailNum ++;
		}
	}

	public void storeWashDetail(String allWashDetailId, String allProcWashDate, String allWashResult) {
		String [] washProcDetailId = null;
		String [] procWashDate = null;
		String [] washResult = null;
		
		if (null != allWashDetailId) {
			washProcDetailId = allWashDetailId.split(",");
		}
		if (null != allProcWashDate) {
			procWashDate = allProcWashDate.split(",");
		}
		if (null != allWashResult) {
			washResult = allWashResult.split(",");
		}
		updateWashProcDetailContent(washProcDetailId, procWashDate, washResult);
	}
	
	private void updateWashProcDetailContent(String [] washProcDetailId, String [] procWashDate, String [] washResult) {
		int procDetailIdNum = 0;
		
		while (procDetailIdNum<washProcDetailId.length) {
			WashDetail procDetail = this.washDetailDao.loadWashDetail(Long.valueOf(washProcDetailId[procDetailIdNum]));
			
			if ( null != procWashDate) {                     //更新实际清洗日期
				for (int i=0; i<procWashDate.length; i=i+2) {
					if (procWashDate[i].equals(washProcDetailId[procDetailIdNum])) {
						procDetail.setProcWashDate(DateUtil.toDate(procWashDate[i+1], "yyyy-MM-dd"));
						break;
					} else {
						procDetail.setProcWashDate(null);
					}
				}
			} else {
				procDetail.setProcWashDate(null);
			}
			
			if (null != washResult) {
				for (int i=0; i<washResult.length; i=i+2) {
					if (washResult[i].equals(washProcDetailId[procDetailIdNum])) {
						if (washResult[i+1].equals(WashDetailResult.UNFINISHED.toString())) {
							procDetail.setWashResult(WashDetailResult.UNFINISHED);
						} else {
							procDetail.setWashResult(WashDetailResult.FINISHED);
						}
						break;
					} else {
						procDetail.setWashResult(null);
					}
				}
			} else {
				procDetail.setWashResult(null);
			}
			this.washDetailDao.storeWashDetail(procDetail);
			procDetailIdNum ++;
		}
	}

	public void delteAllWashDetailPlans(Collection<WashDetail> details) throws Exception{
		for (WashDetail planDetail : details) {
			if (planDetail.getWashResult().equals(WashDetailResult.FINISHED)) {
				throw new Exception();
			}
		}
		this.washDetailDao.delteAllWashDetail(details);
	}

}
