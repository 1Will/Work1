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

import com.yongjun.tdms.model.prophase.business.AcceptBill;

/**
 * @author xschen
 * @version $Id: AcceptBillDao.java 11279 2008-03-15 01:12:13Z xschen $
 */

public interface AcceptBillDao {
	/**
	 * 根据传入的验收单ID，获取验收单
	 * 
	 * @param AcceptBillId 验收单ID
	 * @return AcceptBill 验收单
	 */
	AcceptBill loadAcceptBill(Long id);
	/**
	 *  根据传入的验收单的Ids集合 获得验收单集合
	 * @param AcceptBillIds
	 * @return List
	 */
    List<AcceptBill> loadAcceptBills(Long[] AcceptBillIds);
    /**
     * 根据传入的验收单的对象 保存验收单实体
     * @param acceptBill
     */
	void storeAcceptBill(AcceptBill acceptBill);
	/**
	 * 根据传入的验收单的对象 删除验收单对象
	 * @param acceptBill
	 */
	void deleteAcceptBill(AcceptBill acceptBill);
	/**
	 * 根据传入的验收单集合  删除所有的验收单
	 * @param acceptBillIds
	 */
	void deleteAllAcceptBill(Collection<AcceptBill> acceptBillIds);
}
