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
		<@ww.textfield label="'${action.getText('contractManagement.code')}'" name="'contractManagement.code'" value="'${req.getParameter('contractManagement.code')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('contractManagement.contractName')}'" name="'contractManagement.contractName'" value="'${req.getParameter('contractManagement.contractName')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('contractManagement.customerInfo.name')}'" name="'contractManagement.customerInfo.name'" value="'${req.getParameter('contractManagement.customerInfo.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('contractManagement.linkman.name')}'" name="'contractManagement.linkman.name'" value="'${req.getParameter('contractManagement.linkman.name')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('contractManagement.saleman.name')}'" name="'contractManagement.saleman.name'" value="'${req.getParameter('contractManagement.saleman.name')?if_exists}'" cssClass="'underline'" />
		<@ww.textfield label="'${action.getText('contractManagement.deparment.name')}'" name="'contractManagement.deparment.name'" value="'${req.getParameter('contractManagement.deparment.name')?if_exists}'" cssClass="'underline'" />
	</tr>
	<tr>
	
		<@pp.dateRanger label="'${action.getText('contractManagement.ciemdinghTime')}'" 
 						name="'contractManagement.ciemdinghTime'" 
			            value="'${req.getParameter('contractManagement.ciemdinghTime_start')?if_exists}|${req.getParameter('contractManagement.ciemdinghTime_end')?if_exists}'"
			           	readonly="true"
			            cssClass="'underline'" 
			            maxlength="10"/>
			            
	<@ww.select label="'${action.getText('contractManagement.contractType')}'" 
				id="contractType"
				name="'contractManagement.contractType.id'" 
				value="'${req.getParameter('contractManagement.contractType.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allContractType"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
		
	<@ww.select label="'${action.getText('contractManagement.state')}'" 
				id="state"
				name="'contractManagement.state.id'" 
				value="'${req.getParameter('contractManagement.state.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allState"
				emptyOption="false" 
				disabled="false">
			</@ww.select>
	</tr>
	<tr>	
		<@crm_onlySearchInvalid_checkBox />
	</tr>
</@inputTable>
<script language="javascript">
	
	function checkInvalidParms(){
	
		var beginDateMsg="${action.getText('invalidParms.ciemdinghTime1')}";
	    if(queryDate("contractManagement.ciemdinghTime_start","contractManagement.ciemdinghTime_end",
	       beginDateMsg,null)==false){
	   	   return false;
	    }	 
	    var endDateMsg="${action.getText('invalidParms.ciemdinghTime2')}";
	    if(queryDate("contractManagement.ciemdinghTime_start","contractManagement.ciemdinghTime_end",
	       endDateMsg,null)==false){   
	   	   return false;
	    }
	    if(getObjByName('contractManagement.ciemdinghTime_start').value>getObjByName('contractManagement.ciemdinghTime_end').value){
				alert('${action.getText('invalidParms.ciemdinghTime3')}');
				getObjByName('contractManagement.ciemdinghTime_end').value="";
	       		getObjByName('contractManagement.ciemdinghTime_end').focus();
				return false;
		}
		return true;
    }
  getObjByName(function(){
  
   	<#if req.getParameter('contractManagement.contractType.id')?exists>
    		getObjByName("contractType").value=("${req.getParameter('contractManagement.contractType.id')?if_exists}");
    	</#if>
    	<#if req.getParameter('contractManagement.state.id')?exists>
    		getObjByName("state").value=("${req.getParameter('contractManagement.state.id')?if_exists}");
    	</#if>
    	
  });
</script>