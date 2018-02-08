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
package com.yongjun.tdms.dao.runmaintenance.fault;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;


/**
 * <p>Title: FaultBillDao
 * <p>Description: 故障单数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: FaultBillDao.java 8929 2007-12-03 09:54:27Z zbzhang $
 */
public interface FaultBillDao {
	/**
	 * 根据传入的故障单ID，获取故障单
	 * 
	 * @param faultBillId 故障单ID
	 * @return FaultBill 故障单
	 */
	FaultBill loadFaultBill(Long faultBillId);
	
	/**
	 * 根据传入的故障单ID集合，获取集合中的故障单
	 * 
	 * @param faultBillIds 故障单ID集合
	 * @return List 故障单集合
	 */
	List<FaultBill> loadAllFaultBills(Long [] faultBillIds);
	
	/**
	 * 获取集合中的故障单
	 * 
	 * @return List 故障单集合
	 */
	List<FaultBill> loadAllFaultBills();
	
	/**
	 * 保存故障单
	 * 
	 * @param faultBill 故障单实体
	 * @return
	 */
	void storeFaultBill(FaultBill faultBill);
	
	/**
	 * 删除故障单
	 * 
	 * @param faultBill 故障单实体
	 * @return
	 */
	void deleteFaultBill(FaultBill faultBill);
	
	/**
	 * 根据传入的故障单集合，删除集合中的事故单
	 * 
	 * @param faultBills 故障单集合
	 * @return
	 */
	void deleteAllFaultBill(Collection<FaultBill> faultBills);
}
