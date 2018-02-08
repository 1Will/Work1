package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

public class ListSubscribeDetailController extends JasperReportsController{
	private final SubscribeManager   subscribeManager;
	
	private final SubscribeDao subscribeDao;
	
	   private  List result;
	   HttpServletRequest request;
	   public ListSubscribeDetailController(SubscribeManager   subscribeManager,SubscribeDao subscribeDao){
		   this.subscribeManager=subscribeManager;
		   this.subscribeDao=subscribeDao;
	   }
		@Override
		protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {			
			
			String subscribeId=RequestUtils.getStringParameter(httpservletrequest, "subscribeid");			
			
			List<SubscribeDetail> subscribeDetail=null;
			subscribeDetail=subscribeDao.loadByKey("subscribe", subscribeId);
			List<Long> ids= new ArrayList<Long>();
			if(null!=subscribeDetail){
				for (SubscribeDetail detail : subscribeDetail) {
					ids.add(detail.getId());
				}
			}			
			String flag="sd";
			result = subscribeManager.loadSubscribeDetailView(ids,flag);
			Map model = new HashMap();			
			model.put("reportData", result );
			return model;
		}
}
