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
package com.yongjun.tdms.dao.prophase.business;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;

/**
 * @author qs
 * @version $Id: PurchaseBillDao.java 11279 2008-03-12 01:12:13Z mwei $
 */
public interface AcceptBillDetailDao {
	/**
	 * 根据传入的验收单明细ID，获取验收单明细
	 * 
	 * @param AcceptBillDetailId 验收单明细ID
	 * @return AcceptBillDetail 验收单明细
	 */
	AcceptBillDetail loadAcceptBillDetail(Long AcceptBillDetailId);
	/**
	 * 根据传入的验收单明细ID集合，获取验收单集合
	 * 
	 * @param AcceptBillDetaiIds 验收单明细ID集合
	 * @return List 采购单集合
	 */
	List<AcceptBillDetail> loadAcceptBillDetais(Long[] AcceptBillDetaiIds);

	/**
	 * 保存验收明细单实体
	 * 
	 * @param AcceptBillDetail 验收单明细
	 * @return
	 */
	void storeAcceptBillDetail(AcceptBillDetail acceptBillDetai);
	/**
	 * 删除验收明细实体
	 * 
	 * @param acceptBillDetai 验收明细单实体
	 * @return
	 */
	void deleteAcceptBillDetail(AcceptBillDetail acceptBillDetail);
	/**
	 * 根据传入的采购单集合，删除集合中的采购单
	 * 
	 * @param AcceptBillDetails 验收单集合
	 * @return
	 */
	void deleteAllAcceptBillDetails(Collection<AcceptBillDetail> AcceptBillDetails);
	/**
	 * 保存工装下的工装制作明细
	 * @param acceptBillDetai
	 */
	void storeToolingAcceptBillToolingMakeDetail(AcceptBillDetail acceptBillDetai);
	/**
	 * 删除所有的工装制作明细
	 * @param AcceptBillDetails
	 */
	void deleteToolingAllAcceptBillMakeDetails(Collection<AcceptBillDetail> AcceptBillDetails);
}
