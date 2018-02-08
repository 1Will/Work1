<#--
  Copyright (c) 2001-2005 vTradEx Information Technology Co.,Ltd. All Rights Reserved.
    This software is the confidential and proprietary information of vTradEx
  Information Technology Co.,Ltd. ("Confidential Information").  You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with vTradEx.
    VTRADEX MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
  SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
  IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
  PURPOSE, OR NON-INFRINGEMENT. VTRADEX SHALL NOT BE LIABLE FOR ANY DAMAGES
  SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
  THIS SOFTWARE OR ITS DERIVATIVES.
 -->
<#--$Id: supplierSearch.ftl 8122 2007-10-30 05:38:08Z mwei $ -->
	<@inputTable>
	       <tr>
			 <@ww.textfield label="'预算编号'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>
			 <@ww.textfield label="'预算名称'" name="'area.code'" value="" cssClass="'underline'" readonly="true"/>			
	       </tr>
	       <tr>	
            <@ww.select label="'分类'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'工装', 
	                    			'设备'
	                    	  	  }"
	                    	value="selectedDevice"
	         />	       
			 <@pp.datePicker readonly="true" label="'年度'" size="6" name="'month'" required="false" cssClass="'underline'" dateFormat="'%Y'"/> 
	       </tr>
	        <tr>       
	         <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('冲压一车间')}', 
	                    			'${action.getText('冲压二车间')}',
	                    			'${action.getText('冲焊一车间')}'
	                    	  	  }"
	                    	value="selectedDevice"
           />
		   <@ww.select label="'状态'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{'${action.getText('所有')}', 
	                    			'${action.getText('提交')}', 
	                    			'${action.getText('审批中')}', 
	                    			'${action.getText('未审批')}',
	                    			'${action.getText('拒绝')}'
	                    	  	  }"
	                    	value="selectedDevice"
	       />
	      </tr>
	</@inputTable> 