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
package com.yongjun.tdms.dao.asset.spare.inWareHouse;

import java.util.List;

import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

/**
 * <p>Title: SpareInBillDetailDao
 * <p>Description: 备件入库明细接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface SpareInBillDetailDao {
	
	void storeSpareInBillDtl(SpareInBillDetail spareInBillDtl);
	
	SpareInBillDetail loadSpareInBillDtl(Long spareInBillDtlId);
	
	List<SpareInBillDetail> loadAllSpareInBillDtl(Long[] spareInBillDtlIds);
	
	List<SpareInBillDetail> loadAllSpareInBillDtl();
	
	void deleteSpareInBillDtl(SpareInBillDetail spareInBillDtl);
	
	void deleteAllSpateInBillDtl(List<SpareInBillDetail> list);
	/**
	 * 根据部门ID，库位ID,备件ID 获取明细台帐实体,如果该条件下有多条入库单明细实体,则取最顶一个
	 * @param deptId 部门ID
	 * @param locationId库位ID
	 * @param spareId 备件ID
	 * @return
	 */
	SpareInBillDetail getTop1SpareInDetailByDeptAndLocationAndSpare(Long deptId, Long locationId, Long spareId);
	
	/**
	 * 根据入库明细ID集合获取对应的采购明细ID集合
	 * @param SpareInBillDtlIds
	 */
	public String updPurchaseBillIds(String SpareInBillDtlIds);
	
	/**
	 * 根据传入的入库单明细ID集合，更新申购单、汇总单、采购单的入库项
	 * 
	 * @param SpareInBillDtlIds 入库单明细ID集合
	 * @return
	 */
	public void updStatus(final String SpareInBillDtlIds);
}
