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
package com.yongjun.tdms.dao.runmaintenance.fault.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.fault.FaultBillDao;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;

/**
 * <p>Title: HibernateFaultBill
 * <p>Description: 故障单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: HibernateFaultBill.java 8882 2007-12-02 03:06:15Z zbzhang $
 * @see com.yongjun.tdms.dao.runmaintenance.tooling.record.FaultBillDao
 */
public class HibernateFaultBill extends BaseHibernateDao implements FaultBillDao{

	public FaultBill loadFaultBill(Long faultBillId) {
		return this.load(FaultBill.class, faultBillId);
	}

	public List<FaultBill> loadAllFaultBills(Long[] faultBillIds) {
		return this.loadAll(FaultBill.class, faultBillIds);
	}

	public List<FaultBill> loadAllFaultBills() {
		return this.loadAll(FaultBill.class);
	}

	public void storeFaultBill(FaultBill faultBill) {
		this.store(faultBill);
	}

	public void deleteFaultBill(FaultBill faultBill) {
		this.delete(faultBill);
	}

	public void deleteAllFaultBill(Collection<FaultBill> faultBills) {
		this.deleteAll(faultBills);
	}

}
