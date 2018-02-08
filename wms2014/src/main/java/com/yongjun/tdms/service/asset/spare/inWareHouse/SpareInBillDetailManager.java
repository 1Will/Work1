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
package com.yongjun.tdms.service.asset.spare.inWareHouse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

/**
 * <p>Title:SpareInBillDetailManager
 * <p>Description:备件入库明细服务控制接口类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
@Transactional(readOnly = true)
public interface SpareInBillDetailManager {
	/**
	 * 存储备件入库单明细
	 * @param spareInBillDtl
	 */
	@Transactional
	void storeSpareInBillDtl(SpareInBillDetail spareInBillDtl);
	/**
	 * 根据ID加载入库单明细
	 * @param spareInBillDtlId
	 * @return SpareInBillDetail
	 */
	SpareInBillDetail loadSpareInBillDtl(Long spareInBillDtlId);
	/**
	 * 根据ID集合加载入库单明细
	 * @param spareInBillDtlIds
	 * @return list
	 */
	List<SpareInBillDetail> loadAllSpareInBillDtl(Long[] spareInBillDtlIds);
	/**
	 * 删除入库单明细
	 * @param spareInBillDtl
	 */
	@Transactional
	void deleteSpareInBillDtl(SpareInBillDetail spareInBillDtl);
	/**
	 * 删除入库单明细集合
	 * @param list
	 */
	@Transactional
	void deleteAllSpateInBillDtl(List<SpareInBillDetail> list ,SpareInBill spareInBill)throws Exception;
	
	/**
	 *从备件库台帐copy到入库单明细
	 */
	@Transactional
	void storeSpareInBillDtlFromAccount(SpareInBill spareInBill, String addSpareAccountIds);
	
	/**
	 * 从采购单明细copy到入库单明细
	 * @return SUCCESS
	 */
	@Transactional
	void storeSpareInBillDtlFromPurBillDtl(SpareInBill spareInBill, String addPurchaseBillDetailIds);
	/**
	 * 从备件台账copy到入库单明细
	 * @param spareInBill 关联的入库单
	 * @param addSpareIds 备件台账Ids
	 */
	@Transactional
	public void storeSpareInBillFromSpare(SpareInBill spareInBill, String addSpareIds);
	/**
	 * 从出库单明细copy到入库单明细
	 * @param spareInBill 关联的入库单
	 * @param addSpareOutBillDtlIds 出库单明细Ids
	 */
	@Transactional
	public void storeSpareInBillFromSpareOutBillDtl(SpareInBill spareInBill, String addSpareOutBillDtlIds);
	
	@Transactional
	void storeSpareInBillDtl(long roleWarehouseId,String allSpareInBillDtlComment,  String allSpareInBillDtlNumber, String allSpareInBillDtlId,String allSpareInBillDeparement,String allLocationCodeValue,String allSpareInBillDtlTaxPriceValue);
	@Transactional
	void storeSpareInBillDtlForOldToNew(long roleWarehouseId,String allSpareInBillDtlComment, String allSpareInBillDtlName, String allSpareInBillDtlModel, String allSpareInBillDtlNumber, String allSpareInBillDtlId,String allSpareInBillDeparement,String allLocationCodeValue,String allSpareInBillDtlTaxPriceValue,String allDisableSpare);
	@Transactional
	void storeOldSpareInBillDtl(long roleWarehouseId,String allSpareInBillDtlComment, String allSpareInBillDtlNumber, String allSpareInBillDtlId,String allSpareInBillDeparement,String allLocationCodeValue,String allSpareInBillDtlTaxPriceValue,String allDisableSpare);
	//	@Transactional
//	void storeSpareBorrowToSpareInBillDtl(SpareInBill spareInBill,String spareBorrowIds);
//	入库明细保存到出库明细
	void storeOutBillDtlToInBillDtl(SpareInBill spareInBill, String addSpareOutBillIds);
	
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
