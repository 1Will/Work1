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
package com.yongjun.tdms.service.asset.spare;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.SpareInOutHistory;

/**
 * @author qs
 * @version $Id: SpareInOutHistoryManager.java 8647 2007-11-26 02:42:25Z qsun $
 */
@Transactional(readOnly=true)
public interface SpareInOutHistoryManager {
	String getMaxSpareHistoryById(Long spareId,boolean Flag);
	
	SpareInOutHistory loadSpareInOutHistory(Long spareInOutHistoryIds);
	
	@Transactional
	void storeSpareInOutHistory(SpareInOutHistory spareInOutHistory,Long number);
}
