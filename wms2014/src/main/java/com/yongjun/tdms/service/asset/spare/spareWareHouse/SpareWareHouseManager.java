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
package com.yongjun.tdms.service.asset.spare.spareWareHouse;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.spareWareHouse.SpareWareHouse;

/**
 * <p>Title: SpareWareHouseManager
 * <p>Description: 备件库总台帐业务逻辑操作接口类</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
@Transactional(readOnly=true)
public interface SpareWareHouseManager {
	SpareWareHouse loadSpareWareHouse(Long spareWareHouseId);
	/**
	 * 根据备件和仓库ID获取备件库总台帐的实体
	 * @param spareId
	 * @param wareHouseId
	 * @return
	 */
	SpareWareHouse loadSpareWareHouse(Long spareId,Long wareHouseId);
	void storeSpareWareHouse(SpareWareHouse spareWareHouse);
	/**
	 * 根据备件ID、仓库ID，判断备件库台帐记录是否存在，
	 * 如果存在，则更新该记录的总库存
	 * 如果不存在，则新增记录
	 * @param spare
	 * @param wareHouse
	 * @param stocks
	 */
	void storeOrUpdateSpareWareHouse(Spare spare, Warehouse wareHouse,Long stocks);
	/**
	 * 根据备件ID、仓库ID，判断备件库台帐记录是否存在，
	 * 如果存在，则更新该记录的总库存
	 * 如果不存在，则新增记录
	 * @param spare
	 * @param wareHouse
	 * @param stocks
	 */
	void storeOrUpdateSpareWareHouseHaveDisabe(Spare spare, Warehouse wareHouse,Long stocks);
	/**
	 * 根据备件ID、仓库ID，判断备件库台帐记录是否存在，
	 * 如果存在，则更新该记录的总库存
	 * 如果不存在，则新增记录
	 * @param spare
	 * @param wareHouse
	 * @param stocks
	 */
	void storeOrUpdateOldSpareWareHouse(Spare spare, Warehouse wareHouse,Long stocks);
	/**
	 * 根据备件ID、仓库ID，判断备件库台帐记录是否存在，
	 * 如果存在，则更新该记录的总库存
	 * 如果不存在，则新增记录
	 * @param spare
	 * @param wareHouse
	 * @param stocks
	 */
	void storeOrUpdateOldSpareWareHouseHaveDisabe(String isDisable,Spare spare, Warehouse wareHouse,Long stocks);
	
	
	/**
	 * 根据备件库总台帐ID,保存安全库存
	 * @param allSpareWareHouseIds
	 * @param allSpareWareHouseMinStocks
	 */
	void storeSpareWareHouse(String allSpareWareHouseIds,String allSpareWareHouseMinStocks);

}
