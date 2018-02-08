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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailView;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;

/**
 * <p>Title:SpareInBillDetailReportController
 * <p>Description:入库明细报表打印控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class SpareInBillDetailReportController extends JasperReportsController {
	private final SpareInBillManager spareInBillManager;
	private List<SpareInBillDetailView> details;
	HttpServletRequest request;
	public SpareInBillDetailReportController(SpareInBillManager spareInBillManager){
		this.spareInBillManager = spareInBillManager;
	}
	/**
	 * 根据请求中的备件入库单的ID,获取入库单对象，并且放入model中
	 * @param request http请求，包含备件入库单的ID
	 * @return Map 包含入库单对象的键值对
	 */
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest)
			throws Exception {
		Map model = new HashMap();
		String spareInBillId=RequestUtils.getStringParameter(httpservletrequest, "spareInBillId");
		String listPage = RequestUtils.getStringParameter(httpservletrequest, "list");
		String[] queryInfo = spareInBillId.split(","); //获取页面列表值
		details = new ArrayList<SpareInBillDetailView>();
		//列表页面打印验收单
		if(listPage !=null && listPage.equals("list")){
			//循环加载当前列表的明细记录
			for(int i = 0; i < queryInfo.length; i++){
				List<SpareInBillDetailView> detailsList = spareInBillManager.loadSpareInBillDetailView(Long.parseLong(queryInfo[i]));
				if(detailsList.size()>0){
					//当明细记录数少于5条时获取验收人名称
					String checkpeople = detailsList.get(0).getCheckpeople();
					String warehouse = detailsList.get(0).getWarehouse();
					//当明细记录数少于5条时添加空对象
					int _index_ = 5-detailsList.size();
					for(int j=0 ;j<_index_;j++){
						SpareInBillDetailView sbd =  new SpareInBillDetailView();
						//设置空对象的验收人名称以便在页面显示
						sbd.setCheckpeople(checkpeople);
						sbd.setWarehouse(warehouse);
						detailsList.add(sbd);
					}
				}
				details.addAll(detailsList);
			}
		}else{//明细页面打印验收单
			if(spareInBillId!=null&&!spareInBillId.equals("")){
				details = spareInBillManager.loadSpareInBillDetailView(Long.parseLong(spareInBillId));
			}
		}
		model.put("reportData",details);
		return model;
	}

}
