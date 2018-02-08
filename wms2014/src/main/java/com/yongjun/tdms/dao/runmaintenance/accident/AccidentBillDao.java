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
package com.yongjun.tdms.dao.runmaintenance.accident;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.accident.AccidentBill;


/**
 * <p>Title: AccidentBillDao
 * <p>Description: 事故单数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: AccidentBillDao.java 8882 2007-12-02 03:06:15Z zbzhang $
 */
public interface AccidentBillDao {
	/**
	 * 根据传入的事故单ID,获取事故单
	 * 
	 * @param accidentId 事故单ID
	 * @return AccidentBill 事故单实体
	 */
	AccidentBill loadAccidentBill(Long accidentId);
	
	/**
	 * 根据传入的事故单ID集合，获取集合中的事故单
	 * 
	 * @param accidentIds 事故单ID集合
	 * @return  List 事故单集合
	 */
	List<AccidentBill> loadAllAccidentBills(Long[] accidentIds);
	
	/**
	 * 获取集合中的事故单
	 * 
	 * @return List 事故单集合
	 */
	List<AccidentBill> loadAllAccidentBills();
	
	/**
	 * 保存事故单
	 * 
	 * @param accidentBill 事故单实体
	 * @return
	 */
	void storeAccidentBill(AccidentBill accidentBill);
	
	/**
	 * 删除事故单
	 * 
	 * @param accidentBill 事故单实体
	 * @return
	 */
	void deleteAccidentBill(AccidentBill accidentBill);
	
	/**
	 * 根据传入的事故单集合，删除集合中的事故单
	 * 
	 * @param accidentBill 事故单集合
	 * @return
	 */
	void deleteAllAccidentBils(Collection<AccidentBill> accidentBill);
	
}
