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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inWareHouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;

/**
 * <p>Title:listAllSpareInBillReportController
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: listAllSpareInBillReportController.java 2008-11-25 14:13:10 yli$
 */
public class ListAllSpareInBillReportController extends JasperReportsController {
	private final SpareInBillManager spareInBillManager;
//	private final SupplierManager supplierManager;
//	private final UserManager userManager;
//	private Supplier supplier;
//	private User inPeople;
//	private User checkPeople;
	List result;
	public ListAllSpareInBillReportController(SpareInBillManager spareInBillManager
//			SupplierManager supplierManager,
//			UserManager userManager
			){
		this.spareInBillManager = spareInBillManager;
//		this.supplierManager = supplierManager;
//		this.userManager = userManager;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		String spareInBillNo = "";
		String spareInBillName = "";
		String inPeopleName = "";
		String checkPeopleName = "";
		String supplierName = "";
		//根据入库单号
		if(!RequestUtils.getStringParameter(httpservletrequest,"spareInBillNo",StringUtils.EMPTY).trim().equals("")){
			spareInBillNo = RequestUtils.getStringParameter(httpservletrequest,"spareInBillNo",StringUtils.EMPTY).trim();
		}
		//根据入库单名称
		if(!RequestUtils.getStringParameter(httpservletrequest,"spareInBillName",StringUtils.EMPTY).trim().equals("")){
			spareInBillName = RequestUtils.getStringParameter(httpservletrequest,"spareInBillName",StringUtils.EMPTY).trim();
		}
		//根据入库人
		if(!RequestUtils.getStringParameter(httpservletrequest,"inPeople",StringUtils.EMPTY).trim().equals("")){
			inPeopleName = RequestUtils.getStringParameter(httpservletrequest,"inPeople",StringUtils.EMPTY).trim();
		}
		//根据验收人
		if(!RequestUtils.getStringParameter(httpservletrequest,"checkPeople",StringUtils.EMPTY).trim().equals("")){
			checkPeopleName = RequestUtils.getStringParameter(httpservletrequest,"checkPeople",StringUtils.EMPTY).trim();
		}
		//根据供应商
		if(!RequestUtils.getStringParameter(httpservletrequest,"supplierId",StringUtils.EMPTY).trim().equals("")){
			supplierName = RequestUtils.getStringParameter(httpservletrequest,"supplierId",StringUtils.EMPTY).trim();
		}
		String[] queryInfo = new String[] {
				spareInBillNo,spareInBillName,inPeopleName,checkPeopleName,supplierName,
				RequestUtils.getStringParameter(httpservletrequest,
				"inDate_start", StringUtils.EMPTY).trim(), 
				RequestUtils.getStringParameter(httpservletrequest,
				"inDate_end", StringUtils.EMPTY).trim()
				};
		result = spareInBillManager.loadSpareInBillDetailView(queryInfo);
		Map model = new HashMap();
		model.put("reportData", result);
		return model;
	}

}
