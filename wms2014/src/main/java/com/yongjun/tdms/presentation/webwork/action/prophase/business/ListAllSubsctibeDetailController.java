package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

public class ListAllSubsctibeDetailController  extends JasperReportsController{
	private final SubscribeManager   subscribeManager;
	
	private final SubscribeDao subscribeDao;
	private  List result;
	HttpServletRequest request;
	
	public ListAllSubsctibeDetailController(SubscribeManager subscribeManager,
			SubscribeDao subscribeDao){
		this.subscribeManager = subscribeManager;
		this.subscribeDao = subscribeDao;
	}
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		//申购单ID集合
		String subscribeBillIds=RequestUtils.getStringParameter(httpservletrequest, "subscribeBillIds");
//		String [] subscribeBillId=null;
//		if(subscribeBillIds!=null){
//			subscribeBillId=subscribeBillIds.split(",");
//		}
//		List<Long> ids= new ArrayList<Long>();
//		for(int i=0;i<subscribeBillId.length;i++){
//			ids.add(Long.valueOf(subscribeBillId[i]));
//		}
		//System.out.println("subscribeBillIds: " + subscribeBillIds);
		result = subscribeManager.loadSubscribeDetailViewByBillIds(subscribeBillIds);
		Map model = new HashMap();			
		model.put("reportData", result );
		return model;
	}

}
