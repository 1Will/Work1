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
package com.yongjun.tdms.dao.runmaintenance.accident.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.accident.AccidentBillDao;
import com.yongjun.tdms.model.runmaintenance.accident.AccidentBill;

/**
 * <p>Title: HibernateAccidentBill
 * <p>Description: 事故单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: HibernateAccidentBill.java 8882 2007-12-02 03:06:15Z zbzhang $
 * @see com.yongjun.tdms.dao.runmaintenance.tooling.record.AccidentBillDao
 */
public class HibernateAccidentBill extends BaseHibernateDao implements
		AccidentBillDao {

	public AccidentBill loadAccidentBill(Long accidentId) {
		return this.load(AccidentBill.class, accidentId);
	}

	public List<AccidentBill> loadAllAccidentBills(Long[] accidentIds) {
		return this.loadAll(AccidentBill.class, accidentIds);
	}

	public List<AccidentBill> loadAllAccidentBills() {
		return this.loadAll(AccidentBill.class);
	}

	public void storeAccidentBill(AccidentBill accidentBill) {
		this.store(accidentBill);
	}

	public void deleteAccidentBill(AccidentBill accidentBill) {
		this.delete(accidentBill);
	}

	public void deleteAllAccidentBils(Collection<AccidentBill> accidentBill) {
		this.deleteAll(accidentBill);
	}

}
