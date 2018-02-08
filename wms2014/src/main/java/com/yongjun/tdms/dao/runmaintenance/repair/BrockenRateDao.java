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

package com.yongjun.tdms.dao.runmaintenance.repair;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;

/**
 * <p>Title: FaultBillDao
 * <p>Description: 故障率访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: BrockenRateDao.java 8929 2007-12-03 09:54:27Z xschen $
 */
public interface BrockenRateDao {
	List<BrockenRate> loadAllBrockenRates(Long [] brockenRateIds);
	
	BrockenRate loadBrockenRate(Long brockenRateId);
}
