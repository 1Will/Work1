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
package com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.SpareOutBillMonthViewDao;
import com.yongjun.tdms.model.prophase.business.tooling.reportmanager.SpareOutBillMonthView;

/**
 * <p>Title: com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate.HibernateSpareOutBillMonthView.java</p>
 * <p>Description: the HibernateSpareOutBillMonthViewD class</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:HibernateSpareOutBillMonthViewD.java 2011-3-23 zzb $</p>
 */
public class HibernateSpareOutBillMonthView extends BaseHibernateDao
implements SpareOutBillMonthViewDao {

 
	public void storeSpareOutView(SpareOutBillMonthView outView) {
		 super.store(outView);

	}
	
	/**
	 * 根据时间段加载view
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public List<SpareOutBillMonthView> loadSpareOutBillMonthViewNum(Date startDate, Date endDate) {
		 List<SpareOutBillMonthView> list = new ArrayList<SpareOutBillMonthView>();
		 Session session = this.getSession();
		 Connection conn = session.connection();
		 CallableStatement call = null;
		 ResultSet rs = null;
	 
		 try {
		    call = conn.prepareCall("{Call GET_OUT_BILL_NUM(?,?)}");
		    call.setDate(1, new java.sql.Date(startDate.getTime()));
		    call.setDate(2, new java.sql.Date(endDate.getTime()));
			 
			call.execute();	
			rs = (ResultSet) call.getResultSet();
			while (rs.next()) {
				SpareOutBillMonthView view = new SpareOutBillMonthView();
				view.setMonth(rs.getString("month"));
				view.setTotalPrice(rs.getDouble("totalPrice"));
				view.setDept(rs.getString("dept"));
				view.setNum(rs.getInt("num"));
				view.setBnum(rs.getInt("bnum"));
				view.setBtotalPrice(rs.getDouble("btotalPrice"));
			    list.add(view);
				 
			}
			 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		 
      return list;
	}

}

