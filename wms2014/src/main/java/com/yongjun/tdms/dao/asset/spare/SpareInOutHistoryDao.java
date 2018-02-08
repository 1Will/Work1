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
package com.yongjun.tdms.dao.asset.spare;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.asset.spare.SpareInOutHistory;

/**
 * @author qs
 * @version $Id: SpareInOutHistoryDao.java 9315 2007-12-13 11:49:50Z mwei $
 */
public interface SpareInOutHistoryDao {
	List<SpareInOutHistory> loadAllSpareInOutHistory(Long[] spareInOutHistoryIds);

	SpareInOutHistory loadSpareInOutHistory(Long spareInOutHistoryId);
	
    void storeSpareInOutHistory(SpareInOutHistory spareInOutHistory);

	void deleteSpareInOutHistory(SpareInOutHistory spareInOutHistory);

	void deleteAllSpareInOutHistory(Collection<SpareInOutHistory> spareInOutHistory);
	
	String getTotalSpareNumberBySpare(Long sapreId, boolean flag);
	
	String getMaxSpareHistoryById( Long spareId, boolean Flag);
}
