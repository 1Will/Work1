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
package com.yongjun.tdms.service.asset.spare.move;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBillDetail;
/**
 * <p>Title: MovePositionBillDetailManager
 * <p>Description: 库位移动明细业务逻辑接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xshen@yj-technology.com
 * @version $
 */
@Transactional(readOnly = true)
public interface MovePositionBillDetailManager {
	/**
	 * 根据库位移位明细的ids,获取库位移位的集合
	 * @param ids   库位移位的ids
	 * @return list
	 */
	  List<MovePositionBillDetail> loadAllMovePositionBillDtl(Long[] ids);
	  /**
	   * 根据库位移位明细的id,获取库位移位的对象
	   * @param id 库位移位的id
	   * @return MovePositionBillDetail
	   */
	  MovePositionBillDetail loadMovePositionBillDtl(Long id);
	  /**
	   * 根据库位移位明细的对象,保存库位移位的实例
	   * @param movePositionBillDtl
	   */
	  @Transactional
	  void storeMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl);
	  /**
	   * 根据库位移位明细的对象,删除库位移位的实例
	   * @param movePositionBillDtl
	   */
	  @Transactional
	  void deleteMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl);
	  /**
	   * 根据库位移位明细的movePositionBillDtls集合 ,删除库位移位的集合
	   * @param movePositionBillDtls
	   */
	  @Transactional
	  void deleteAllMovePositionBillDtl(Collection<MovePositionBillDetail> movePositionBillDtls,MovePositionBill movePostionBill)throws Exception;
	  /**
	   * 保存从备件台帐明细选择过来的记录
	   * @param movePostionBill
	   * @param addSpareLocationAccountIds
	   */
	  @Transactional
	  void storeSpareLocationToMovePostionDtl(MovePositionBill movePostionBill, String addSpareLocationAccountIds);
	  /**
	   * 保存移位明细
	   * @param allMovePostionBillDtlIds
	   * @param allLocationCodeValue
	   * @param allMovePostionDetailNumber
	   */
	  @Transactional
	  void storeMovePositionBillDtl(String allMovePostionBillDtlIds, String allLocationCodeValue, String allMovePostionDetailNumber);
	  
	  /**
	   * 保存移位明细
	   * @param allMovePostionBillDtlIds
	   * @param allLocationCodeValue
	   * @param allMovePostionDetailNumber
	   * @param warehouseId 移位仓库ID
	   */
	  @Transactional
	  void storeMovePositionBillDtl(String allMovePostionBillDtlIds, String allLocationCodeValue, 
			  String allMovePostionDetailNumber,Long warehouseId);
}
