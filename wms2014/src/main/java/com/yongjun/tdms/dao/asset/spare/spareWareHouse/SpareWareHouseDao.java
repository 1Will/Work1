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
package com.yongjun.tdms.dao.asset.spare.spareWareHouse;

import com.yongjun.tdms.model.asset.spare.spareWareHouse.SpareWareHouse;

/**
 * <p>Title: SpareLocationDao
 * <p>Description: 备件库总台帐数据库访问控制接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public interface SpareWareHouseDao {
	
	SpareWareHouse loadSpareWareHouse(Long spareWareHouseId);
	/**
	 * 根据备件和仓库ID获取备件库总台帐的实体
	 * @param spareId
	 * @param wareHouseId
	 * @return
	 */
	SpareWareHouse loadSpareWareHouse(Long spareId,Long wareHouseId);
	void storeSpareWareHouse(SpareWareHouse spareWareHouse);
	

}
