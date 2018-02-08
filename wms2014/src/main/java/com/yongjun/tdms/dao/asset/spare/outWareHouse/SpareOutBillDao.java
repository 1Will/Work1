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
package com.yongjun.tdms.dao.asset.spare.outWareHouse;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.spare.SpareInOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;

/**
 * <p>Title: SpareOutBillDao
 * <p>Description: 备件出库数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface SpareOutBillDao {
    List<SpareOutBill> loadAllSpareOutBill(Long[] spareOutBillIds);

    List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId);
    List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId,String outDetailids);
    SpareOutBill loadSpareOutBill(Long spareOutBillId);
	
    void storeSpareOutBill(SpareOutBill spareOutBill);

	void deleteSpareOutBill(SpareOutBill spareOutBill);

	void deleteAllSpareOutBill(Collection<SpareOutBill> spareOutBill);
	
	  /**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOutTotalPrice(String[] param);

	/**
     * 查询旧件列表下所有金额
     * @param param
     * @return
     */
	Double showOldOutTotalPrice(String[] param);

	Double showOldScrapTotalPrice(String[] param);

	Double showOldMaintainTotalPrice(String[] param);
}
