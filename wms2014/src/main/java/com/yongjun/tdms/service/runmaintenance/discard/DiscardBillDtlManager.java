package com.yongjun.tdms.service.runmaintenance.discard;

import java.util.Collection;
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
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBillDtl;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
/**
 * <p>Title: DiscardBillDtlDao
 * <p>Description: 报废单明细数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DiscardBillDtlDao.java 8876 2007-12-02 02:47:27Z wzou $
 */
@Transactional(readOnly=true)
public interface DiscardBillDtlManager {
    /**
	 * 
	 * @param discardBillDtlId 根据报废单明细Id,获得报废单明细对象
	 * @return DiscardBillDtl
	 */
	@Transactional
public DiscardBillDtl loadDiscardBillDtl(Long discardBillDtlId);
	/**
	 * 
	 * @param discardBillDtlIds 根据报废单明细Ids,获得报废单明细集合
	 * @return List
	 */
	List<DiscardBillDtl> loadAllDiscardBillDtls(Long[] discardBillDtlIds);
	/**
	 * 
	 * @param discardBillDtl   保存报废单明细
	 */
	@Transactional
	void storeDiscardBillDtl(DiscardBillDtl discardBillDtl);
	/**
	 * 
	 * @param discardBillDtl   给据报废单明细对象 删除报废单明细
	 */
	@Transactional
	void deleteDiscardBillDtl(DiscardBillDtl discardBillDtl);
	/**
	 * 
	 * @param discardBillDtls  获得所要删除的报废单明细集合
	 */
	@Transactional
	void deleteAllDiscardBillDtl(Collection<DiscardBillDtl> discardBillDtls);
	@Transactional
	void storeDiscardDevDetail(DiscardBill discardBill, String newDeviceIds);
	@Transactional
	void storeDiscardDevBillDtl(String allDiscardDeviceId,String allmemoInfo);
}
