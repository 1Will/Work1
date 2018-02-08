  /*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.service.prophase.business.tooling.reportmanager;

import java.util.Date;
import java.util.List;

import com.yongjun.tdms.model.prophase.business.tooling.reportmanager.SpareOutBillMonthView;

/**
 * <p>Title: com.yongjun.tdms.service.prophase.business.tooling.reportmanager.SpareOutBillViewManager.java</p>
 * <p>Description: the SpareOutBillViewManager class</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:SpareOutBillViewManager.java 2011-3-23 zzb $</p>
 */
public interface SpareOutBillMonthViewManager {
	public void storeSpareOutView(SpareOutBillMonthView outView);
	
	/**
	 * 时间段加载view
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public List<SpareOutBillMonthView> loadSpareOutBillMonthViewNum(Date startDate,Date endDate);
	
}

