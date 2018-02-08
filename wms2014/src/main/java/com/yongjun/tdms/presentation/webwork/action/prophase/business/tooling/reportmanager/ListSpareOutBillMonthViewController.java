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
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.business.tooling.reportmanager.SpareOutBillMonthView;
import com.yongjun.tdms.service.prophase.business.tooling.reportmanager.SpareOutBillMonthViewManager;

/**
 * <p>Title: com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager.ListSpareOutBillMonthViewController.java</p>
 * <p>Description: 一级备件出库月度报表控制类</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:ListSpareOutBillMonthViewController.java 2011-3-23 zzb $</p>
 */
public class ListSpareOutBillMonthViewController extends
		JasperReportsController {
	
	private final SpareOutBillMonthViewManager spareOutBillMonthViewManager;
    public ListSpareOutBillMonthViewController(
    		SpareOutBillMonthViewManager spareOutBillMonthViewManager){
    	this.spareOutBillMonthViewManager = spareOutBillMonthViewManager;
    }
	@SuppressWarnings("unchecked")
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		String startDate = httpservletrequest.getParameter("outDate");
        startDate = startDate.substring(0, 7);
        
        
		Map model = new HashMap();
		String endDate = null;

		if (null != startDate && !"".equals(startDate)) {
			startDate = startDate + "-01";
			Date date = this.stringToDate(startDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, 1);

			endDate = this.dateToString(cal.getTime());

			List<SpareOutBillMonthView> result = new ArrayList<SpareOutBillMonthView>();
            result = this.spareOutBillMonthViewManager.loadSpareOutBillMonthViewNum(this.stringToDate(startDate), 
            		this.stringToDate(endDate));
			model.put("reportData", result);

		}

		return model;

	}

	/**
	 * 字符串到日期
	 * @param str
	 * @return
	 */
	public Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期到字符串
	 * @param date
	 * @return
	 */
	public String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = null;
		str = sdf.format(date);

		return str;
	}

}
