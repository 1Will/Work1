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
package com.yongjun.tdms.presentation.webwork.action.base.codevalue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.exception.ErroSaveException;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;

/**
 * <p>Title:EditSpareTypeAction
 * <p>Description:备件分类维护页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class EditSpareTypeAction extends PrepareAction {

	private static final long serialVersionUID = -8602260376044777787L;
	
	private SpareType spareType;				//备件分类对象
	private List<SpareType> spareTypes;			//备件分类集合
	private final SpareTypeManager spareTypeManager;
	public EditSpareTypeAction(SpareTypeManager spareTypeManager){
		this.spareTypeManager = spareTypeManager;
	}
	public void prepare() throws Exception {
		if(null == this.spareType){
			//判断备件分类是新建还是修改
			if(this.hasId("spareType.id")){
				this.spareType = this.spareTypeManager.loadSpareType(this.getId("spareType.id"));
			}else{
				this.spareType = new SpareType();
			}
		}
	}
	
	public String save()throws IOException {
		boolean isNew = this.spareType.isNew();
    	//如果该备件分类编号在库中存在 那么就不能在保存同样的备件分类编号
/*    	if(!StringUtils.isEmpty(request.getParameter("spareType.referCode"))&&spareType.isNew()){
    		 try {
    			 CodeValue spareTypeExists=codeValueManager.loadCodeTypeByReferCode(request.getParameter("spareType.referCode"));
    			 	if(spareTypeExists!=null){
    			 		//如果存在同编号的就抛出异常，提醒用户该编号已经存在
	        			if(request.getParameter("spareType.referCode").equals(spareTypeExists.getReferCode())){
	        				throw new Exception();
	        			}		
    	        	}
    	    	} catch (Exception e) {
    	    		this.addActionError(this.getText("referCode.already.exists",
    	                    Arrays.asList(new Object[]{request.getParameter("spareType.referCode")})));
    	    		return ERROR;
    	    	}	
    	}
		if (isNew) {
			//根据"82"来获取所有masterCode=82的集合,因为82在codevalue中是备件种类的ID
			spareTypes = this.codeValueManager.loadAllValuesByCodeId(Long.valueOf(82));
			int code=0,max=0;
			String s_code;
			//循环出所有的备件类型
			for(CodeValue spareType : spareTypes) {
				//获取备件类型的code的值并转换成int类型,对其进行比较大小，取出最大的一个
				code = Integer.valueOf(spareType.getCode());
				if (code>max) {
					max = code;
				}
			}
			//将得到的最大的code+1，并转换成字符串
			s_code = Integer.toString((max+1));
			//由于在字符串转换成int类型的时候数字0丢失，下面将字符串中的0给加上
			s_code = "0"+s_code;
			this.spareType.setCode(s_code);
			//根据013来获得备件类型
			CodeValue spareType82 = this.codeValueManager.loadCodeTypeByCode(CodeConstants.SPARE_TYPE);
			//存储备件类型的标识"82"
			this.spareType.setMasterCode(spareType82);
		}*/
		
		if(!StringUtils.isEmpty(request.getParameter("toolingDevFlag"))){
			if (request.getParameter("toolingDevFlag").equals("DEVICE")) {
				spareType.setToolingDevFlag(SysModel.DEVICE);
			}
			if(request.getParameter("toolingDevFlag").equals("TOOLING")){
				spareType.setToolingDevFlag(SysModel.TOOLING);
			}
		}
		try{
			try {
				this.spareTypeManager.storeSpareType(spareType);
			} catch (Exception e) {
				throw new ErroSaveException();
			}
		}catch(ErroSaveException e){
			this.addActionMessage(this.getText("spareType.referCode.exists",Arrays.asList(new Object[] { spareType.getCode()})));
			return NEW;
		}
		if(isNew){
			this.addActionMessage(this.getText("spareType.add.success",
					Arrays.asList(new Object[] { spareType.getCode()})));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareType.edit.success",
					Arrays.asList(new Object[] { spareType.getCode()})));
			return SUCCESS;
		}
	}
	/**
	 * 类别选择
	 * @return
	 */
	public List<LabelValue> getSpareSort() {  
		LabelValue[] arrays = this.wrapEnum(SysModel.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public SpareType getSpareType() {
    	return spareType;
    }
	public void setSpareType(SpareType spareType) {
    	this.spareType = spareType;
    }

}
