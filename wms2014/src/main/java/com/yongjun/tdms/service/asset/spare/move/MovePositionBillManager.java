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


/**
 * <p>Title: MovePositionBillManager
 * <p>Description: 库位移动业务逻辑接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xshen@yj-technology.com
 * @version $
 */
@Transactional(readOnly = true)
public interface MovePositionBillManager {
	/**
	 * 根据库位移动的ids,获取库移集合
	 * @param ids  库位移动ids
	 * @return list
	 */
	  List<MovePositionBill> loadAllMovePositionBill(Long[] ids);
	  /**
	   * 根据库位移动的id,获取库移对象
	   * @param id   获取库移id
	   * @return   MovePositionBillDetail
	   */
	  MovePositionBill loadMovePositionBill(Long id);
	  /**
	   * 根据库位移动的对象,保存库位移动
	   * @param movePositionBillDtl
	   */
	  @Transactional
	  void storeMovePositionBill(MovePositionBill movePositionBill);
	 /**
	  * 根据库位移动的对象,删除库位移动
	  * @param movePositionBillDtl
	  */
	  @Transactional
	  void deleteMovePositionBill(MovePositionBill movePositionBill);
	  /**
	   * 根据库位移动的movePositionBillDtls集合,删除库位移动
	   * @param movePositionBillDtls
	   */
	  @Transactional
	  void deleteAllMovePositionBill(Collection<MovePositionBill> movePositionBills);
	  @Transactional
	  void disableMovePostionBill(List<MovePositionBill> movePositionBills)throws Exception;
	  @Transactional
	  void enableMovePostionBill(List<MovePositionBill> movePositionBills);
}
