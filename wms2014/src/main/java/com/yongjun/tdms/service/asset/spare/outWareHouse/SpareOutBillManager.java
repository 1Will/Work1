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
package com.yongjun.tdms.service.asset.spare.outWareHouse;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailView;

/**
 * <p>Title: SpareOutBillManager
 * <p>Description: 备件出库业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zsmzhang@yj-technology.com
 * @version $Id:$
 */
public interface SpareOutBillManager {
    List<SpareOutBill> loadAllSpareOutBill(Long[] spareOutBillIds);
    List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId);
    //查询出所有id不在outDetailids中的
    List<SpareOutBillDetail> loadAllSpareOutBill(long spareOutBillId,String outDetailids);
    SpareOutBill loadSpareOutBill(Long spareOutBillId);
    @Transactional
    void storeSpareOutBill(SpareOutBill spareOutBill);
    @Transactional
	void deleteSpareOutBill(SpareOutBill spareOutBill);
    @Transactional
	void deleteAllSpareOutBill(Collection<SpareOutBill> spareOutBill);
	@Transactional
	void disabledAllSpareOutBill(Collection<SpareOutBill> spareOutBills)throws Exception;
	@Transactional
	void enabledAllSpareOutBill(Collection<SpareOutBill> spareOutBills);
	List<SpareOutBillDetailView> loadSpareOutBillDetail(Long outWareBillId);
	List<SpareOutBillDetailView> loadSpareOutBillDetail(String[] array) throws HibernateException;
	@Transactional
	void accordingSpareOutBillDetailStatusUpdateSpareOutStatus(SpareOutBill spareOutBill);
    /**
     * 查询列表下所有金额
     * @param param
     * @return
     */
	public Double showOutTotalPrice(String[] param);
	/**
     * 查询旧件列表下所有金额
     * @param param
     * @return
     */
	public Double showOldOutTotalPrice(String[] param);
	
	public Double showOldScrapTotalPrice(String[] param);
	
	public Double showOldMaintainTotalPrice(String[] param);
}
