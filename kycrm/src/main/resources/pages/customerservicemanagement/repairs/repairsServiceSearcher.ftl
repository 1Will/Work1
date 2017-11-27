<#--
	Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: customerInfoSearcher.ftl 2009-12-10 15:50:35Z wliu $ -->

<#include "../../includes/hco2011.ftl" />

<@inputTable>
	<tr>
	<@ww.textfield label="'${action.getText('repairs.code')}'" name="'repairs.code'" value="'${req.getParameter('repairs.code')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('repairs.customerInfo')}'" name="'repairs.customerInfo'" value="'${req.getParameter('repairs.customerInfo')?if_exists}'" cssClass="'underline'" />
 	
 	<@pp.dateRanger label="'${action.getText('repairs.repairsTime')}'" 
 						name="'repairs.repairsTime'" 
			            value="'${req.getParameter('repairs.repairsTime_start')?if_exists}|${req.getParameter('repairs.repairsTime_end')?if_exists}'"
			            cssClass="'underline'" 
			            dateFormat="'%Y-%m-%d'"
			            maxlength="10"/>
	</tr>
	<#--<tr>
	<@ww.select label="'${action.getText('服务类型')}'" 
				id="serviceType"
				name="'customerService.serviceType.id'" 
				value="'${req.getParameter('customerService.serviceType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allServiceType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
	<@ww.select label="'${action.getText('服务方式')}'" 
				id="serviceWay"
				name="'customerService.serviceWay.id'" 
				value="'${req.getParameter('customerService.serviceWay.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allServiceWay"
				emptyOption="false" 
				disabled="false">
			</@ww.select>		
			
	<#--<@ww.textfield label="'${action.getText('服务类型')}'" name="'customerService.serviceType'" value="'${req.getParameter('customerService.serviceType')?if_exists}'" cssClass="'underline'" />
	<@ww.textfield label="'${action.getText('服务方式')}'" name="'customerService.serviceWay'" value="'${req.getParameter('customerService.serviceWay')?if_exists}'" cssClass="'underline'" />
		-->
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
		
	function checkInvalidParms(){
	<#--	var beginDateMsg="${action.getText('时间格式不对，是：年-月-日')}";
	    if(queryDate("repairs.repairsTime_start","repairs.repairsTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('时间格式不对，是：年-月-日')}";
	    if(queryDate("repairs.repairsTime_start","repairs.repairsTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('repairs.repairsTime_start').value>getObjByName('repairs.repairsTime_end').value){
				alert('${action.getText('开始时间要在结束时间之前!')}');
				getObjByName('repairs.repairsTime_end').value="";
	       		getObjByName('repairs.repairsTime_end').focus();
				return false;
		}
		-->

		return true;
    }
    
    
    
    
    
    
    getObjByName(function(){
    <#--	<#if req.getParameter('customerService.serviceType.id')?exists>
    		jgetObjByName("#serviceType").val("${req.getParameter('customerService.serviceType.id')?if_exists}");
    	</#if>
    	<#if req.getParameter('customerService.serviceType.id')?exists>
    		jgetObjByName("#serviceWay").val("${req.getParameter('customerService.serviceWay.id')?if_exists}");
    	</#if>
    	-->
    });
</script>