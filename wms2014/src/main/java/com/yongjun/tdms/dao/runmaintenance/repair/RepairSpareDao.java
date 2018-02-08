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

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;

/**
 * <p>Title: RepairItemDao
 * <p>Description: 维修备件明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairSpareDao.java 10069 2008-01-03 06:56:45Z wzou $
 */
public interface RepairSpareDao {
	/**
	 * 根据传入的维修备件单ID集合，获取集合中的维修备件单
	 * 
	 * @param spareIds 维修备件单ID集合
	 * @return List 维修备件单集合
	 */
	List<RepairSpare> loadRepairSpares(Long[] spareIds);
	
	/**
	 * 保存维修备件单
	 * 
	 * @param repairSpare 维修备件单实体
	 * @return
	 */
	@Transactional
	void storeRepairSpare(RepairSpare repairSpare);
	
	/**
	 * 根据传入的维修备件单集合，删除集合中的维修备件单
	 * 
	 * @param repairSpare 维修备件单集合
	 * @return
	 */
	@Transactional
	void deleteAllRepairSpare(Collection<RepairSpare> repairSpare);
	
	/**
	 * 根据传入的维修备件单ID，获取维修备件单
	 * 
	 * @param repairSpareId 维修备件单ID
	 * @return RepairSpare 维修备件单
	 */
	@Transactional
	public RepairSpare loadRepairSpare(Long repairSpareId);
}
