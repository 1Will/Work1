package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class ListPurchaseBillDetailController extends JasperReportsController {

	private final PurchaseBillManager purchaseBillManager;

	private final PurchaseBillDetailDao purchaseBillDetailDao;

	private PurchaseBill purchaseBill;

	private Set<PurchaseBillDetail> details = new HashSet();

	List result;

	HttpServletRequest request;

	public ListPurchaseBillDetailController(
			PurchaseBillManager purchaseBillManager,
			PurchaseBillDetailDao purchaseBillDetailDao) {
		this.purchaseBillManager = purchaseBillManager;
		this.purchaseBillDetailDao = purchaseBillDetailDao;
	}

	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		Map model = new HashMap();
		String purchaseBillid = RequestUtils.getStringParameter(httpservletrequest, "purchaseBillid");
		List<PurchaseBillDetail> purchaseBills = null;
		purchaseBills = purchaseBillDetailDao.loadByKey("purchaseBill",purchaseBillid);
		List<Long> ids = new ArrayList<Long>();
		if (null != purchaseBills) {
			for (PurchaseBillDetail detail : purchaseBills) {
				ids.add(detail.getId());
			}
			result = purchaseBillManager.loadPurchaseBillDtlByBillId(ids);
			model.put("reportData", result);
		}else if (purchaseBillid != null && !purchaseBillid.equals("")) {
			purchaseBill = purchaseBillManager.loadPurchaseBill((Long.parseLong(purchaseBillid)));
			details = purchaseBill.getPurchaseBillDetails();
			model.put("reportData", details);
		}
		return model;
	}
}
