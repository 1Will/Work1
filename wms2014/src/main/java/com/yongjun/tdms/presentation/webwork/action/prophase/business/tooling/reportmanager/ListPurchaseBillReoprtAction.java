/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.reportmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

/**
 * @author Administrator
 *
 */
public class ListPurchaseBillReoprtAction extends ValueListAction {

	
	
	
	
 
	
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
	
	public String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str= null; 
		str = sdf.format(date);
		 
		return str;
	}
	
	@Override
	protected String getAdapterName() {
		return "listPurchaseBillReport";
	}
	
	

}
