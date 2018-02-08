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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.fault;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * <p>Title: ListFaultAnalysisByFaultCatorgyAction
 * <p>Description: 故障类型故障统计访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wliu@yj-technology.com
 * @version $Id: ListFaultAnalysisByFaultCatorgyAction.java 11417 2009-09-11 15:01:51Z wliu $
 */
public class ListFaultAnalysisByFaultCatorgyAction
extends ValueListAction {

	private static final long serialVersionUID = 1L;
	//故障类型业务逻辑接口
	private final CodeValueManager codeValueManager;
	
	/**
	 * 故障类型故障统计访问控制类构造函数
	 * 注入故障类型业务逻辑接口
	 * @param codeValueManager 
	 */
	public ListFaultAnalysisByFaultCatorgyAction(CodeValueManager codeValueManager) {
		
		this.codeValueManager = codeValueManager;
	}
	
	/**
	 * 默认调用方法
	 */
	@Override
	public void prepare() throws Exception {

		this.setFirst(true);
	}
	
	/**
	 * 调用valueList配置文件关联的hql语句
	 */
	@Override
	protected String getAdapterName() {
		
		return "FaultAnalysisByFaultCatorgyHQL";
	}

	/**
	 * 获得所有故障类型信息，并添加首行选择所有
	 * @return
	 */
	public List<CodeValue> getAllFaultCatorgys(){
		
		List<CodeValue> list = codeValueManager.LoadAllValuesByCode(CodeConstants.FAULT_CATEGORY);
		CodeValue c = new CodeValue();
		c.setValue(this.getText("select.option.all"));
		c.setId(-1L);
		list.add(0, c);
		return list;
	}
	
}
