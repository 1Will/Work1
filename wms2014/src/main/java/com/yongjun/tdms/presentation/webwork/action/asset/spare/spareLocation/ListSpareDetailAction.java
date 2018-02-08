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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.spareLocation;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;

/**
 * <p>Title: ListSpareDetailAction
 * <p>Description: 备件明细台帐页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class ListSpareDetailAction extends ValueListAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final SpareLocationManager spareLocationManager;
	private final SpareManager spareManager;	
	
	//spareLocatin id 用","分割开的字符串
	private String allSpareLocationIdValue;
    //单价 用","分割开的字符串
	private String allUnitPriceValue;
	//库位号 用","分割开的字符串
	private String allLocationCodeValue;
	
	private String allWareHouseIdValue;
	
	//备件
	private Spare spare;
	
	public ListSpareDetailAction(SpareLocationManager spareLocationManager,SpareManager spareManager){
		this.spareLocationManager = spareLocationManager;
		this.spareManager = spareManager;
	}
	
	@Override
	public void prepare() throws Exception {
		super.prepare();
		if (this.hasId("spare.id")) {
			this.spare = this.spareManager.loasSpare(this.getId("spare.id"));
		}
	}

	@Override
	public String execute() throws Exception {
		
		if(isSave()) {
			return saveOrUpdateInfoOfSpareLocation();
		}
		return SUCCESS;
	}
	
	public String saveOrUpdateInfoOfSpareLocation() {
		//获取页面传来的spareLocation id 用","分割开的字符串
		if(!StringUtils.isEmpty(request.getParameter("allSpareLocationIdValue"))){
			this.allSpareLocationIdValue = request.getParameter("allSpareLocationIdValue");
		}
        //获取页面传来的单价 用","分割开的字符串
		if(!StringUtils.isEmpty(request.getParameter("allUnitPriceValue"))){
			this.allUnitPriceValue = request.getParameter("allUnitPriceValue");
		}
        //获取页面传来的库位号 用","分割开的字符串
		if(!StringUtils.isEmpty(request.getParameter("allLocationCodeValue"))){
			this.allLocationCodeValue = request.getParameter("allLocationCodeValue");
		}
		if(!StringUtils.isEmpty(request.getParameter("allWareHouseIdValue"))){
			this.allWareHouseIdValue = request.getParameter("allWareHouseIdValue");
		}
		if (null != allSpareLocationIdValue && null != allUnitPriceValue) {
			this.spareLocationManager.storePartInfoOfSpareLocation(
					allSpareLocationIdValue, allUnitPriceValue,
					allLocationCodeValue,allWareHouseIdValue);
		}
		return SUCCESS;
	}
	/**
	 * 判断页面是否点击保存"
	 * @return true | false
	 */
	private boolean isSave() {
		if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		  }
	    }
		return false;
	}
	
	@Override
	protected String getAdapterName() {
		// TODO Auto-generated method stub
		return "spareDetails";
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}
}
