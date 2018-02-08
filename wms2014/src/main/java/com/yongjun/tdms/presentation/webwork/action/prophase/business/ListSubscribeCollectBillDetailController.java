package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;

public class ListSubscribeCollectBillDetailController extends JasperReportsController{
		private final SubscribeCollectBillManager subscribeCollectBillManager;
		private final SubscribeDao subscribeDao;
	
	   private  List result;
	   HttpServletRequest request;
	   public ListSubscribeCollectBillDetailController(SubscribeCollectBillManager   subscribeCollectBillManager,
			   SubscribeDao subscribeDao){
		   this.subscribeCollectBillManager=subscribeCollectBillManager;
		   this.subscribeDao=subscribeDao;
	   }
		@Override
		protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
			Map model = new HashMap();
			String subscribeCollectBillid=RequestUtils.getStringParameter(httpservletrequest, "subscribeCollectBillid");	
			List<SubscribeDetail> subscribeDetail=null;
			subscribeDetail=subscribeDao.loadByKey("subscribeCollectBill", subscribeCollectBillid);
			List<Long> ids= new ArrayList<Long>();
			if(null!=subscribeDetail){				
				for (SubscribeDetail detail : subscribeDetail) {
					ids.add(detail.getId());
				}			
			}
			String flag="sd";
			result = subscribeCollectBillManager.loadSubscribeDetailView(ids,flag);
			model.put("reportData", result );
			return model;
		}
}
