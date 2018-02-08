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
package com.yongjun.tdms.dao.asset.spare.move;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;

/**
 * <p>Title: MovePositionBillDao
 * <p>Description: 移位管理数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:$
 */
public interface MovePositionBillDao {
    List<MovePositionBill> loadAllMovePositionBill(Long[] ids);
    MovePositionBill loadMovePositionBill(Long id);
    void storeMovePositionBill(MovePositionBill movePositionBill);
	void deleteMovePositionBill(MovePositionBill movePositionBill);
	void deleteAllMovePositionBill(Collection<MovePositionBill> movePositionBill);
}
